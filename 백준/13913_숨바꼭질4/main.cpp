#include <iostream>
#include <vector>
#include <queue>
#include <stack>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int N, K;
    cin >> N >> K;

    vector<int> visited(150000); // 1 == -1, 2 == +1, 3 == *2

    queue<int> q;
    q.push(N);

    while (!q.empty()) {
        int here = q.front();
        q.pop();

        // print
        if (here == K) {
            int count = 0;
            stack<int> stk;
            while (here != N) {
                stk.push(here);
                if (visited[here] == 1)
                    ++here;
                else if (visited[here] == 2)
                    --here;
                else
                    here /= 2;
                ++count;
            }
            stk.push(here);
            cout << count << "\n";
            while (!stk.empty()) {
                cout << stk.top() << " ";
                stk.pop();
            }
            return 0;
        }

        int there = here - 1;
        if (there >= 0 && !visited[there]) {
            q.push(there);
            visited[there] = 1;
        }

        there = here + 1;
        if (there <= K && !visited[there]) {
            q.push(there);
            visited[there] = 2;
        }

        there = here * 2;
        if (there - K < K - here && !visited[there]) {
            q.push(there);
            visited[there] = 3;
        }
    }


    return 0;
}