#include <iostream>
#include <vector>

using namespace std;

int N;
vector<vector<int>> board;
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, 1, 0, -1};

int clockX[] = {0, 1, 1, 1, 0, -1, -1, -1};
int clockY[] = {-1, -1, 0, 1, 1, 1, 0, -1};
int outCircle[] = {2, 0, 0, 0, 2, 0, 5, 0};
int inCircle[] = {7, 1, 0, 1, 7, 10, 0, 10};

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    board.resize(N, vector<int>(N));
    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            cin >> board[i][j];

    int ans = 0;
    int x = N / 2;
    int y = N / 2;
    int s = 1;
    int i = 0;
    while (x >= 0) {
        int dir = i % 4;
        for (int k = 0; k < s; ++k) {
            x = x + dx[dir];
            y = y + dy[dir];
            if (x < 0)
                break;

            // 모래 날리기
            int spreaded = 0;
            for (int j = 0; j < 8; ++j) {
                // 바깥
                int nx = x + clockX[j] * 2;
                int ny = y + clockY[j] * 2;
                int percent = outCircle[(j + dir * 2) % 8];
                int sand = (board[y][x] * percent) / 100;

                spreaded += sand;

                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    ans += sand;
                else
                    board[ny][nx] += sand;

                // 안
                nx = x + clockX[j];
                ny = y + clockY[j];
                percent = inCircle[(j + dir * 2) % 8];
                sand = (board[y][x] * percent) / 100;

                spreaded += sand;

                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    ans += sand;
                else
                    board[ny][nx] += sand;
            }
            // 알파 구역
            int ax = x + dx[dir];
            int ay = y + dy[dir];
            if (ax < 0 || ax >= N || ay < 0 || ay >= N)
                ans += board[y][x] - spreaded;
            else
                board[ay][ax] += board[y][x] - spreaded;
            
            board[y][x] = 0;
        }

        ++i;
        // 2턴마다 움직이는 거리 +1
        if (i % 2 == 0)
            ++s;
    }

    cout << ans;

    return 0;
}