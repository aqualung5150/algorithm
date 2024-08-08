#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, R, Q;
vector<vector<int>> edges;
vector<int> parent;
vector<int> numOfNodes;

void makeTree() {
    queue<int> q;
    q.push(R);
    parent[R] = -1;

    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        for (int i = 0; i < edges[cur].size(); ++i) {
            int next = edges[cur][i];

            if (next == parent[cur]) continue;

            parent[next] = cur;
            q.push(next);
        }
    }
}

void countSubtreeNodes(int cur) {
    numOfNodes[cur] = 1;
    for (int i = 0; i < edges[cur].size(); ++i) {
        int next = edges[cur][i];
        if (parent[cur] == next)
            continue;
        countSubtreeNodes(next);
        numOfNodes[cur] += numOfNodes[next];
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> R >> Q;

    edges.resize(N + 1);
    parent.resize(N + 1);
    numOfNodes.resize(N + 1);

    --N;
    while (N--) {
        int a, b;
        cin >> a >> b;
        edges[a].push_back(b);
        edges[b].push_back(a);
    }

    makeTree();
    countSubtreeNodes(R);

    while (Q--) {
        int root;
        cin >> root;
        cout << numOfNodes[root] << "\n";
    }

    return 0;
}