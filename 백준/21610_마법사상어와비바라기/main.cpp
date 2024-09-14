#include <iostream>
#include <vector>

using namespace std;

struct coord {
    int x;
    int y;
};

int N, M;
vector<vector<int>> board;
vector<vector<bool>> prevCloud;
vector<coord> cloud;
vector<int> dx;
vector<int> dy;
int total = 0;
int dx1[] = {1, 1, -1, -1};
int dy1[] = {1, -1, 1, -1};

void moveAndRain(int d, int s) {
    for (auto &c : cloud) {
        c.x = (c.x + dx[d] * s) % N;
        c.y = (c.y + dy[d] * s) % N;
        ++total;
        ++board[c.y][c.x];
        prevCloud[c.y][c.x] = true;
    }
}

void gatherWater() {
    for (auto c : cloud) {
        for (int i = 0; i < 4; ++i) {
            int nx = c.x + dx1[i];
            int ny = c.y + dy1[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || !board[ny][nx])
                continue;

            ++total;
            ++board[c.y][c.x];
        }
    }
}

void setCloud() {
    cloud.clear();

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            if (prevCloud[i][j])
                prevCloud[i][j] = false;
            else if (board[i][j] >= 2) {
                cloud.push_back({j, i});
                total -= 2;
                board[i][j] -= 2;
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M;
    board.resize(N, vector<int>(N));
    prevCloud.resize(N, vector<bool>(N));
    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j) {
            cin >> board[i][j];
            total += board[i][j];
        }
    
    dx = {N - 1, N - 1, 0, 1, 1, 1, 0, N - 1};
    dy = {0, N - 1, N - 1, N - 1, 0, 1, 1, 1};
    cloud = {{0, N - 1}, {1, N - 1}, {0, N - 2}, {1, N - 2}};

    while (M--) {
        int d, s;
        cin >> d >> s;

        moveAndRain(d - 1, s);
        gatherWater();
        setCloud();
    }

    cout << total;

    return 0;
}