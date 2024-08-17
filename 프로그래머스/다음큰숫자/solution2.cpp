#include <bitset>

using namespace std;

int solution(int n) {
    int count = bitset<20>(n).count();
    
    while (bitset<20>(++n).count() != count);
    
    return n;
}