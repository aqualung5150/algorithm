#include <iostream>
#include <vector>

using namespace std;

struct Coord {
    int x;
    int y;
};

struct Heater {
    int x;
    int y;
    int dir;
};

int R, C, K, W;
vector<vector<int>> board;
vector<vector<vector<bool>>> wall; // {right, left, top, bottom}
vector<Coord> testTarget;
vector<Heater> heater;

int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, -1, 1};

void heaterGo(vector<vector<bool>> &visited, int x, int y, int d, int k) {
    if (k == 0)
        return;

    if (d == 0) {// right
        // forward
        int nx = x + 1;
        int ny = y;

        if (!(nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || wall[y][x][0])) {
            visited[ny][nx] = true;
            board[ny][nx] += k;
            heaterGo(visited, nx, ny, d, k - 1);
        }
        // forward left
        nx = x + 1;
        ny = y - 1;

        if (!(nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || wall[y - 1][x][0] || wall[y - 1][x][3])) {
            visited[ny][nx] = true;
            board[ny][nx] += k;
            heaterGo(visited, nx, ny, d, k - 1);
        }

        // forward right
        nx = x + 1;
        ny = y + 1;

        if (!(nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || wall[y + 1][x][0] || wall[y + 1][x][2])) {
            visited[ny][nx] = true;
            board[ny][nx] += k;
            heaterGo(visited, nx, ny, d, k - 1);
        }
    }

    if (d == 1) {// left
        // forward
        int nx = x - 1;
        int ny = y;

        if (!(nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || wall[y][x][1])) {
            visited[ny][nx] = true;
            board[ny][nx] += k;
            heaterGo(visited, nx, ny, d, k - 1);
        }
        // forward left
        nx = x - 1;
        ny = y + 1;

        if (!(nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || wall[y + 1][x][1] || wall[y + 1][x][2])) {
            visited[ny][nx] = true;
            board[ny][nx] += k;
            heaterGo(visited, nx, ny, d, k - 1);
        }

        // forward right
        nx = x - 1;
        ny = y - 1;

        if (!(nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || wall[y - 1][x][1] || wall[y - 1][x][3])) {
            visited[ny][nx] = true;
            board[ny][nx] += k;
            heaterGo(visited, nx, ny, d, k - 1);
        }
    }

    if (d == 2) {// up
        // forward
        int nx = x;
        int ny = y - 1;

        if (!(nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || wall[y][x][2])) {
            visited[ny][nx] = true;
            board[ny][nx] += k;
            heaterGo(visited, nx, ny, d, k - 1);
        }
        // forward left
        nx = x - 1;
        ny = y - 1;

        if (!(nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || wall[y][x - 1][2] || wall[y][x - 1][0])) {
            visited[ny][nx] = true;
            board[ny][nx] += k;
            heaterGo(visited, nx, ny, d, k - 1);
        }

        // forward right
        nx = x + 1;
        ny = y - 1;

        if (!(nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || wall[y][x + 1][2] || wall[y][x + 1][1])) {
            visited[ny][nx] = true;
            board[ny][nx] += k;
            heaterGo(visited, nx, ny, d, k - 1);
        }
    }

    if (d == 3) {// down
        // forward
        int nx = x;
        int ny = y + 1;

        if (!(nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || wall[y][x][3])) {
            visited[ny][nx] = true;
            board[ny][nx] += k;
            heaterGo(visited, nx, ny, d, k - 1);
        }
        // forward left
        nx = x + 1;
        ny = y + 1;

        if (!(nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || wall[y][x + 1][3] || wall[y][x + 1][1])) {
            visited[ny][nx] = true;
            board[ny][nx] += k;
            heaterGo(visited, nx, ny, d, k - 1);
        }

        // forward right
        nx = x - 1;
        ny = y + 1;

        if (!(nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || wall[y][x - 1][3] || wall[y][x - 1][0])) {
            visited[ny][nx] = true;
            board[ny][nx] += k;
            heaterGo(visited, nx, ny, d, k - 1);
        }
    }
}

void init() {
    cin >> R >> C >> K;
    board.resize(R, vector<int>(C));
    wall.resize(R, vector<vector<bool>>(C, vector<bool>(4)));
    for (int i = 0; i < R; ++i) {
        for (int j = 0; j < C; ++j) {
            int input;
            cin >> input;

            if (input == 5)
                testTarget.push_back({j, i});
            else if (input > 0) {
                int nx = j + dx[input - 1];
                int ny = i + dy[input - 1];
                heater.push_back({nx, ny, input - 1});
            }
        }
    }

    cin >> W;
    while (W--) {
        int x, y, d;
        cin >> y >> x >> d;
        --x; --y;
        if (d == 0) {
            // up
            wall[y][x][2] = true;
            wall[y - 1][x][3] = true;
        } else {
            //right
            wall[y][x][0] = true;
            wall[y][x + 1][1] = true;
        }
    }
}

void setTemp() {
    vector<vector<int>> tmp(R, vector<int>(C));
    for (int i = 0; i < R; ++i) {
        for (int j = 0; j < C; ++j) {
            tmp[i][j] += board[i][j];
            for (int d = 0; d < 4; ++d) {
                int nx = j + dx[d];
                int ny = i + dy[d];

                if (nx < 0 || nx >= C || ny < 0 || ny >= R || wall[i][j][d] || board[i][j] <= board[ny][nx])
                    continue;
                
                int diff = (board[i][j] - board[ny][nx]) / 4;
                tmp[ny][nx] += diff;
                tmp[i][j] -= diff;
            }
        }
    }

    board = tmp;

    for (int i = 0; i < R; ++i) {
        if (board[i][0] > 0)
            --board[i][0];
        if (board[i][C - 1] > 0)
            --board[i][C - 1];
    }

    for (int i = 1; i < C - 1; ++i) {
        if (board[0][i] > 0)
            --board[0][i];
        if (board[R - 1][i] > 0)
            --board[R - 1][i];
    }
}

bool finishCheck() {
    for (auto t : testTarget) {
        if (board[t.y][t.x] < K)
            return false;
    }
    return true;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    init();

    int ans = 0;
    while (ans <= 100) {
        for (auto h : heater) {
            vector<vector<bool>> visited(R, vector<bool>(C));
            board[h.y][h.x] += 5;
            heaterGo(visited, h.x, h.y, h.dir, 4);
        }
        setTemp();
        ++ans;
        if (finishCheck())
            break;
    }

    cout << ans;

    return 0;
}