### 틀린 풀이

```cpp
#include <string>
#include <vector>
#include <queue>
#include <sstream>

using namespace std;

vector<int> solution(vector<string> operations) {
    priority_queue<int> pq_desc;
    priority_queue<int> pq_asc;

    int count = 0;

    for (int i = 0; i < operations.size(); ++i) {
        istringstream iss(operations[i]);
        string op, num;
        iss >> op >> num;
        int n = stoi(num);

        if (op == "I") {
            pq_desc.push(n);
            pq_asc.push(-n);
            ++count;
        } else if (n == 1 && count) {
            pq_desc.pop();
            --count;
        } else if (count) {
            pq_asc.pop();
            --count;
        }

        if (!count) {
            while (!pq_desc.empty())
                pq_desc.pop();
            while (!pq_asc.empty())
                pq_asc.pop();
        }
    }

    vector<int> ans;
    if (count) {
        ans.push_back(pq_desc.top());
        ans.push_back(-pq_asc.top());
    } else {
        ans.push_back(0);
        ans.push_back(0);
    }
    return ans;
}
```

이중우선순위큐에 남아있는 요소개수(count)가 0개 일 때 큐를 모두 비워주는 방법으로 풀면 안되는 이유
-> 반례: `["I 1", "I 2", "I 3", "D 1", "D 1", "I 3", "I 4", "I 5", "D -1", "D -1"]`

첫번째 `D -1`은 `1`을 삭제하고 두번째 `D -1`은 `3`을 삭제하는 대신 `2`를 삭제해버린다.
