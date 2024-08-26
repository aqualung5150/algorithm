#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> land) {    
    int ans = 0;
    for (int i = 1; i < land.size(); ++i) {
        for (int j = 0; j < 4; ++j) {
            int cur = land[i][j];
            for (int k = 0; k < 4; ++k) {
                if (j == k)
                    continue;
                land[i][j] = max(land[i][j], cur + land[i - 1][k]);
            }
            ans = max(ans, land[i][j]);
        }
    }
    return ans;
}