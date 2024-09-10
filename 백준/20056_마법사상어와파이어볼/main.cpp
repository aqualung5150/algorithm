#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M, K;
vector<vector<queue<vector<int>>>> board; // {m, s, d}
vector<vector<int>> qSize;
vector<int> dx;
vector<int> dy;
int ans = 0;

void move() {
    for (int y = 0; y < N; ++y) {
        for (int x = 0; x < N; ++x) {
            queue<vector<int>> &q = board[y][x];
            for (int i = 0; i < qSize[y][x]; ++i) {
                int m = q.front()[0];
                int s = q.front()[1];
                int d = q.front()[2];
                q.pop();

                int nx = (x + dx[d] * s) % N;
                int ny = (y + dy[d] * s) % N;

                board[ny][nx].push({m, s, d});
            }
        }
    }
}

void split() {
    for (int y = 0; y < N; ++y) {
        for (int x = 0; x < N; ++x) {
            queue<vector<int>> &q = board[y][x];
            qSize[y][x] = q.size();

            if (q.size() < 2)
                continue;
            
            int m_sum = 0;
            int s_sum = 0;
            int odd = q.front()[2] % 2;
            bool flag = true;
            while (!q.empty()) {
                m_sum += q.front()[0];
                s_sum += q.front()[1];
                if (q.front()[2] % 2 != odd)
                    flag = false;
                q.pop();
            }

            int m = m_sum / 5;
            int s = s_sum / qSize[y][x];
            int d = flag ? 0 : 1;
            if (m) {
                for (; d < 8; d += 2)
                q.push({m, s, d});
            }

            qSize[y][x] = q.size();

            ans -= m_sum;
            ans += m * 4;
        }
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M >> K;
    board.resize(N, vector<queue<vector<int>>>(N));
    qSize.resize(N, vector<int>(N));

    dx = {0, 1, 1, 1, 0, N - 1, N - 1, N - 1};
    dy = {N - 1, N - 1, 0, 1, 1, 1, 0, N - 1};

    while (M--) {
        int x, y, m, s, d;
        cin >> y >> x >> m >> s >> d;

        ans += m;
        board[y - 1][x - 1].push({m, s, d});
        ++qSize[y - 1][x - 1];
    }

    while (K--) {
        move();
        split();
    }

    cout << ans;

    return 0;
}