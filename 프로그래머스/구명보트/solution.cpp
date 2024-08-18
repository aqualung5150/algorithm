#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> people, int limit) {
    sort(people.begin(), people.end());
    
    int left = 0;
    int right = people.size() - 1;
    
    int ans = 0;
    while (left <= right) {
        ++ans;
        int a = limit - people[right];
        --right;
        
        if (people[left] <= a)
            ++left;
    }
    
    return ans;
}