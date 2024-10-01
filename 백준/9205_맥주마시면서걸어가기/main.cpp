#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>

using namespace std;

struct Point {
    int x;
    int y;
};

Point cur;
Point dest;

int getDist(Point &a, Point &b) {
    return abs(a.x - b.x) + abs(a.y - b.y);
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N;
        cin >> N;

        int sx, sy;
        cin >> sx >> sy;
        cur = {sx, sy};

        vector<Point> conv(N);
        for (int i = 0; i < N; ++i) {
            int cx, cy;
            cin >> cx >> cy;
            conv[i] = {cx, cy};
        }

        int dx, dy;
        cin >> dx >> dy;
        dest = {dx, dy};

        vector<bool> visited(N);

        queue<Point> q;
        q.push(cur);
        
        bool isHappy = false;
        while (!q.empty()) {
            Point here = q.front();
            q.pop();

            if (getDist(here, dest) <= 1000) {
                isHappy = true;
                break;
            }

            for (int i = 0; i < N; ++i) {
                if (visited[i])
                    continue;

                if (getDist(here, conv[i]) <= 1000) {
                    q.push(conv[i]);
                    visited[i] = true;
                }
            }
        }

        if (isHappy)
            cout << "happy\n";
        else
            cout << "sad\n";
    }

    return 0;
}