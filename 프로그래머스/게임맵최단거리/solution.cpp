#include <vector>
#include <queue>

using namespace std;

int solution(vector<vector<int> > board) {
    int dx[] = {1, -1, 0, 0};
    int dy[] = {0, 0, 1, -1};
    
    vector<vector<int>> dist(board.size(), vector<int>(board[0].size(), -1));
    queue<pair<int, int>> q;
    q.push({0, 0});
    dist[0][0] = 1;
    
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || nx >= board[0].size() || ny < 0 || ny >= board.size() || !board[ny][nx] || dist[ny][nx] != -1)
                continue;
            
            dist[ny][nx] = dist[y][x] + 1;
            q.push({nx, ny});
        }
    }
    
    return dist[board.size() - 1][board[0].size() - 1];
}