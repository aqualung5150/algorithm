#include <iostream>
#include <vector>

using namespace std;

int n;
vector<int> v;
vector<int> dp;

int lowerBound(int target, int left, int right) {
    while (left < right) {
        int mid = (left + right) / 2;

        if (dp[mid] < target)
            left = mid + 1;
        else
            right = mid;
    }
    return right;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> n;
    for (int i = 0; i < n; ++i) {
        int input;
        cin >> input;
        v.push_back(input);
    }
    
    dp.push_back(v[0]);

    int lastIndexOfDp = 0;
    for (int i = 0; i < n; ++i) {
        if (dp[lastIndexOfDp] < v[i]) {
            dp.push_back(v[i]);
            ++lastIndexOfDp;
        }
        else
            dp[lowerBound(v[i], 0, lastIndexOfDp)] = v[i];
    }

    cout << dp.size();

    return 0;
}