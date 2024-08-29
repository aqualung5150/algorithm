#include <iostream>
#include <vector>
#include <string>

using namespace std;

vector<vector<vector<char>>> cube(6, vector<vector<char>>(3, vector<char>(3)));

void init() {
    vector<char> c = {'o', 'w', 'r', 'y', 'g', 'b'};

    for (int k = 0; k < 6; ++k)
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
                cube[k][i][j] = c[k];
}

vector<vector<char>> turn(vector<vector<char>> &c, int d) {
    vector<vector<char>> ret(3, vector<char>(3));
    if (d == 1) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                ret[j][2 - i] = c[i][j];
            }
        }
    } else {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                ret[2 - j][i] = c[i][j];
            }
        }
    }
    return ret;
}

void cubing(string op) {
    /*
    CUBE
      3
      0
    4 1 5
      2
      3
    */
    if (op == "U+") {
        cube[1] = turn(cube[1], 1);

        vector<char> temp = {cube[0][2][0], cube[0][2][1], cube[0][2][2]};
        //B
        cube[0][2][0] = cube[4][2][2];
        cube[0][2][1] = cube[4][1][2];
        cube[0][2][2] = cube[4][0][2];
        //L
        cube[4][2][2] = cube[2][0][2];
        cube[4][1][2] = cube[2][0][1];
        cube[4][0][2] = cube[2][0][0];
        //F
        cube[2][0][2] = cube[5][0][0];
        cube[2][0][1] = cube[5][1][0];
        cube[2][0][0] = cube[5][2][0];
        //R
        cube[5][0][0] = temp[0];
        cube[5][1][0] = temp[1];
        cube[5][2][0] = temp[2];
    }
    if (op == "U-") {
        cube[1] = turn(cube[1], -1);

        vector<char> temp = {cube[0][2][0], cube[0][2][1], cube[0][2][2]};
        //B
        cube[0][2][0] = cube[5][0][0];
        cube[0][2][1] = cube[5][1][0];
        cube[0][2][2] = cube[5][2][0];
        //R
        cube[5][0][0] = cube[2][0][2];
        cube[5][1][0] = cube[2][0][1];
        cube[5][2][0] = cube[2][0][0];
        //F
        cube[2][0][2] = cube[4][2][2];
        cube[2][0][1] = cube[4][1][2];
        cube[2][0][0] = cube[4][0][2];
        //L
        cube[4][2][2] = temp[0];
        cube[4][1][2] = temp[1];
        cube[4][0][2] = temp[2];
    }
    if (op == "D+") {
        cube[3] = turn(cube[3], 1);

        vector<char> temp = {cube[2][2][0], cube[2][2][1], cube[2][2][2]};
        //F
        cube[2][2][0] = cube[4][0][0];
        cube[2][2][1] = cube[4][1][0];
        cube[2][2][2] = cube[4][2][0];
        //L
        cube[4][0][0] = cube[0][0][2];
        cube[4][1][0] = cube[0][0][1];
        cube[4][2][0] = cube[0][0][0];
        //B
        cube[0][0][2] = cube[5][2][2];
        cube[0][0][1] = cube[5][1][2];
        cube[0][0][0] = cube[5][0][2];
        //R
        cube[5][2][2] = temp[0];
        cube[5][1][2] = temp[1];
        cube[5][0][2] = temp[2];
    }
    if (op == "D-") {
        cube[3] = turn(cube[3], -1);

        vector<char> temp = {cube[2][2][0], cube[2][2][1], cube[2][2][2]};
        //F
        cube[2][2][0] = cube[5][2][2];
        cube[2][2][1] = cube[5][1][2];
        cube[2][2][2] = cube[5][0][2];
        //R
        cube[5][2][2] = cube[0][0][2];
        cube[5][1][2] = cube[0][0][1];
        cube[5][0][2] = cube[0][0][0];
        //B
        cube[0][0][2] = cube[4][0][0];
        cube[0][0][1] = cube[4][1][0];
        cube[0][0][0] = cube[4][2][0];
        //L
        cube[4][0][0] = temp[0];
        cube[4][1][0] = temp[1];
        cube[4][2][0] = temp[2];
    }
    if (op == "F+") {
        cube[2] = turn(cube[2], 1);

        vector<char> temp = {cube[1][2][0], cube[1][2][1], cube[1][2][2]};
        //U
        cube[1][2][0] = cube[4][2][0];
        cube[1][2][1] = cube[4][2][1];
        cube[1][2][2] = cube[4][2][2];
        //L
        cube[4][2][0] = cube[3][0][2];
        cube[4][2][1] = cube[3][0][1];
        cube[4][2][2] = cube[3][0][0];
        //D
        cube[3][0][2] = cube[5][2][0];
        cube[3][0][1] = cube[5][2][1];
        cube[3][0][0] = cube[5][2][2];
        //R
        cube[5][2][0] = temp[0];
        cube[5][2][1] = temp[1];
        cube[5][2][2] = temp[2];
    }
    if (op == "F-") {
        cube[2] = turn(cube[2], -1);

        vector<char> temp = {cube[1][2][0], cube[1][2][1], cube[1][2][2]};
        //U
        cube[1][2][0] = cube[5][2][0];
        cube[1][2][1] = cube[5][2][1];
        cube[1][2][2] = cube[5][2][2];
        //R
        cube[5][2][0] = cube[3][0][2];
        cube[5][2][1] = cube[3][0][1];
        cube[5][2][2] = cube[3][0][0];
        //D
        cube[3][0][2] = cube[4][2][0];
        cube[3][0][1] = cube[4][2][1];
        cube[3][0][0] = cube[4][2][2];
        //L
        cube[4][2][0] = temp[0];
        cube[4][2][1] = temp[1];
        cube[4][2][2] = temp[2];
    }
    if (op == "B+") {
        cube[0] = turn(cube[0], 1);

        vector<char> temp = {cube[3][2][0], cube[3][2][1], cube[3][2][2]};
        //D
        cube[3][2][0] = cube[4][0][2];
        cube[3][2][1] = cube[4][0][1];
        cube[3][2][2] = cube[4][0][0];
        //L
        cube[4][0][2] = cube[1][0][2];
        cube[4][0][1] = cube[1][0][1];
        cube[4][0][0] = cube[1][0][0];
        //U
        cube[1][0][2] = cube[5][0][2];
        cube[1][0][1] = cube[5][0][1];
        cube[1][0][0] = cube[5][0][0];
        //R
        cube[5][0][2] = temp[0];
        cube[5][0][1] = temp[1];
        cube[5][0][0] = temp[2];
    }
    if (op == "B-") {
        cube[0] = turn(cube[0], -1);

        vector<char> temp = {cube[3][2][0], cube[3][2][1], cube[3][2][2]};
        //D
        cube[3][2][0] = cube[5][0][2];
        cube[3][2][1] = cube[5][0][1];
        cube[3][2][2] = cube[5][0][0];
        //R
        cube[5][0][2] = cube[1][0][2];
        cube[5][0][1] = cube[1][0][1];
        cube[5][0][0] = cube[1][0][0];
        //U
        cube[1][0][2] = cube[4][0][2];
        cube[1][0][1] = cube[4][0][1];
        cube[1][0][0] = cube[4][0][0];
        //L
        cube[4][0][2] = temp[0];
        cube[4][0][1] = temp[1];
        cube[4][0][0] = temp[2];
    }
    if (op == "L+") {
        cube[4] = turn(cube[4], 1);

        vector<char> temp = {cube[0][0][0], cube[0][1][0], cube[0][2][0]};
        //B
        cube[0][0][0] = cube[3][0][0];
        cube[0][1][0] = cube[3][1][0];
        cube[0][2][0] = cube[3][2][0];
        //D
        cube[3][0][0] = cube[2][0][0];
        cube[3][1][0] = cube[2][1][0];
        cube[3][2][0] = cube[2][2][0];
        //F
        cube[2][0][0] = cube[1][0][0];
        cube[2][1][0] = cube[1][1][0];
        cube[2][2][0] = cube[1][2][0];
        //U
        cube[1][0][0] = temp[0];
        cube[1][1][0] = temp[1];
        cube[1][2][0] = temp[2];
    }
    if (op == "L-") {
        cube[4] = turn(cube[4], -1);

        vector<char> temp = {cube[0][0][0], cube[0][1][0], cube[0][2][0]};
        //B
        cube[0][0][0] = cube[1][0][0];
        cube[0][1][0] = cube[1][1][0];
        cube[0][2][0] = cube[1][2][0];
        //U
        cube[1][0][0] = cube[2][0][0];
        cube[1][1][0] = cube[2][1][0];
        cube[1][2][0] = cube[2][2][0];
        //F
        cube[2][0][0] = cube[3][0][0];
        cube[2][1][0] = cube[3][1][0];
        cube[2][2][0] = cube[3][2][0];
        //D
        cube[3][0][0] = temp[0];
        cube[3][1][0] = temp[1];
        cube[3][2][0] = temp[2];
    }
    if (op == "R+") {
        cube[5] = turn(cube[5], 1);

        vector<char> temp = {cube[0][2][2], cube[0][1][2], cube[0][0][2]};
        //B
        cube[0][2][2] = cube[1][2][2];
        cube[0][1][2] = cube[1][1][2];
        cube[0][0][2] = cube[1][0][2];
        //U
        cube[1][2][2] = cube[2][2][2];
        cube[1][1][2] = cube[2][1][2];
        cube[1][0][2] = cube[2][0][2];
        //F
        cube[2][2][2] = cube[3][2][2];
        cube[2][1][2] = cube[3][1][2];
        cube[2][0][2] = cube[3][0][2];
        //D
        cube[3][2][2] = temp[0];
        cube[3][1][2] = temp[1];
        cube[3][0][2] = temp[2];
    }
    if (op == "R-") {
        cube[5] = turn(cube[5], -1);

        vector<char> temp = {cube[0][2][2], cube[0][1][2], cube[0][0][2]};
        //B
        cube[0][2][2] = cube[3][2][2];
        cube[0][1][2] = cube[3][1][2];
        cube[0][0][2] = cube[3][0][2];
        //D
        cube[3][2][2] = cube[2][2][2];
        cube[3][1][2] = cube[2][1][2];
        cube[3][0][2] = cube[2][0][2];
        //F
        cube[2][2][2] = cube[1][2][2];
        cube[2][1][2] = cube[1][1][2];
        cube[2][0][2] = cube[1][0][2];
        //U
        cube[1][2][2] = temp[0];
        cube[1][1][2] = temp[1];
        cube[1][0][2] = temp[2];
    }
}

void print() {
    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 3; ++j)
            cout << cube[1][i][j];
        cout << "\n";
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int T;
    cin >> T;
    while (T--) {
        int N;
        cin >> N;
        init();

        while (N--) {
            string input;
            cin >> input;
            cubing(input);
        }

        print();
    }

    return 0;
}