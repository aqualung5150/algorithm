#include <string>
#include <vector>

using namespace std;

vector<double> getDegree(int t) {
    int h = t / 3600;
    int m = (t % 3600) / 60;
    int s = (t % 3600) % 60;
    
    vector<double> ret;
    
    ret.push_back((h % 12) * 30.0  + m * (30.0 * 1/60.0) + s * (1 / 120.0));
    ret.push_back(m * 6.0 + s * (6.0 * 1/60.0));
    ret.push_back(s * 6.0);
    
    return ret;
}

int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
    int start = h1 * 3600 + m1 * 60 + s1;
    int end = h2 * 3600 + m2 * 60 + s2;
    
    int ans = 0;
    
    if (start == 0 || start == 3600 * 12)
        ++ans;
    
    for (int now = start; now < end; ++now) {
        vector<double> cur = getDegree(now);
        vector<double> next = getDegree(now + 1);
        double curH = cur[0];
        double curM = cur[1];
        double curS = cur[2];
        double nextH = next[0];
        double nextM = next[1];
        double nextS = next[2];
        
        if ((curS < curH && nextS >= nextH) || curS == 354 && curH > 354) 
            ++ans;
        if ((curS < curM && nextS >= nextM) || curS == 354 && curM > 354) 
            ++ans;
        
        if(nextH == 0 && nextM == 0)
            --ans;
    }    
    return ans;
}
