#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int N, K, L;
    cin >> N >> K;
    vector<vector<int>> board(N + 1, vector<int>(N + 1));
    
    while (K--) {
        int x, y;
        cin >> y >> x;
        board[y][x] = 2;
    }

    cin >> L;
    unordered_map<int, int> direction;
    while (L--) {
        int t;
        char d;
        cin >> t >> d;
        if (d == 'L')
            direction[t] = 3;
        else
            direction[t] = 1;
    }

    int ans = 0;
    int hx = 1;
    int hy = 1;
    int tx = 1;
    int ty = 1;
    int dx[] = {1, 0, -1, 0};
    int dy[] = {0, 1, 0, -1};
    int hd = 0;
    int td = 0;
    int len = 0;
    board[1][1] = 1;
    while (1) {
        ++ans;
        hx += dx[hd];
        hy += dy[hd];

        if (hx < 1 || hx > N || hy < 1 || hy > N || board[hy][hx] == 1) {
            cout << ans;
            return 0;
        }

        if (board[hy][hx] == 2) {
            ++len;
        } else {
            board[ty][tx] = 0;
            tx += dx[td];
            ty += dy[td];
            if (direction[ans - len])
                td = (td + direction[ans - len]) % 4;
        }

        board[hy][hx] = 1;

        if (direction[ans])
            hd = (hd + direction[ans]) % 4;
    }
    
    return 0;
}