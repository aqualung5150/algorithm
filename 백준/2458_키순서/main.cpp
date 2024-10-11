#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M;
vector<vector<int>> edge[2];

int bfs(vector<vector<int>> edges, int start) {
    vector<bool> visited(N);
    queue<int> q;
    q.push(start);
    int ret = 0;

    while (!q.empty()) {
        int here = q.front();
        q.pop();

        for (int i = 0; i < edges[here].size(); ++i) {
            int there = edges[here][i];

            if (visited[there])
                continue;

            q.push(there);
            visited[there] = true;
            ++ret;
        }
    }

    return ret;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M;
    edge[0].resize(N);
    edge[1].resize(N);

    for (int i = 0; i < M; ++i) {
        int a, b;
        cin >> a >> b;
        --a; --b;

        edge[0][a].push_back(b);
        edge[1][b].push_back(a);
    }

    int answer = 0;
    for (int i = 0; i < N; ++i) {
        int edgeCount = 0;
        edgeCount += bfs(edge[0], i);
        edgeCount += bfs(edge[1], i);

        if (edgeCount == N - 1)
            ++answer;
    }

    cout << answer;

    return 0;
}