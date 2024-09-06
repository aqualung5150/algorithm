#include <iostream>
#include <vector>

using namespace std;

struct s_shark {
    int x;
    int y;
    int d;
    vector<vector<int>> p;
};

int N, M, k;
vector<vector<int>> board;
vector<vector<int>> scent;
vector<s_shark> shark;

int total;
int dx[] = {0, 0, -1, 1};
int dy[] = {-1, 1, 0, 0};

void removeScent() {
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            if (scent[i][j]) {
                --scent[i][j];
                if (!scent[i][j])
                    board[i][j] = 0;
            }
        }
    }
}

void addScent() {
    for (int i = 1; i <= M; ++i) {
        int x = shark[i].x;
        int y = shark[i].y;

        if (x < 0)
            continue;

        scent[y][x] = k;
        // removeScent()에서 이동한 위치(board[y][x])가 지워졌을 수 있음 (line.28).
        board[y][x] = i;
    }
}

void move() {
    for (int s = 1; s <= M; ++s) {
        int x = shark[s].x;
        int y = shark[s].y;
        vector<int> dir = shark[s].p[shark[s].d]; // 다음 방향 인덱스

        // 쫓겨난 상어
        if (x < 0)
            continue;
        
        bool isMoved = false;
        // 빈 공간 탐색
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[dir[i]];
            int ny = y + dy[dir[i]];

            // 다른 상어의 냄새가 있는 경우
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || (board[ny][nx] && scent[ny][nx]))
                continue;

            isMoved = true;

            // 이번 턴에 다른 상어가 같은 곳으로 이동했을 경우
            if (board[ny][nx]) {
                --total;
                int other = board[ny][nx];
                if (other < s) {
                    // 큰 상어
                    shark[s].x = -1;
                    shark[s].y = -1;
                    break;
                } else if (other > s) {
                    // 작은 상어
                    shark[other].x = -1;
                    shark[other].y = -1;
                }
            }
            
            board[ny][nx] = s;
            shark[s].x = nx;
            shark[s].y = ny;
            shark[s].d = dir[i];
            break;
        }

        if (isMoved)
            continue;

        // 자신의 냄새 탐색
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[dir[i]];
            int ny = y + dy[dir[i]];

            // 다른 상어의 냄새가 있는 경우
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[ny][nx] != s)
                continue;
            
            board[ny][nx] = s;
            shark[s].x = nx;
            shark[s].y = ny;
            shark[s].d = dir[i];
            break;
        }
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M >> k;
    total = M;
    board.resize(N, vector<int>(N));
    scent.resize(N, vector<int>(N));
    shark.resize(M + 1);
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            cin >> board[i][j];
            if (board[i][j]) {
                scent[i][j] = k;
                shark[board[i][j]].x = j;
                shark[board[i][j]].y = i;
            }
        }
    }
    // 첫 방향
    for (int i = 1; i <= M; ++i) {
        cin >> shark[i].d;
        --shark[i].d;
    }
    // 방향 우선순위
    for (int s = 1; s <= M; ++s) {
        vector<vector<int>> p(4, vector<int>(4));
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 4; ++j) {
                cin >> p[i][j];
                --p[i][j];
            }
        shark[s].p = p;
    }

    int ans = 0;
    while (ans <= 1000) {
        if (total == 1) {
            cout << ans;
            return 0;
        }
        
        move();
        removeScent();
        addScent();
        ++ans;
    }

    cout << -1;

    return 0;
}