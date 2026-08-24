import java.util.*;
/*
 * [문제 정보] 조이스틱 (Level 2)
 * [사용 알고리즘] BFS(너비 우선 탐색), 비트마스킹
 * 
 * [풀이 핵심]
 * 1. 상하 조작 (알파벳 변경):
 *    - 각 위치의 문자가 'A'가 아닐 때 위로 이동(c - 'A') vs 아래로 이동('Z' - c + 1) 중 최솟값을 누적.
 *    - 방문해야 할 위치들을 targetMask의 해당 비트(1 << i)를 켜서 기록.
 * 2. 좌우 조작 (커서 최단 이동):
 *    - 'A'가 아닌 모든 인덱스를 방문하는 최소 조작 횟수를 BFS로 탐색.
 *    - 큐 상태: int[]{현재 인덱스, 지금까지 방문한 비트마스크, 이동 횟수}
 *    - visited[len][1 << len] 2차원 배열로 (현재 위치, 방문 조합) 상태의 중복 탐색 차단.
 * 3. 종료 조건:
 *    - (mask & targetMask) == targetMask 를 만족하는 최초 도달 상태의 moves 반환.
 * 
 * [시간 복잡도] O(N * 2^N) - 문자열 길이 N <= 20이므로 모든 상태 공간을 빠르게 탐색 가능.
 */
class Solution {
    public int solution(String name) {
        int targetMask = 0;
        int cnt = 0;
        
        // 1. 알파벳 변환 횟수 누적 및 방문해야 할 타깃 인덱스 비트 마킹
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c != 'A') {
                cnt += Math.min(c - 'A', 'Z' - c + 1);
                targetMask |= (1 << i); // i번째 비트 켜기
            }
        }
        
        // 바꿀 문자가 없다면(전부 'A') 조작 횟수는 0
        if (targetMask == 0) return 0;
        
        // 상하 조작(알파벳 변환) 횟수 + 좌우 최소 이동 횟수 반환
        return cnt + bfs(name.length(), targetMask);
    }
    static int bfs(int len, int targetMask) {
        Queue<int[]> q = new LinkedList<>();  // int[] {idx, mask, moves}
        boolean[][] visited = new boolean[len][1 << len];
        
        // 시작 지점 (0번 index, 0번 비트 ON: 1 << 0 = 1, 이동 횟수 0)
        q.offer(new int[]{0, 1, 0});
        visited[0][1] = true;
        
        while (!q.isEmpty()) {
            int [] curr = q.poll();
            int idx = curr[0];
            int mask = curr[1];
            int moves = curr[2];
            
            // 목표로 하는 'A'가 아닌 모든 인덱스를 방문했다면 이동 횟수 반환
            if ((mask & targetMask) == targetMask)
                return moves;
            
            // 오른쪽으로 이동
            int right = (idx + 1) % len;
            int rightMask = mask | (1 << right);
            if (!visited[right][rightMask]) {
                visited[right][rightMask] = true;
                q.offer(new int[] {right, rightMask, moves + 1});
            }
            
            // 왼쪽으로 이동
            int left = (idx - 1 + len) % len;
            int leftMask = mask | (1 << left);
            if (!visited[left][leftMask]) {
                visited[left][leftMask] = true;
                q.offer(new int[] {left, leftMask, moves + 1});
            }
        }
        
        return 0;
    }
}