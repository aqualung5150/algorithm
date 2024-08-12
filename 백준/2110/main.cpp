#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int N, C;
    cin >> N >> C;

    vector<int> v(N);
    for (int i = 0; i < N; ++i)
        cin >> v[i];

    sort(v.begin(), v.end());

    int left = 1;
    int right = (v[v.size() - 1] - v[0]) / (C - 1);

    int ans = 0;
    while (left <= right) {
        int mid = (left + right) / 2;

        int count = 1;
        int cur = 0;
        for (int i = 0; i < v.size(); ++i) {
            if (v[i] - v[cur] >= mid) {
                ++count;
                cur = i;
            }
        }

        if (count >= C) {
            ans = max(ans, mid);
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    cout << ans;

    return 0;
}