#include <iostream>
#include <queue>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    priority_queue<int> maxHeap;
    priority_queue<int, vector<int>, greater<int>> minHeap;

    int N;
    cin >> N;

    for (int i = 0; i < N; ++i) {
        int n;
        cin >> n;

        if (!(i % 2))
            maxHeap.push(n);
        else
            minHeap.push(n);

        if (!minHeap.empty() && maxHeap.top() > minHeap.top()) {
            int temp = maxHeap.top();
            maxHeap.pop();
            maxHeap.push(minHeap.top());
            minHeap.pop();
            minHeap.push(temp);
        }

        cout << maxHeap.top() << "\n";
    }

    return 0;
}