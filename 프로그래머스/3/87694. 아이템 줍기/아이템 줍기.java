import java.util.*;
/*
 * [문제 정보] 아이템 줍기 (Level 3)
 * [사용 알고리즘] BFS (너비 우선 탐색), 2D 그리드 스케일링
 * 
 * [풀이 핵심]
 * 1. 1x1 크기의 ㄷ자 형태 경로에서 BFS 탐색 시 테두리를 건너뛰는 지름길 오류를 방지하기 위해 전체 좌표를 '2배'로 확대.
 * 2. 2차원 배열(map)에 사각형 영역을 칠할 때, 테두리는 1, 내부는 2로 구분하여 채움.
 *    단, 이미 다른 사각형의 내부(2)로 지정된 위치는 덮어쓰지 않음으로써 순수 바깥 테두리(1)만 남김
 * 3. 출발지(characterX, characterY)부터 목적지(itemX, itemY)까지 '2배' 확대된 좌표를 기준으로 BFS 탐색 진행.
 * 4. 목적지 도달 시 이동 거리(dist)를 2로 나누어 원래 크기의 이동 거리를 반환.
 * 
 * [시간 복잡도] O(N * M) - N, M: 2배 확대된 좌표 크기 (100 x 100 = 약 10,000번 연산으로 매우 빠름)
 */
class Solution {
    static int[][] map;
    static boolean[][] visited;
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 1x1 건너뜀 방지를 위해 좌표판을 2배로 확대 (최대 50 -> 100)
        map = new int[101][101];
        visited = new boolean[101][101];
        
        // 모든 사각형 영역에 대해 테두리(1), 내부(2)로 채움
        for (int[] r : rectangle) {
            int x1 = r[0] * 2, y1 = r[1] * 2;
            int x2 = r[2] * 2, y2 = r[3] * 2;
            
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    // 이미 다른 사각형의 내부에 해당하는 위치(2)면 덮어쓰지 않음
                    if (map[i][j] == 2) continue;
                    
                    // 테두리면 1, 내부면 2
                    if (i == x1 || i == x2 || j == y1 || j == y2) {
                        map[i][j] = 1;
                    } else {
                        map[i][j] = 2;
                    }
                }
            }
        }
        
        // bfs 함수 호출 시 출발지와 목적지도 2배 확대
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }
    
    private int bfs(int cX, int cY, int iX, int iY) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {cX, cY, 0});  // {x, y, dist}
        visited[cX][cY] = true;
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            int dist = curr[2];
            
            // 목적지에 도착한 경우 최종 거리 반환
            if (x == iX && y == iY) {
                return dist / 2;  // 최종 최단 거리를 2로 나누어 반환
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dr[i];
                int ny = y + dc[i];
                
                if (nx < 1 || nx > 100 || ny < 1 || ny > 100) continue;  // 경계 체크
                if (map[nx][ny] != 1 || visited[nx][ny]) continue;  // 테투리(1)가 아니거나 / 방문했거나
                
                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny, dist + 1});
            }
        }
        
        return 0;
    }
}