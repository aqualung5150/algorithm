#include <iostream>

using namespace std;

int lowerBound(int arr[], int target, int left, int right) {
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

    int arr[200];
    int dp[200];
    for (int i = 0; i < N; ++i)
        cin >> arr[i];

    dp[0] = arr[0];
    
    int dp_idx = 0;
    for (int i = 1; i < N; ++i) {
        if (dp[dp_idx] < arr[i]) {
            dp[++dp_idx] = arr[i];
        } else {
            dp[lowerBound(dp, arr[i], 0, dp_idx)] = arr[i];
        }
    }

    cout << N - dp_idx - 1;

    return 0;
}