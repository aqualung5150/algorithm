#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
     int maxHealth = health;
    
    for (int i = 0; i < attacks.size(); ++i) {
        health -= attacks[i][1];
        
        if (i + 1 >= attacks.size() || health <= 0)
            break;
        
        int time = attacks[i + 1][0] - attacks[i][0] - 1;
        
        health += time * bandage[1];
        health += (time / bandage[0]) * bandage[2];
        health = min(health, maxHealth);
    }
    
    if (health <= 0)
        return -1;
    return health;
}