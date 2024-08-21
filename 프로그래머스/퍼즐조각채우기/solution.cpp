#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

typedef struct s_coord {
    int x;
    int y;
} t_coord;

vector<vector<int>> getShape(vector<vector<int>> &table, int start_x, int start_y, bool valid) {
    vector<t_coord> coords;
    queue<t_coord> q;
    q.push({start_x, start_y});
    table[start_y][start_x] = !valid;
    
    int dx[] = {1, -1, 0, 0};
    int dy[] = {0, 0, 1, -1};
    
    int min_x = table[0].size();
    int min_y = table.size();
    int max_x = 0;
    int max_y = 0;
    
    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;
        q.pop();
        
        coords.push_back({x, y});
        
        min_x = min(min_x, x);
        min_y = min(min_y, y);
        max_x = max(max_x, x);
        max_y = max(max_y, y);
        
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || nx >= table[0].size() || ny < 0 || ny >= table.size() || table[ny][nx] != valid)
                continue;
            
            table[ny][nx] = !valid;
            q.push({nx, ny});
        }
    }
    
    vector<vector<int>> puzzle(max_y - min_y + 1, vector<int>(max_x - min_x + 1, 1));
    
    for (int i = 0; i < coords.size(); ++i) {
        int x = coords[i].x - min_x;
        int y = coords[i].y - min_y;
        puzzle[y][x] = 0;
    }
    
    return puzzle;
}

vector<vector<int>> rotateShape(vector<vector<int>> & puzzle) {
    vector<vector<int>> ret(puzzle[0].size(), vector<int>(puzzle.size()));
    
    for (int i = 0; i < puzzle.size(); ++i) {
        for (int j = 0; j < puzzle[0].size(); ++j) {
            ret[j][puzzle.size() - 1 - i] = puzzle[i][j];
        }
    }
    
    return ret;
}

int compareShape(vector<vector<int>> &board, vector<vector<int>> &puzzle) {
    int ret = 0;
    
    if (board.size() != puzzle.size() || board[0].size() != puzzle[0].size())
        return 0;
    
    for (int i = 0; i < puzzle.size(); ++i)
        for (int j = 0; j < puzzle[0].size(); ++j) {
            if (board[i][j] != puzzle[i][j])
                return 0;
            else if (!board[i][j])
                ++ret;
        }
    
    return ret;
}

int solution(vector<vector<int>> game_board, vector<vector<int>> table) {
    vector<vector<vector<int>>> boards;
    vector<vector<vector<vector<int>>>> puzzles(4);
    // game_board
    for (int i = 0; i < game_board.size(); ++i) {
        for (int j = 0; j < game_board[0].size(); ++j) {
            if (game_board[i][j])
                continue;
            boards.push_back(getShape(game_board, j, i, 0));
        }
    }
    // table
    for (int i = 0; i < table.size(); ++i) {
        for (int j = 0; j < table[0].size(); ++j) {
            if (!table[i][j])
                continue;
            puzzles[0].push_back(getShape(table, j, i, 1));
            
            for (int i = 1; i < 4; ++i)
                puzzles[i].push_back(rotateShape(puzzles[i - 1][puzzles[i - 1].size() - 1]));
        }
    }
    
    vector<bool> visited(puzzles[0].size());
    int ans = 0;
    for (int i = 0; i < boards.size(); ++i) {
        for (int j = 0; j < 4; ++j) {
            int matched = 0;
            for (int k = 0; k < puzzles[j].size(); ++k) {
                if (visited[k])
                    continue;
                
                matched = compareShape(boards[i], puzzles[j][k]);
                if (matched) {
                    ans += matched;
                    visited[k] = true;
                    break;
                }
            }
            if (matched)
                break;
        }
    }
    
    return ans;
}
