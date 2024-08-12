```cpp
// 틀린 코드
bool isPossible(vector<int> &stones, int mid, int k) {
    int count = 0;
    for (int i = 0; i < stones.size(); ++i) {
        if (stones[i] <= mid)
            ++count;
        else
            count = 0;

        if (count > k)
            return false;
    }
    return true;
}
```

```cpp
// 올바른 코드
bool isPossible(vector<int> &stones, int mid, int k) {
    int count = 0;
    for (int i = 0; i < stones.size(); ++i) {
        if (stones[i] < mid)
            ++count;
        else
            count = 0;

        if (count >= k)
            return false;
    }
    return true;
}
```

`555321555`, `k = 3` 징검다리가 있을때 `mid = 3`인 경우만 `true`가 나와야 하지만 틀린코드는 `mid = 4`일때도 통과해버린다.
