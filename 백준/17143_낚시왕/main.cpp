#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int R, C, M;
vector<vector<int>> sharks; // {x, y, size, speed, direction}
int dx[] = {0, 0, 1, -1};
int dy[] = {-1, 1, 0, 0};

bool cmp(const vector<int> &a, const vector<int> &b) {
    if (a[0] == b[0] && a[1] == b[1])
        return a[2] > b[2];
    if (a[0] == b[0])
        return a[1] < b[1];
    return a[0] < b[0];
}

int x_search(int x) {
    int left = 0;
    int right = sharks.size() - 1;
    int ret = -1;
    while (left <= right) {
        int mid = (left + right) / 2;

        if (sharks[mid][0] < x)
            left = mid + 1;
        else if (sharks[mid][0] > x)
            right = mid - 1;
        else {
            ret = mid;
            right = mid - 1;
        }
    }

    return ret;
}

void move() {
    for (auto &s : sharks) {
        int x = s[0];
        int y = s[1];
        int dir = s[4];
        int spd = s[3];

        if (dir == 2 || dir == 3) {
            // x축
            // 왕복
            spd %= (C - 1) * 2;

            for (int i = 0; i < spd; ++i) {
                x += dx[dir];
                if (x < 0 || x >= C) {
                    if (dir == 2)
                        dir = 3;
                    else
                        dir = 2;
                    x += dx[dir] * 2;
                }
            }

            s[0] = x;
            s[4] = dir;
        } else {
            // y축
            // 왕복
            spd %= (R - 1) * 2;

            for (int i = 0; i < spd; ++i) {
                y += dy[dir];
                if (y < 0 || y >= R) {
                    if (dir == 0)
                        dir = 1;
                    else
                        dir = 0;
                    y += dy[dir] * 2;
                }
            }

            s[1] = y;
            s[4] = dir;
        }
    }
}

void eat() {
    for (int i = 0; i < sharks.size() - 1; ++i) {
        int x = sharks[i][0];
        int y = sharks[i][1];
        int nx = sharks[i + 1][0];
        int ny = sharks[i + 1][1];

        if (x == nx && y == ny) {
            auto it = sharks.erase(sharks.begin() + i + 1);
            while (it != sharks.end()) {
                vector<int> &cur = *it;

                if (cur[0] == x && cur[1] == y) {
                    it = sharks.erase(it);
                } else
                    break;
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> R >> C >> M;
    for (int i = 0; i < M; ++i) {
        int r, c, s, d, z;
        cin >> r >> c >> s >> d >> z;

        sharks.push_back({c - 1, r - 1, z, s, d - 1});
    }

    int ans = 0;
    for (int i = 0; i < C && sharks.size() > 0; ++i) {
        sort(sharks.begin(), sharks.end(), cmp);
        eat();

        int found = x_search(i);

        if (found != -1) {
            ans += sharks[found][2];
            int x = sharks[found][0];
            int y = sharks[found][1];
            // 잡은 상어 제거
            sharks.erase(sharks.begin() + found);
        }
        move();
    }

    cout << ans;

    return 0;
}