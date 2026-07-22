import java.util.*;
/*
 * [문제 정보] 피로도 (Level 2)
 * [사용 알고리즘] DFS, 백트래킹
 * 
 * [풀이 핵심]
 * 1. 던전의 최대 개수가 8로 매우 작으므로, 모든 던전의 방문 순서(순열)를 고려하는 완전 탐색 기법을 적용.
 * 2. 현재 남아있는 피로도가 던전의 '최소 필요 피로도' 이상일 때만 탐색 진행.
 * 3. 탐색을 마친 뒤에는 백트래킹(visited[i] = false)을 수행하여, 다른 방문 순서(경로)에서도 해당 던전을 다시 탐색할 수 있도록 복구.
 * 
 * [시간 복잡도] O(N!) - 최대 8!(= 40,320)가지의 경수를 탐색.
 */
class Solution {
    static boolean[] visited;
    static int maxCnt = 0;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        
        dfs(k, 0, dungeons);
        
        return maxCnt;
    }
    
    public void dfs(int currK, int cnt, int[][] dungeons) {
        maxCnt = Math.max(maxCnt, cnt);
        
        for (int i = 0; i < dungeons.length; i++) {
            int minFati = dungeons[i][0]; // 최소 필요 피로도
            int useFati = dungeons[i][1]; // 소모 필요도
            
            if (!visited[i] && currK >= minFati) {
                visited[i] = true;
                
                dfs(currK - useFati, cnt + 1, dungeons);
                
                visited[i] = false; // 방문상태 초기화
            }
        }
    }
}