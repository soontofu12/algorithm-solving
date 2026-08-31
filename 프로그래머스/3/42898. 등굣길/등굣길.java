import java.util.*;
/*
 * [문제 정보] 등굣길 (Level 3)
 * [사용 알고리즘] DP(동적 계획법)
 *
 * [풀이 핵심]
 * 1. map[i][j]에 (i, j)까지 도달하는 경로의 수를 저장.
 * 2. 물웅덩이는 별도의 boolean 배열로 표시하여 계산에서 제외.
 * 3. 현재 칸의 경로 수는 위쪽 + 왼쪽 경로 수로 계산.
 * 4. 값이 커질 수 있으므로 매 계산마다 1,000,000,007로 나눈 나머지를 저장.
 *
 * [시간 복잡도] O(N * M) (= 물웅덩이 처리 O(P) + 전체 격자 순회 O(N * M))
 */
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n+1][m+1];
        boolean[][] pool = new boolean[n+1][m+1];
        for (int i = 0; i < puddles.length; i++) {
            pool[puddles[i][1]][puddles[i][0]] = true;  // true: 물에 잠긴 지역
        }
        
        map[1][1] = 1;  // 집 위치를 1로 초기화
        
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[0].length; j++) {
                // 출발 지점과 물웅덩이는 건너뜀
                if ((i == 1 && j == 1) || pool[i][j]) continue;

                // 위쪽 + 왼쪽에서 오는 경로 수
                map[i][j] = (map[i - 1][j] + map[i][j - 1]) % 1000000007;
            }
        }
        
        return map[n][m];
    }
}