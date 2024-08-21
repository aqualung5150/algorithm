#include <string>
#include <vector>
#include <cctype>
#include <algorithm>

using namespace std;

void getLower(string &s) {
    for (int i = 0; i < s.length(); ++i)
        s[i] = tolower(s[i]);
}

int solution(int cacheSize, vector<string> cities) {
    vector<string> caches;
    
    int ans = 0;
    for (auto city : cities) {
        getLower(city);
        auto it = find(caches.begin(), caches.end(), city);
        if (it != caches.end()) {
            ++ans;
            caches.erase(it);
            caches.push_back(city);
        } else {
            ans += 5;
            if (caches.size() > 0 && caches.size() == cacheSize)
                caches.erase(caches.begin());
            if (cacheSize)
                caches.push_back(city);
        }
    }
    
    return ans;
}