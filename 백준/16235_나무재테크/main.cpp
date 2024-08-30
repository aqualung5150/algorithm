#include <iostream>
#include <vector>
#include <deque>
#include <algorithm>

using namespace std;

int N, M, K, cnt;
vector<vector<int>> store;
vector<vector<int>> feed;
vector<vector<deque<int>>> tree;
int dx[] = {1, 1, 0, -1, -1, -1, 0, 1};
int dy[] = {0, 1, 1, 1, 0, -1, -1, -1};

void autumn(int x, int y) {
    for (int idx = 0; idx < tree[y][x].size(); ++idx) {
        if (tree[y][x][idx] % 5)
            continue;
        
        for (int i = 0; i < 8; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                continue;

            tree[ny][nx].push_front(1);
            ++cnt;
        }
    }
}

void springSummer(int x, int y) {
    int i = 0;
    while (i < tree[y][x].size()) {
        int age = tree[y][x][i];
        if (store[y][x] >= age) {
            store[y][x] -= age;
            ++tree[y][x][i];
            ++i;
        } else
            break;
    }

    for (int j = tree[y][x].size() - 1; j >= i; --j) {
        store[y][x] += tree[y][x][j] / 2;
        tree[y][x].pop_back();
        --cnt;
    }
}

void winter() {
    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            store[i][j] += feed[i][j];
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M >> K;

    cnt = M;
    store.resize(N, vector<int>(N, 5));
    feed.resize(N, vector<int>(N));
    tree.resize(N, vector<deque<int>>(N));

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            cin >> feed[i][j];

    vector<vector<int>> input; // {z, y, x}
    for (int i = 0; i < M; ++i) {
        int x, y, z;
        cin >> x >> y >> z;

        input.push_back({z, y - 1, x - 1});
    }
    sort(input.begin(), input.end());
    for (auto i : input)
        tree[i[2]][i[1]].push_back(i[0]);

    for (int year = 0; year < K; ++year) {
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                springSummer(j, i);

        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j)
                autumn(j, i);
        winter();
    }

    cout << cnt;

    return 0;
}