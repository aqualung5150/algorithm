#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int M, S;
vector<vector<vector<int>>> prevBoard(4, vector<vector<int>>(4));
vector<vector<vector<int>>> board(4, vector<vector<int>>(4));
vector<vector<int>> scent(4, vector<int>(4));
int sharkX;
int sharkY;
int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
int dy[] = {0, -1, -1, -1, 0, 1, 1, 1};
int sharkDX[] = {0, -1, 0, 1};
int sharkDY[] = {-1, 0, 1, 0};

bool go(int x, int y, int d) {
    for (int i = 8; i > 0; --i) {
        int curD = (d + i) % 8;
        int nx = x + dx[curD];
        int ny = y + dy[curD];

        if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || scent[ny][nx] > 0 || (nx == sharkX && ny == sharkY))
            continue;

        board[ny][nx].push_back(curD);
        return true;
    }
    return false;
}

void removeScentAndBoardAppend() {
    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            --scent[i][j];
            for (int k = 0; k < board[i][j].size(); ++k)
                prevBoard[i][j].push_back(board[i][j][k]);
        }
    }
    board.clear();
    board.resize(4, vector<vector<int>>(4));
}

vector<int> getDirections() {
    vector<int> ret;
    int maxCnt = -1;
    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            for (int k = 0; k < 4; ++k) {
                vector<vector<vector<int>>> temp = board;
                vector<int> curDir = {i, j, k};
                int cnt = 0;
                int x = sharkX;
                int y = sharkY;
                for (int a = 0; a < 3; ++a) {
                    int d = curDir[a];
                    x += sharkDX[d];
                    y += sharkDY[d];

                    if (x < 0 || x >= 4 || y < 0 || y >= 4) {
                        cnt = -1;
                        break;
                    }

                    cnt += temp[y][x].size();
                    temp[y][x].clear();
                }

                if (cnt > maxCnt) {
                    maxCnt = cnt;
                    ret = curDir;
                }
            }
        }
    }
    return ret;
}

void sharkGo() {
    vector<int> directions = getDirections();

    for (int i = 0; i < directions.size(); ++i) {
        int d = directions[i];
        sharkX += sharkDX[d];
        sharkY += sharkDY[d];

        if (board[sharkY][sharkX].size()) {
            scent[sharkY][sharkX] = 3;
            board[sharkY][sharkX].clear();
        }
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> M >> S;
    while (M--) {
        int x, y, d;
        cin >> y >> x >> d;
        prevBoard[--y][--x].push_back(--d);
    }
    int x, y;
    cin >> y >> x;
    sharkX = --x;
    sharkY = --y;

    int b = 1;
    while (S--) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < prevBoard[i][j].size(); ++k)
                    if (!go(j, i, prevBoard[i][j][k])) {
                        board[i][j].push_back(prevBoard[i][j][k]);
                    }
            }
        }
        
        sharkGo();
        removeScentAndBoardAppend();
    }

    int ans = 0;
    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            ans += prevBoard[i][j].size();
        }
    }

    cout << ans;

    return 0;
}