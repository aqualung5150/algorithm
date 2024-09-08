#include <iostream>
#include <vector>

using namespace std;

int N, K;
int s, e;
int ans = 0;
int broken = 0;
vector<int> dura;
vector<bool> state;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> K;
    dura.resize(N * 2);
    state.resize(N * 2);
    s = 0;
    e = N - 1;

    for (int i = 0; i < N * 2; ++i)
        cin >> dura[i];

    while (broken < K) {
        s = (s + N * 2 - 1) % (N * 2);
        e = (e + N * 2 - 1) % (N * 2);

        state[e] = false;

        for (int i = N - 2; i > 0; --i) {
            int here = (s + i) % (N * 2);
            if (!state[here]) // 이 위치에 로봇 없음
                continue;

            int next = (s + i + 1) % (N * 2);
            if (!dura[next] || state[next])
                continue;

            state[next] = true;
            state[here] = false;
            --dura[next];
            
            if (!dura[next])
                ++broken;
            if (next == e)
                state[next] = false;
        }
        
        // 올리는 위치
        if (dura[s] && !state[s]) {
            --dura[s];
            if (!dura[s])
                ++broken;
            state[s] = true;
        }

        ++ans;
    }

    cout << ans;

    return 0;
}