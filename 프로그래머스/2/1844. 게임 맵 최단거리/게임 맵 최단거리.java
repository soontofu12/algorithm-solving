import java.util.*;
/*
 * [문제 정보] 게임 맵 최단거리 (Level 2)
 * [사용 알고리즘] BFS (너비 우선 탐색)
 * 
 * [풀이 핵심]
 * 1. (0,0)에서 (N-1, M-1)까지 가는 '최단 거리'를 구해야 하므로 BFS를 사용.
 * 2. Queue에 [현재 행, 현재 열, 이동 거리]를 배열로 저장하여 관리.
 * 3. 큐에 새로운 좌표를 넣을 때(offer) 즉시 방문 처리(visited=true)를 해두어 중복 투입 및 메모리 초과 방지.
 * 
 * [시간 복잡도] O(N * M) - 맵의 모든 칸을 최대 한 번씩 방문
 */
class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    
    public int solution(int[][] maps) {
        visited = new boolean[maps.length][maps[0].length];
        
        return bfs(maps, 0, 0);
    }
    
    public int bfs(int[][] maps, int startR, int startC) {
        int N = maps.length;
        int M = maps[0].length;
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startR, startC, 1});
        visited[startR][startC] = true;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];
            
            // 도착 조건 설정
            if (r == N - 1 && c == M - 1) return dist;
            
            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];
                
                // 경계선, 이동 가능 여부 체크
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (maps[nr][nc] == 0 || visited[nr][nc] == true) continue;
                
                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, dist + 1});
            }
        }
        // 목적지에 도달하지 못하는 경우
        return -1;
    }
}