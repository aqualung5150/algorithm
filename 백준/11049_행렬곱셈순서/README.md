```cpp
for (int k = 0; k < N; ++k) {
    for (int i = 0; i + k < N; ++i) {
        /* if (k == 0) {
                dp[i][i] == 초기값
            }
        */
        for (int j = i; j < i + k; ++j) {
            // 이 패턴을 잘 기억해두자.
        }
    }
}
```