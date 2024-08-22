#include <vector>
#include <algorithm>

using namespace std;

vector<vector<int>> getLock(vector<vector<int>> &lock) {
    vector<pair<int, int>> coords;
    int min_x = 20;
    int min_y = 20;
    int max_x = 0;
    int max_y = 0;
    
    for (int i = 0; i < lock.size(); ++i) {
        for (int j = 0; j < lock[0].size(); ++j) {
            if (!lock[i][j]) {
                coords.push_back({j, i});
                min_x = min(min_x, j);
                min_y = min(min_y, i);
                max_x = max(max_x, j);
                max_y = max(max_y, i);
            }
        }
    }
    
    vector<vector<int>> ret;
    
    if (min_x > max_x || min_y > max_y)
        return ret;
    
    ret.resize(max_y - min_y + 1, vector<int>(max_x - min_x + 1));
    
    for (auto coord : coords)
        ret[coord.second - min_y][coord.first - min_x] = 1;
    
    return ret;
}

vector<vector<int>> rotateLock(vector<vector<int>> &lock) {
    vector<vector<int>> ret(lock[0].size(), vector<int>(lock.size()));
    
    for (int i = 0; i < lock.size(); ++i) {
        for (int j = 0; j < lock[0].size(); ++j) {
            ret[j][lock.size() - 1 - i] = lock[i][j];
        }
    }
    
    return ret;
}

bool compareLock(vector<vector<int>> &key, vector<vector<int>> &lock) {
    int x_diff = key[0].size() - lock[0].size();
    int y_diff = key.size() - lock.size();
    
    for (int move_y = 0; move_y <= y_diff; ++move_y) {
        for (int move_x = 0; move_x <= x_diff; ++move_x) {
            bool valid = true;
            // compare
            for (int i = 0; i < lock.size(); ++i) {
                for (int j = 0; j < lock[0].size(); ++j) {
                    if (lock[i][j] != key[i + move_y][j + move_x]) {
                        valid = false;
                        break;
                    }
                }
            }
            
            if (valid)
                return true;
        }
    }
    return false;
}

bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
    vector<vector<vector<int>>> locks(4);
    locks[0] = getLock(lock);
    
    // 자물쇠에 홈이 없을때
    if (locks[0].size() == 0)
        return true;
    
    if (locks[0].size() > key.size() || locks[0][0].size() > key[0].size())
        return false;
    
    for (int i = 1; i < 4; ++i)
        locks[i] = rotateLock(locks[i - 1]);
    
    for (auto l : locks) {
        if (compareLock(key, l))
            return true;
    }
    return false;
}

/*
1. 자물쇠 블록 떼어내기
2. 자물쇠 회전시켜 저장
3. 자물쇠 좌표 바꾸면서 맞는지 찾기
4. 자물쇠 회전시키면서 찾기
*/