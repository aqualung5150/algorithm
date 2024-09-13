#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct coord {
    int x;
    int y;
};

int N, M;
int ans = 0;
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};
vector<vector<int>> board;
vector<coord> biggest;
int biggestRainbow = 0;

void bfs(vector<vector<bool>> &visited, int sx, int sy) {
    int color = board[sy][sx];
    vector<coord> group;
    vector<coord> rainbowCoord;
    queue<coord> q;
    q.push({sx, sy});
    visited[sy][sx] = true;

    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;
        q.pop();

        group.push_back({x, y});
        if (board[y][x] == 0)
            rainbowCoord.push_back({x, y});

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx] || (board[ny][nx] != 0 && board[ny][nx] != color))
                continue;
            
            q.push({nx, ny});
            visited[ny][nx] = true;
        }
    }
    // 무지개 블록 visited 초기화
    for (coord r : rainbowCoord)
        visited[r.y][r.x] = false;

    // 가장 큰 블록
    if (group.size() >= 2) {
        if (group.size() > biggest.size()) {
            biggest = group;
            biggestRainbow = rainbowCoord.size();
        } else if (group.size() == biggest.size()) {
            if (rainbowCoord.size() >= biggestRainbow) {
                biggest = group;
                biggestRainbow = rainbowCoord.size();
            }
        }
    }
}

void setBlockGroup() {
    // blockGroup.clear();
    biggest.clear();
    biggestRainbow = 0;
    vector<vector<bool>> visited(N, vector<bool>(N));

    // set blockGroup
    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j) {
            if (visited[i][j] || board[i][j] < 1)
                continue;
            bfs(visited, j, i);
        }
}

bool deleteBlockGroup() {
    int size = biggest.size();

    if (size < 2)
        return false;

    ans += size * size;
    for (coord c : biggest)
        board[c.y][c.x] = -2;

    return true;
}

void gravity() {
    for (int x = 0; x < N; ++x) {
        for (int y = N - 2; y >= 0; --y) {
            if (board[y][x] < 0)
                continue;

            int d = 0;
            while (y + 1 + d < N && board[y + 1 + d][x] == -2)
                ++d;

            if (!d)
                continue;
            
            board[y + d][x] = board[y][x];
            board[y][x] = -2;
        }
    }
}

void rotateBoard() {
    vector<vector<int>> temp(N, vector<int>(N));

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            temp[N - 1 - j][i] = board[i][j];
        }
    }

    board = temp;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M;
    board.resize(N, vector<int>(N));
    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            cin >> board[i][j];

    while (1) {
        setBlockGroup();
        if (!deleteBlockGroup()) {
            cout << ans;
            return 0;
        }
        gravity();
        rotateBoard();
        gravity();
    }

    return 0;
}