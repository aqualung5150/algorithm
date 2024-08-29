#include <iostream>
#include <vector>

using namespace std;

int dx[] = {1, 0, -1, 0};
int dy[] = {0, -1, 0, 1};
int N;
vector<vector<bool>> board(101, vector<bool>(101));

void draw(int x, int y, int d, int g) {
    vector<int> dir;
    dir.push_back(d);

    for (int i = 1; i <= g; ++i)
        for (int j = dir.size() - 1; j >= 0; --j)
            dir.push_back((dir[j] + 1) % 4);

    board[y][x] = true;
    for (int d : dir) {
        x += dx[d];
        y += dy[d];
        board[y][x] = true;
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    while (N--) {
        int x, y, d, g;
        cin >> x >> y >> d >> g;
        draw(x, y, d, g);
    }

    int ans = 0;
    for (int i = 0; i < 100; ++i)
        for (int j = 0; j < 100; ++j)
            if (board[i][j] && board[i][j + 1] && board[i + 1][j] && board[i + 1][j + 1])
                ++ans;

    cout << ans;

    return 0;
}