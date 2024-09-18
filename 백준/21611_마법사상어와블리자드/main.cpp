#include <iostream>
#include <vector>

using namespace std;

int N, M;
vector<vector<int>> board;
vector<vector<int>> direction;
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, 1, 0, -1};
vector<int> exploded(3);

void blizzard(int d, int s) {
    int dx1[] = {0, 0, -1, 1};
    int dy1[] = {-1, 1, 0, 0};

    int x = N / 2;
    int y = N / 2;
    for (int i = 0; i < s; ++i) {
        x = x + dx1[d];
        y = y + dy1[d];

        board[y][x] = 0;
    }
}

void initDirection() {
    int x = N / 2;
    int y = N / 2;
    int cnt = 0;
    int s = 0;
    while (1) {
        int d = cnt % 4;
        if (cnt % 2 == 0)
            ++s;

        bool outBound = false;
        for (int i = 0; i < s; ++i) {
            direction[y][x] = d;
            x = x + dx[d];
            y = y + dy[d];
            if (x < 0 || x >= N || y < 0 || y >= N) {
                outBound = true;
                break;
            }
        }

        if (outBound)
            break;
        
        ++cnt;
    }
}

void moveBall() {
    board[N / 2][N / 2] = -1;
    int curX = N / 2;
    int curY = N / 2;
    int prevX = curX;
    int prevY = curY;
    int cnt = 0;
    int s = 0;
    while (1) {
        int d = direction[curY][curX];
        if (cnt % 2 == 0)
            ++s;

        bool outBound = false;
        for (int i = 0; i < s; ++i) {
            curX = curX + dx[d];
            curY = curY + dy[d];

            if (curX < 0 || curX >= N || curY < 0 || curY >= N) {
                outBound = true;
                break;
            }

            /*
            (prev == 0 && cur == 0) => ++cur
            (prev == 0 && cur != 0) => ++cur; ++prev;
            */
            if (board[prevY][prevX] == 0 && board[curY][curX] == 0)
                continue;
            else if (board[prevY][prevX] == 0 && board[curY][curX] != 0) {
                int prevDir = direction[prevY][prevX];
                board[prevY][prevX] = board[curY][curX];
                board[curY][curX] = 0;
                prevX = prevX + dx[prevDir];
                prevY = prevY + dy[prevDir];
            } else if (board[curY][curX] == 0) {
                prevX = curX;
                prevY = curY;
            }
        }

        if (outBound)
            break;
        
        ++cnt;
    }
}

bool explodeBall() {
    bool ret = false;
    int curX = N / 2;
    int curY = N / 2;
    int prevX = curX;
    int prevY = curY;
    int sameCnt = 0;
    int cnt = 0;
    int s = 0;
    while (1) {
        int d = direction[curY][curX];
        if (cnt % 2 == 0)
            ++s;

        bool finish = false;
        for (int i = 0; i < s; ++i) {
            curX = curX + dx[d];
            curY = curY + dy[d];

            /*
            (prev != cur || cur == 0) { if (sameCnt >= 4) explode(); prev = cur} ==> if (cur == 0) outBound break;
            (prev == cur) { ++sameCnt; }
            */
            if (board[prevY][prevX] != board[curY][curX]) {
                if (sameCnt >= 4) {
                    // explode
                    ret = true;
                    exploded[board[prevY][prevX] - 1] += sameCnt;
                    while (sameCnt--) {
                        int prevDir = direction[prevY][prevX];

                        board[prevY][prevX] = 0;

                        prevX = prevX + dx[prevDir];
                        prevY = prevY + dy[prevDir];
                    }
                } else {
                    prevX = curX;
                    prevY = curY;
                    sameCnt = 1;
                }

                // finish
                if (board[curY][curX] == 0) {
                    finish = true;
                    break;
                }
            } else
                ++sameCnt;
        }

        if (finish)
            break;
        
        ++cnt;
    }
    return ret;
}

vector<vector<int>> setNewBoard() {
    vector<vector<int>> newBoard(N, vector<int>(N));
    int newX = N / 2;
    int newY = N / 2;

    int curX = N / 2 - 1;
    int curY = N / 2;
    int prevX = curX;
    int prevY = curY;
    int sameCnt = 1;
    int cnt = 1;
    int s = 1;
    if (board[curY][curX] == 0)
        return newBoard;
    while (1) {
        int d = direction[curY][curX];
        if (cnt % 2 == 0)
            ++s;

        bool finish = false;
        for (int i = 0; i < s; ++i) {
            curX = curX + dx[d];
            curY = curY + dy[d];

            /*
            (prev != cur || cur == 0) { if (sameCnt >= 4) explode(); prev = cur} ==> if (cur == 0) outBound break;
            (prev == cur) { ++sameCnt; }
            */
            if (board[prevY][prevX] != board[curY][curX]) {
                int n[] = {sameCnt, board[prevY][prevX]};

                for (int j = 0; j < 2; ++j) {
                    int newDir = direction[newY][newX];
                    newX = newX + dx[newDir];
                    newY = newY + dy[newDir];

                    if (newX < 0 || newX >= N || newY < 0 || newY >= N)
                        return newBoard;

                    newBoard[newY][newX] = n[j];
                }

                prevX = curX;
                prevY = curY;
                sameCnt = 1;

                // finish
                if (board[curY][curX] == 0) {
                    finish = true;
                    break;
                }
            } else
                ++sameCnt;
        }

        if (finish)
            break;
        
        ++cnt;
    }
    return newBoard;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M;
    board.resize(N, vector<int>(N));
    direction.resize(N, vector<int>(N));
    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            cin >> board[i][j];

    initDirection();

    while (M--) {
        int d, s;
        cin >> d >> s;

        blizzard(d - 1, s);
        moveBall();
        while (explodeBall()) {
            moveBall();
        }
        board = setNewBoard();
    }

    int sum = 0;
    for (int i = 0; i < 3; ++i)
        sum += exploded[i] * (i + 1);
    
    cout << sum;

    return 0;
}