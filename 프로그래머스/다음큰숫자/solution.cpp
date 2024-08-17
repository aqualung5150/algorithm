#include <string>
#include <vector>

using namespace std;

int getNumberOfOne(int n) {
    int count = 0;
    while (n) {
        count += n % 2;
        n /= 2;
    }
    return count;
}

int solution(int n) {
    int count = getNumberOfOne(n);
    
    while (1) {
        ++n;
        
        if (getNumberOfOne(n) == count)
            return n;
    }
}