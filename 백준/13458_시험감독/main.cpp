#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int N;
    cin >> N;
    
    vector<int> v(N);
    for (int i = 0; i < N; ++i) {
        cin >> v[i];
    }

    int B, C;
    cin >> B >> C;

    long long ans = 0;
    for (int i = 0; i < N; ++i) {
        ++ans;
        v[i] -= B;
        if (v[i] > 0) {
            ans += v[i] / C;
            if (v[i] % C)
                ++ans;
        }
    }
    cout << ans;

    return 0;
}