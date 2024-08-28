#include <iostream>
#include <vector>
#include <string>
#include <cmath>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    vector<vector<int>> gear(4, vector<int>(8));

    for (int i = 0; i < 4; ++i) {
        string input;
        cin >> input;
        for (int j = 0; j < 8; ++j)
            gear[i][j] = input[j] - '0';
    }

    int K;
    cin >> K;

    vector<int> curTop(4);
    while (K--) {
        vector<int> curDir(4);
        int cur, dir;
        cin >> cur >> dir;
        --cur;
        curDir[cur] = dir;
        // right
        for (int right = cur + 1; right < 4; ++right) {
            int leftTop = curTop[right - 1];
            int rightTop = curTop[right];
            int leftThree = gear[right - 1][(leftTop + 2) % 8];
            int rightNine = gear[right][(rightTop + 6) % 8];

            if (leftThree == rightNine)
                break;

            curDir[right] = -curDir[right - 1];
        }
        // left
        for (int left = cur - 1; left >= 0; --left) {
            int leftTop = curTop[left];
            int rightTop = curTop[left + 1];
            int leftThree = gear[left][(leftTop + 2) % 8];
            int rightNine = gear[left + 1][(rightTop + 6) % 8];

            if (leftThree == rightNine)
                break;

            curDir[left] = -curDir[left + 1];
        }
        // turn gears
        for (int i = 0; i < 4; ++i) {
            if (curDir[i] == 1)
                curTop[i] = (curTop[i] + 7) % 8;
            else if (curDir[i] == -1)
                curTop[i] = (curTop[i] + 1) % 8;
        }
    }

    int ans = 0;

    for (int i = 0; i < 4; ++i)
        if (gear[i][curTop[i]])
            ans += pow(2, i);

    cout << ans;

    return 0;
}