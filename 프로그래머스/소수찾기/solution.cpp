#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

int ans = 0;
vector<bool> visited;
unordered_map<int, bool> check;

bool isPrime(int n) {
    if (n < 2)
        return false;
    for (int i = 2; i * i <= n; ++i) {
        if (n % i == 0)
            return false;
    }
    return true;
}

void dfs(string numbers, string s) {
    if (s.length() != 0) {
        int n  = stoi(s);
        if (n == 0)
            return;

        if (!check[n]) {
            if (isPrime(n))
                ++ans;
            check[n] = true;
        }
    }
    
    for (int i = 0; i < numbers.length(); ++i) {
        if (visited[i])
            continue;

        visited[i] = true;
        dfs(numbers, s + numbers[i]);
        visited[i] = false;
    }
}

int solution(string numbers) {
    visited.resize(numbers.length());
    dfs(numbers, "");
    
    return ans;
}