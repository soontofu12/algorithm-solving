import java.util.*;
/*
 * [문제 정보] 무인도 여행 (Level 2)
 * [사용 알고리즘] BFS
 * 
 * [풀이 핵심]
 * 1. 2차원 지도를 탐색하며 바다('X')가 아닌 무인도('1'~'9')를 발견하면 BFS 시작.
 * 2. BFS 탐색을 진행하며 연결된 무인도 영역의 모든 식량 값을 합산.
 * 3. 각 무인도별 식량 합을 List에 저장한 뒤 오름차순으로 정렬.
 * 4. 지낼 수 있는 무인도가 없다면(List가 비어있다면) -1을 담아 반환.
 * 
 * [시간 복잡도] O(R * C) - 지도의 행(R)과 열(C)의 크기만큼 방문 처리하므로 N=100*100일 때 최대 10,000번 연산으로 매우 여유로움.
 */
class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static char[][] cMaps;  // 지도를 cMaps라는 새로운 문자 행렬에 담음
    static boolean[][] visited;  // 방문 체크
    static List<Integer> answer;
    
    static int currSum;  // 현재 방문 중인 섬의 방문 가능한 날의 수
    
    public List<Integer> solution(String[] maps) {
        cMaps = new char[maps.length][maps[0].length()];
        
        for (int i = 0; i < maps.length; i++) {
            cMaps[i] = maps[i].toCharArray();
        }
        
        visited = new boolean[cMaps.length][cMaps[0].length];
        answer = new ArrayList<>();
        
        for (int i = 0; i < cMaps.length; i++) {
            for (int j = 0; j < cMaps[0].length; j++) {
                if (cMaps[i][j] != 'X' && !visited[i][j]) {
                    currSum = 0;  // 초기화
                    bfs(i, j, cMaps);
                    answer.add(currSum);
                }
            }
        }
        
        if (answer.isEmpty())  // 방문 가능한 무인도가 없다면
            answer.add(-1);
        else {
            // 있다면 오름차순 정렬
            Collections.sort(answer, (o1, o2) -> o1 - o2);
        }
        
        return answer;
    }
    
    public void bfs(int r, int c, char[][] cMaps) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c});
        currSum += cMaps[r][c] - 48;
        visited[r][c] = true;
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];
                
                if (nr < 0 || nr >= cMaps.length || nc < 0 || nc >= cMaps[0].length) continue;
                if (cMaps[nr][nc] == 'X' || visited[nr][nc]) continue;
                
                currSum += cMaps[nr][nc] - 48;
                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc});
            }
        }
    }
}