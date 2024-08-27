#include <string>
#include <vector>
#include <map>
#include <sstream>

using namespace std;

int getTime(string t) {
    int ret = stoi(t.substr(0, 2)) * 60;
    ret += stoi(t.substr(3, 2));
    return ret + 1;
}

vector<int> solution(vector<int> fees, vector<string> records) {
    map<string, int> spend;
    map<string, int> m;
    
    for (auto r : records) {
        stringstream ss(r);
        string t, n, s;
        ss >> t >> n >> s;
        
        if (!m[n])
            m[n] = getTime(t);
        else {
            if (!spend[n])
                spend[n] = 0;
            
            spend[n] += getTime(t) - m[n];
            m[n] = 0;
        }
    }
    
    vector<int> ans;
    for (auto i : m) {
        if (i.second)
            spend[i.first] += getTime("23:59") - i.second;
        int sum = fees[1];
        if (spend[i.first] - fees[0] > 0) {
            sum += ((spend[i.first] - fees[0]) / fees[2]) * fees[3];
            if ((spend[i.first] - fees[0]) % fees[2])
                sum += fees[3];
        }
        ans.push_back(sum);
    }
    
    return ans;
}