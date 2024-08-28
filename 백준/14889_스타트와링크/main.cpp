#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int N;
vector<vector<int>> score;
vector<bool> member;
int ans = 1000000000;

int getScoreDiff() {
    int a = 0;
    int b = 0;

    for (int i = 0; i < member.size(); ++i) {
        for (int j = 0; j < member.size(); ++j) {
            if (i != j && member[i] == member[j]) {
                if (member[i] == true)
                    a += score[i][j];
                else
                    b += score[i][j];
            }
        }
    }

    return abs(a - b);
}

void dfs(int idx, int prev) {
    if (idx == N / 2) {
        ans = min(ans, getScoreDiff());
    } else {
        for (int i = prev + 1; i < N; ++i) {
            member[i] = true;
            dfs(idx + 1, i);
            member[i] = false;
        }
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    score.resize(N, vector<int>(N));
    member.resize(N);

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            cin >> score[i][j];

    dfs(0, -1);
    
    cout << ans;

    return 0;
}

/*
4
0 1 2 3
4 0 5 6
7 1 0 2
3 4 5 0
*/