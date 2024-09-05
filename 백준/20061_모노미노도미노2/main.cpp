#include <iostream>
#include <vector>

using namespace std;

int N;
int score = 0;
int total = 0;
vector<vector<bool>> green(6, vector<bool>(4));
vector<vector<bool>> blue(4, vector<bool>(6));

void g_remove(int y) {
    for (int x = 0; x < 4; ++x)
        if (green[y][x])
            --total;
    
    for (int i = y - 1; i >= 0; --i)
        for (int j = 0; j < 4; ++j)
            green[i + 1][j] = green[i][j];
    
    for (int i = 0; i < 4; ++i)
        green[0][i] = false;
}

void g_check(int y) {
    int flag = true;
    for (int x = 0; x < 4; ++x)
        if (!green[y][x]) {
            flag = false;
            break;
        }
    
    if (flag) {
        ++score;
        g_remove(y);
    }
}

void b_remove(int x) {
    for (int y = 0; y < 4; ++y)
        if (blue[y][x])
            --total;
    
    for (int i = 0; i < 4; ++i)
        for (int j = x - 1; j >= 0; --j)
            blue[i][j + 1] = blue[i][j];

    for (int i = 0; i < 4; ++i)
        blue[i][0] = false;
}

void b_check(int x) {
    int flag = true;
    for (int y = 0; y < 4; ++y)
        if (!blue[y][x]) {
            flag = false;
            break;
        }
    
    if (flag) {
        ++score;
        b_remove(x);
    }
}

void special() {
    // green
    bool flag[2] = {false, false};
    for (int i = 0; i < 4; ++i) {
        if (green[0][i])
            flag[0] = true;
        if (green[1][i])
            flag[1] = true;
    }
    if (flag[0])
        g_remove(5);
    if (flag[1])
        g_remove(5);
    // blue
    flag[0] = false;
    flag[1] = false;
    for (int i = 0; i < 4; ++i) {
        if (blue[i][0])
            flag[0] = true;
        if (blue[i][1])
            flag[1] = true;
    }
    if (flag[0])
        b_remove(5);
    if (flag[1])
        b_remove(5);
}

void setBlock(int t, int x, int y) {
    if (t == 1) {
        total += 2;
        // green
        int gy = 1;
        while (gy < 6 && green[gy][x] == false)
            ++gy;
        green[gy - 1][x] = true;
        g_check(gy - 1);
        // blue
        int bx = 1;
        while (bx < 6 && blue[y][bx] == false)
            ++bx;
        blue[y][bx - 1] = true;
        b_check(bx - 1);
    } else if (t == 2) {
        total += 4;
        // green
        int gy = 1;
        while (gy < 6 && green[gy][x] == false)
            ++gy;
        green[gy - 1][x] = true;
        green[gy - 2][x] = true;
        g_check(gy - 2);
        g_check(gy - 1);
        // blue
        int bx = 1;
        while (bx < 6 && blue[y][bx] == false && blue[y + 1][bx] == false)
            ++bx;
        blue[y][bx - 1] = true;
        blue[y + 1][bx - 1] = true;
        b_check(bx - 1);
    } else {
        total += 4;
        // green
        int gy = 1;
        while (gy < 6 && green[gy][x] == false && green[gy][x + 1] == false)
            ++gy;
        green[gy - 1][x] = true;
        green[gy - 1][x + 1] = true;
        g_check(gy - 1);
        // blue
        int bx = 1;
        while (bx < 6 && blue[y][bx] == false)
            ++bx;
        blue[y][bx - 1] = true;
        blue[y][bx - 2] = true;
        b_check(bx - 2);
        b_check(bx - 1);
    }
    special();
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;

    while (N--) {
        int t, x, y;
        cin >> t >> x >> y;

        setBlock(t, x, y);
    }
    
    cout << score << "\n" << total;

    return 0;
}