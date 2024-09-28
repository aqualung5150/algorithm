#include <iostream>
#include <vector>
#include <stack>

using namespace std;

int lowerBound(vector<int> &arr, int target, int left, int right) {
    while (left < right) {
        int mid = (left + right) / 2;

        if (arr[mid] < target)
            left = mid + 1;
        else
            right = mid;
    }
    return right;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int N;
    cin >> N;

    vector<int> arr(N);
    vector<int> temp(N);
    vector<int> dp(N);

    for (int i = 0; i < N; ++i)
        cin >> arr[i];

    temp[0] = arr[0];
    dp[0] = 1;

    // set dp
    int idx = 0;
    int lastIdx = 0;
    for (int i = 1; i < N; ++i) {
        if (temp[idx] < arr[i]) {
            temp[++idx] = arr[i];
            dp[i] = idx + 1;
            lastIdx = i;
        } else {
            int insertTo = lowerBound(temp, arr[i], 0, idx);
            temp[insertTo] = arr[i];
            dp[i] = insertTo + 1;
        }
    }

    cout << idx + 1 << "\n";

    // find array
    stack<int> stk;
    int lastValue = arr[lastIdx];
    int maxLength = idx + 1;
    stk.push(lastValue);
    for (int i = lastIdx - 1; i >= 0; --i) {
        if (arr[i] < lastValue && dp[i] == maxLength - 1) {
            stk.push(arr[i]);
            lastValue = arr[i];
            --maxLength;
        }
    }

    while (!stk.empty()) {
        cout << stk.top() << " ";
        stk.pop();
    }

    return 0;
}