#include <string>
#include <vector>
#include <string.h>

#define MAX_DEPTH 1000000000

using namespace std;

int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};

int ans = MAX_DEPTH;
// vector<vector<bool>> visited;
int visited[2][4][4];

void dfs(vector<vector<int>> &maze, int x1, int y1, int x2, int y2, int depth) {
    if (maze[y1][x1] == 3 && maze[y2][x2] == 4) {
        ans = min(ans, depth);
        return;
    }
    
    for (int i = 0; i < 4; ++i) {
        int nx1;
        int ny1;
        if (maze[y1][x1] == 3) { // 도착지점에 있음
            nx1 = x1;
            ny1 = y1;
            visited[0][ny1][nx1] = false;
        } else {
            nx1 = x1 + dx[i];
            ny1 = y1 + dy[i];
        }
        
        //범위 밖
        if (nx1 < 0 || nx1 >= maze[0].size() || ny1 < 0 || ny1 >= maze.size() || maze[ny1][nx1] == 5 || visited[0][ny1][nx1])
            continue;
        
        for (int j = 0; j < 4; ++j) {
            int nx2;
            int ny2;
            if (maze[y2][x2] == 4) {
                nx2 = x2;
                ny2 = y2;
                visited[1][ny2][nx2] = false;
            } else {
                nx2 = x2 + dx[j];
                ny2 = y2 + dy[j];
            }
            
            //범위 밖
            if (nx2 < 0 || nx2 >= maze[0].size() || ny2 < 0 || ny2 >= maze.size() || maze[ny2][nx2] == 5 || visited[1][ny2][nx2])
                continue;
            // 스위치 or 같은 목적지
            if ((nx1 == x2 && ny1 == y2 && nx2 == x1 && ny2 == y1) || (nx1 == nx2 && ny1 == ny2))
                continue;
            
            // valid
            visited[0][ny1][nx1] = true;
            visited[1][ny2][nx2] = true;
            
            dfs(maze, nx1, ny1, nx2, ny2, depth + 1);
            
            visited[0][ny1][nx1] = false;
            visited[1][ny2][nx2] = false;
        }
    }
}

int solution(vector<vector<int>> maze) {
    memset(visited, 0, sizeof(visited));
    
    int x1, y1, x2, y2;
    for (int i = 0; i < maze[0].size(); ++i) {
        for (int j = 0; j < maze.size(); ++j) {
            if (maze[j][i] == 1) {
                x1 = i;
                y1 = j;
            } else if (maze[j][i] == 2) {
                x2 = i;
                y2 = j;
            }
        }
    }
    
    visited[0][y1][x1] = true;
    visited[1][y2][x2] = true;
    dfs(maze, x1, y1, x2, y2, 0);
    
    if (ans == MAX_DEPTH)
        return 0;
    return ans;
}

/*
1. 범위 밖 X
2. 위치 스위치 X
3. nx,ny가 같으면 X
4. x,y가 도착위치면 nx,ny 계산 X
*/