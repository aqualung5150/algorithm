#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<int> v;
vector<int> op;
vector<bool> visited;
int ans_max = -1000000000;
int ans_min = 1000000000;

void dfs(int idx, int count) {
    if (idx == N - 1) {
        ans_max = max(ans_max, count);
        ans_min = min(ans_min, count);
    } else {
        for (int i = 0; i < op.size(); ++i) {
            if (visited[i])
                continue;
            
            visited[i] = true;

            switch (op[i]) {
                case 0:
                    dfs(idx + 1, count + v[idx + 1]);
                    break;
                case 1:
                    dfs(idx + 1, count - v[idx + 1]);
                    break;
                case 2:
                    dfs(idx + 1, count * v[idx + 1]);
                    break;
                case 3:
                    dfs(idx + 1, count / v[idx + 1]);
                    break;
            }

            visited[i] = false;
        }
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;

    for (int i = 0; i < N; ++i) {
        int n;
        cin >> n;
        v.push_back(n);
    }

    for (int i = 0; i < 4; ++i) {
        int input;
        cin >> input;
        while (input--)
            op.push_back(i);
    }

    visited.resize(N - 1);

    dfs(0, v[0]);

    cout << ans_max << "\n" << ans_min;

    return 0;
}