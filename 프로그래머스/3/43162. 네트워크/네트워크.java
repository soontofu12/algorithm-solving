import java.util.*;
/*
 * [문제 정보] 프로그래머스 - 네트워크 (Level 3)
 * [사용 알고리즘] BFS (너비 우선 탐색)
 * 
 * [풀이 핵심]
 * 1. 아직 방문하지 않은 컴퓨터를 만나면 새로운 네트워크(그룹)의 시작이므로 answer + 1.
 * 2. Queue를 이용하여 해당 컴퓨터와 직접/간접적으로 연결된 모든 컴퓨터를 탐색하며 방문 처리(visited=true).
 * 3. 인접 행렬(computers[curr][next] == 1)을 통해 연결 여부를 확인.
 * 
 * [시간 복잡도] O(N^2) - N개의 컴퓨터에 대해 각각 N번씩 연결 관계 확인
 */
class Solution {
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0; 
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++;
                bfs(i, n, computers);
            }
        }
        
        return answer;
    }
    
    public void bfs(int start, int n, int[][] computers) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            
            for (int other = 0; other < n; other++) {
                if (curr != other && computers[curr][other] == 1 && !visited[other]) {
                    visited[other] = true;
                    q.offer(other);
                }
            }
        }
    }
}