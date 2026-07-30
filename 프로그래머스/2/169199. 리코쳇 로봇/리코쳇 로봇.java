import java.util.*;
/*
 * [문제 정보] 리코쳇 로봇 (Level 2)
 * [사용 알고리즘] BFS
 * 
 * [풀이 핵심]
 * 1. 일반 BFS와 달리, 장애물('D')이나 벽을 만날 때까지 한 방향으로 계속 미끄러짐 (while문 활용).
 * 2. 미끄러져 '최종 멈춘 위치'를 기준으로 방문 체크(visited)를 수행.
 * 3. 멈춘 위치가 'G'에 도착하면 그때까지의 이동 횟수를 반환.
 * 4. 큐가 빌 때까지 'G'에 도착하지 못하면 -1 반환.
 * 
 * [시간 복잡도] O(R * C) - 보드의 모든 칸을 최대 한 번씩 방문하므로 여유로움.
 */
class Solution {
    static char[][] cMap;
    static boolean[][] visited;
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(String[] board) {
        cMap = new char[board.length][board[0].length()];
        visited = new boolean[cMap.length][cMap[0].length];  // 방문 처리
        
        for (int i = 0; i < board.length; i++) {
            cMap[i] = board[i].toCharArray();
        }
        
        for (int i = 0; i < cMap.length; i++) {
            for (int j = 0; j < cMap[0].length; j++) {
                if (cMap[i][j] == 'R') {
                    return bfs(i, j);
                }
            }
        }
        
        return -1;  // 도착할 수 없는 경우
    }
    public int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c, 0});  // 0: 이동 횟수
        visited[r][c] = true;
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int currCnt = curr[2];  // 현재 이동 횟수 업데이트
            
            for (int i = 0; i < 4; i++) {
                int nr = curr[0];
                int nc = curr[1];
                
                // 경계 조건
                while (nr + dr[i] >= 0 && nr + dr[i] < cMap.length && nc + dc[i] >= 0 && nc + dc[i] < cMap[0].length
                        && cMap[nr + dr[i]][nc + dc[i]] != 'D') {
                    nr += dr[i];
                    nc += dc[i];
                }
                
                if (cMap[nr][nc] == 'G')
                    return currCnt + 1;
                
                if (visited[nr][nc]) continue;
                
                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, currCnt + 1});
            }
        }
        
        return -1;  // 도착할 수 없는 경우
    }
}