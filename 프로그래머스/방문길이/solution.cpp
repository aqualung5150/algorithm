#include <string>
#include <vector>

using namespace std;

int solution(string dirs) {
    vector<vector<vector<bool>>> edges(11, vector<vector<bool>>(11, vector<bool>(4)));
    
    int ans = 0;
    int x = 5;
    int y = 5;
    for (int i = 0; i < dirs.length(); ++i) {
        switch (dirs[i]) {
            case 'U':
                if (y + 1 > 10)
                    break;
                if (!edges[y][x][0]) {
                    edges[y][x][0] = 1;
                    edges[y + 1][x][1] = 1;
                    ++ans;
                }
                ++y;
                break;
            case 'D':
                if (y - 1 < 0)
                    break;
                if (!edges[y][x][1]) {
                    edges[y][x][1] = 1;
                    edges[y - 1][x][0] = 1;
                    ++ans;
                }
                --y;
                break;
            case 'R':
                if (x + 1 > 10)
                    break;
                if (!edges[y][x][2]) {
                    edges[y][x][2] = 1;
                    edges[y][x + 1][3] = 1;
                    ++ans;
                }
                ++x;
                break;
            case 'L':
                if (x - 1 < 0)
                    break;
                if (!edges[y][x][3]) {
                    edges[y][x][3] = 1;
                    edges[y][x - 1][2] = 1;
                    ++ans;
                }
                --x;
                break;
        }
    }
    return ans;
}
