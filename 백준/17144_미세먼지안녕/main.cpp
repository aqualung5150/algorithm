#include <iostream>
#include <vector>

using namespace std;

int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};
int R, C, T;
vector<vector<int>> board;
vector<int> ac; // {t_y, b_y}

void spread() {
    vector<vector<int>> sum(R, vector<int>(C));

    for (int y = 0; y < R; ++y) {
        for (int x = 0; x < C; ++x) {
            if (board[y][x] < 5)
                continue;

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= C || ny < 0 || ny >= R || board[ny][nx] == -1)
                    continue;
                
                sum[ny][nx] += board[y][x] / 5;
                sum[y][x] -= board[y][x] / 5;
            }
        }
    }

    for (int y = 0; y < R; ++y)
        for (int x = 0; x < C; ++x)
            board[y][x] += sum[y][x];
}

void fresh() {
    // top
    int tx_dir[] = {0, 1, 0, -1};
    int ty_dir[] = {-1, 0, 1, 0};
    int x = 0;
    int y = ac[0] - 1;

    for (int i = 0; i < 4; ++i) {
        while (1) {
            int nx = x + tx_dir[i];
            int ny = y + ty_dir[i];

            if (nx >= C || ny < 0 || ny > ac[0])
                break;
            
            if (board[ny][nx] == -1) {
                board[y][x] = 0;
                break;
            }
            
            board[y][x] = board[ny][nx];
            x = nx;
            y = ny;
        }
    }

    // bottom
    int bx_dir[] = {0, 1, 0, -1};
    int by_dir[] = {1, 0, -1, 0};
    x = 0;
    y = ac[1] + 1;

    for (int i = 0; i < 4; ++i) {
        while (1) {
            int nx = x + bx_dir[i];
            int ny = y + by_dir[i];

            if (nx >= C || ny >= R || ny < ac[1])
                break;
            
            if (board[ny][nx] == -1) {
                board[y][x] = 0;
                break;
            }
            
            board[y][x] = board[ny][nx];
            x = nx;
            y = ny;
        }
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    
    cin >> R >> C >> T;
    board.resize(R, vector<int>(C));
    for (int i = 0; i < R; ++i)
        for (int j = 0; j < C; ++j) {
            cin >> board[i][j];
            if (board[i][j] == -1)
                ac.push_back(i);
        }

    while (T--) {
        spread();
        fresh();
    }

    int ans = 0;
    for (int i = 0; i < R; ++i)
        for (int j = 0; j < C; ++j)
            if (board[i][j] != -1)
                ans += board[i][j];

    cout << ans;

    return 0;
}