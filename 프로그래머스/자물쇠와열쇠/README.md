문제설명이 너무 잘못됐다.

자물쇠에 홈이 없는 경우 어떻게 작동하는지 설명하던가, 아니면 그런 케이스가 없다고 명시했어야 한다.

나는 자물쇠에 홈이 없는 경우 다음과 같이 생각했고, 이것 때문에 시간을 너무 지체했다.

```cpp
// 자물쇠에 홈이 없을때
if (locks[0].size() == 0) {
    // 키와 자물쇠에 사이즈가 맞지 않으면 false
    if (key.size() < lock.size())
        return false;
    // 키에 돌기가 하나라도 있으면 자물쇠와 맞지 않는다.
    for (int i = 0; i < key.size(); ++i) {
        for (int j = 0; j < key[0].size(); ++j)
            if (key[i][j])
                return false;
    }

    return true;
}
```
