#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

int r, c, k;
vector<vector<int>> board(3, vector<int>(3));

bool cmp(const pair<int, int> &a, const pair<int, int> &b) {
    if (a.second == b.second)
        return a.first < b.first;
    return a.second < b.second;
}

vector<vector<int>> getSorted() {
    vector<vector<int>> ret;
    int len = 0;

    for (int i = 0; i < board.size(); ++i) {
        unordered_map<int, int> m;

        for (int j = 0; j < board[0].size(); ++j) {
            int cur = board[i][j];
            if (cur) {
                if (m[cur])
                    ++m[cur];
                else
                    m[cur] = 1;
            }
        }

        vector<pair<int, int>> tmp(m.begin(), m.end());
        sort(tmp.begin(), tmp.end(), cmp);

        vector<int> v;
        for (auto i : tmp) {
            if (v.size() == 100)
                break;
            v.push_back(i.first);
            v.push_back(i.second);
        }
        ret.push_back(v);
        len = max(len, (int)v.size());
    }

    for (int i = 0; i < ret.size(); ++i)
        for (int j = ret[i].size(); j < len; ++j)
            ret[i].push_back(0);

    return ret;
}

vector<vector<int>> rotateBoard(int dir) {
    vector<vector<int>> ret(board[0].size(), vector<int>(board.size()));

    if (dir == 0) {
        for (int i = 0; i < ret.size(); ++i)
            for (int j = 0; j < ret[0].size(); ++j)
                ret[i][j] = board[j][board[0].size() - 1 - i];
    } else {
        for (int i = 0; i < ret.size(); ++i)
            for (int j = 0; j < ret[0].size(); ++j)
                ret[i][j] = board[board.size() - 1 - j][i];
    }

    return ret;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> r >> c >> k;
    --r; --c;

    for (int i = 0; i < 3; ++i)
        for (int j = 0; j < 3; ++j)
            cin >> board[i][j];

    int ans = 0;
    while (1) {
        if (ans > 100) {
            cout << -1;
            break;
        } else if (board.size() > r && board[0].size() > c && board[r][c] == k) {
            cout << ans;
            break;
        }

        if (board[0].size() <= board.size()) {
            // R연산
            // 정렬
            board = getSorted();
        } else {
            // C연산
            // 반시계->정렬->시계
            board = rotateBoard(0);
            board = getSorted();
            board = rotateBoard(1);
        }

        ++ans;
    }

    return 0;
}