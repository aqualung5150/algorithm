#include <iostream>
#include <vector>

using namespace std;

vector<vector<pair<int, int>>> board(4, vector<pair<int, int>>(4));
vector<vector<int>> fish(17); //{x, y, d}
int ans = 0;
int dx[] = {0, -1, -1, -1, 0, 1, 1, 1};
int dy[] = {-1, -1, 0, 1, 1, 1, 0, -1};
int sx = 0;
int sy = 0;
int sd;

vector<vector<pair<int, int>>> copy() {
    vector<vector<pair<int, int>>> ret(4, vector<pair<int, int>>(4));
    for (int i = 0; i < 4; ++i)
        for (int j = 0; j < 4; ++j)
            ret[i][j] = board[i][j];
    return ret;
}

void move() {
    for (int here = 1; here <= 16; ++here) {
        int x = fish[here][0];
        int y = fish[here][1];
        int d = fish[here][2];

        if (x < 0)
            continue;

        for (int i = 0; i < 8; ++i) {
            int nx = x + dx[(d + i) % 8];
            int ny = y + dy[(d + i) % 8];

            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || (nx == sx && ny == sy))
                continue;
            
            d = (d + i) % 8;
            int there = board[ny][nx].first;

            if (!there) {
                // empty space
                fish[here][0] = nx;
                fish[here][1] = ny;
                fish[here][2] = d;

                board[ny][nx] = {here, d};
                board[y][x] = {0, 0};
            } else {
                // swap fish
                fish[here][0] = nx;
                fish[here][1] = ny;
                fish[here][2] = d;

                fish[there][0] = x;
                fish[there][1] = y;
                // swap board
                board[ny][nx] = {here, d};
                board[y][x] = {there, fish[there][2]};
            }
            break;
        }
    }
}

void back(vector<vector<pair<int, int>>> prev) {
    for (int i = 0; i < 4; ++i) {
        for (int j = 0; j < 4; ++j) {
            int cur = prev[i][j].first;
            int dir = prev[i][j].second;

            if (cur == 0)
                continue;

            fish[cur] = {j, i, dir};
        }
    }
}

void dfs(int sum) {
    vector<vector<pair<int, int>>> prev = copy();
    move();

    int x = sx;
    int y = sy;
    int d = sd;
    int i = 1;
    while (1) {
        int nx = x + dx[d] * i;
        int ny = y + dy[d] * i;
        ++i;
        // out bound
        if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
            ans = max(ans, sum);
            break;
        }
        // no fish
        if (!board[ny][nx].first)
            continue;

        // go next
        pair<int, int> eaten = board[ny][nx];
        board[ny][nx] = {0, 0};
        fish[eaten.first][0] = -1;
        fish[eaten.first][1] = -1;
        sx = nx;
        sy = ny;
        sd = eaten.second;
        dfs(sum + eaten.first);
        // go back
        board[ny][nx] = eaten;
        fish[eaten.first][0] = nx;
        fish[eaten.first][1] = ny;
        sx = x;
        sy = y;
        sd = d;
    }

    // reset
    back(prev);
    board = prev;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    fish[0] = {0, 0, 0};

    for (int i = 0; i < 4; ++i)
        for (int j = 0; j < 4; ++j) {
            int n, d;
            cin >> n >> d;
            --d;
            board[i][j] = {n, d};
            fish[n] = {j, i, d};
        }

    int eaten = board[0][0].first;
    sd = board[0][0].second;
    fish[eaten] = {-1, -1, -1};
    board[0][0] = {0, 0};
    dfs(eaten);

    cout << ans;

    return 0;
}