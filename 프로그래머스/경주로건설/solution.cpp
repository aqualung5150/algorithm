#include <string>
#include <vector>
#include <queue>

#define MAX_DIST 1000000000

using namespace std;

int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};
vector<vector<vector<int>>> dist;

void bfs(vector<vector<int>> &board) {
    priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq; // {비용, x, y, 방향}
    pq.push({0, 0, 0, 0}); // go right
    pq.push({0, 0, 0, 2}); // go down
    dist[0][0][0] = 0;
    dist[1][0][0] = 0;
    dist[2][0][0] = 0;
    dist[3][0][0] = 0;
    
    while (!pq.empty()) {
        vector<int> here = pq.top();
        pq.pop();
        
        if (here[0] > dist[here[3]][here[2]][here[1]])
            continue;
        
        for (int i = 0; i < 4; ++i) {
            int nx = here[1] + dx[i];
            int ny = here[2] + dy[i];
            int nextDist = here[0] + 100;
            if (here[3] != i)
                nextDist += 500;
            
            if (nx < 0 || nx >= board.size() || ny < 0 || ny >= board.size() || board[ny][nx] || dist[i][ny][nx] < nextDist)
                continue;
            
            dist[i][ny][nx] = nextDist;
            pq.push({nextDist, nx, ny, i});
        }
    }
}

int solution(vector<vector<int>> board) {
    dist.resize(4, vector<vector<int>>(board.size(), vector<int>(board.size(), MAX_DIST)));
    
    bfs(board);
                
    int ans = MAX_DIST;
    for (int i = 0; i < 4; ++i)
        ans = min(ans, dist[i][board.size() - 1][board.size() - 1]);
    return ans;
}