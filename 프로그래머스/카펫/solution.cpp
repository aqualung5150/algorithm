#include <string>
#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> ans(2);
    int i = 1;
    while (1) {
        int a = brown - i * 2;
        if (((a / 2 - 2) * i) == yellow) {
            ans[0] = a / 2;
            ans[1] = i + 2;
            return ans;
        }
        ++i;
    }
}

/*
갈 - {1 * 2, 2 * 2, 3 * 2, 4 * 2 ... i * 2}
-> (갈 / 2 - 2) * i
*/