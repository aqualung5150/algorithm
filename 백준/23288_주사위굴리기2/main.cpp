#include <iostream>
#include <vector>
#include <deque>
#include <queue>

using namespace std;

int N, M, K;
vector<vector<int>> board;

struct Point {
    int x;
    int y;
};

class Dice {
private:
    int score;
    Point point;
    vector<int> dx;
    vector<int> dy;
    int direction;
    deque<int> horizon;
    deque<int> vertical;
    int bottomNumber;

public:
    Dice() {
        score = 0;
        point = {0, 0};
        dx = {1, 0, -1, 0};
        dy = {0, 1, 0, -1};
        direction = 0;
        horizon = {4, 1, 3};
        vertical = {2, 1, 5};
        bottomNumber = 6;
    }

    void addScore() {
        int target = board[point.y][point.x];
        int targetPoint = 0;
        vector<vector<bool>> visited(N, vector<bool>(M));
        queue<Point> q;
        q.push(point);
        visited[point.y][point.x] = true;

        while (!q.empty()) {
            int x = q.front().x;
            int y = q.front().y;
            q.pop();

            ++targetPoint;

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[ny][nx] || board[ny][nx] != target)
                    continue;

                q.push({nx, ny});
                visited[ny][nx] = true;
            }
        }

        score += target * targetPoint;
    }

    void setDirection() {
        if (bottomNumber > board[point.y][point.x])
            direction = (direction + 1) % 4;
        else if (bottomNumber < board[point.y][point.x])
            direction = (direction + 3) % 4;
    }

    void go() {
        int nx = point.x + dx[direction];
        int ny = point.y + dy[direction];

        if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
            direction = (direction + 2) % 4;
            nx = point.x + dx[direction];
            ny = point.y + dy[direction];
        }

        switch (direction) {
            case 0:
                goRight();
                break;
            case 1:
                goDown();
                break;
            case 2:
                goLeft();
                break;
            case 3:
                goUp();
                break;
        }

        point.x = nx;
        point.y = ny;
    }

    void goRight() {
        horizon.push_front(bottomNumber);
        bottomNumber = horizon.back();
        horizon.pop_back();
        vertical[1] = horizon[1];
    }

    void goLeft() {
        horizon.push_back(bottomNumber);
        bottomNumber = horizon.front();
        horizon.pop_front();
        vertical[1] = horizon[1];
    }

    void goUp() {
        vertical.push_back(bottomNumber);
        bottomNumber = vertical.front();
        vertical.pop_front();
        horizon[1] = vertical[1];
    }

    void goDown() {
        vertical.push_front(bottomNumber);
        bottomNumber = vertical.back();
        vertical.pop_back();
        horizon[1] = vertical[1];
    }

    // int top() {
    //     return horizon[1];
    // }

    // int bottom() {
    //     return bottomNumber;
    // }

    int getScore() {
        return score;
    }

    // void print() {
    //     cout << "\n";
    //     for (int i = 0; i < 3; ++i) {
    //         cout << horizon[i];
    //     }
    //     cout << "\n";
    //     for (int j = 0; j < 3; ++j) {
    //         cout << vertical[j];
    //     }
    //     cout << "\n";
    //     cout << bottomNumber << "\n";
    //     cout << direction << " " << point.x << "," << point.y << "\n";
    // }
};

void init() {
    cin >> N >> M >> K;
    board.resize(N, vector<int>(M));
    for (int i = 0; i < N; ++i)
        for (int j = 0; j < M; ++j)
            cin >> board[i][j];
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    init();

    Dice dice;

    while (K--) {
        dice.go();
        dice.setDirection();
        dice.addScore();
        // dice.print();
    }

    cout << dice.getScore();

    return 0;
}