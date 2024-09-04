#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> dice(10);
vector<int> shortcut(41);
vector<pair<int, bool>> status(4, {0, false}); // {말 위치, 지름길}
int ans = 0;

void dfs(int idx, int sum) {
    if (idx == 10) {
        ans = max(ans, sum);
    } else {
        for (int i = 0; i < 4; ++i) {
            int d = dice[idx];
            int coord = status[i].first;
            bool isShortcut = status[i].second;
            int next = coord;
            bool nextIsShortcut = false;
            if (coord == -1)
                continue;
            if (coord != 10 && coord != 20 && coord != 30 && !isShortcut) {
                next += d * 2;
                if (next > 40)
                    next = -1;
            } else {
                if (coord == 30 && !isShortcut) {
                    next = 28;
                    --d;
                }
                while (d--) {
                    next = shortcut[next];
                    if (next == -1)
                        break;
                }
                nextIsShortcut = true;
            }

            if (next == -1) {
                status[i] = {next, nextIsShortcut};
                dfs(idx + 1, sum);
                status[i] = {coord, isShortcut};
            } else {
                bool isValid = true;
                for (auto s : status) {
                    if (next == 40 && s.first == 40) {
                        isValid = false;
                        break;
                    }
                    if (s.first == next && s.second == nextIsShortcut) {
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    status[i] = {next, nextIsShortcut};
                    dfs(idx + 1, sum + next);
                    status[i] = {coord, isShortcut};
                }
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    shortcut[10] = 13;
    shortcut[13] = 16;
    shortcut[16] = 19;
    shortcut[19] = 25;
    shortcut[20] = 22;
    shortcut[22] = 24;
    shortcut[24] = 25;
    shortcut[25] = 30;
    shortcut[26] = 25;
    shortcut[27] = 26;
    shortcut[28] = 27;
    shortcut[30] = 35;
    shortcut[35] = 40;
    shortcut[40] = -1;

    for (int i = 0; i < 10; ++i)
        cin >> dice[i];

    dfs(0, 0);

    cout << ans;

    return 0;
}