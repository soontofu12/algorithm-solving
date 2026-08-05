import java.util.*;
/*
 * [문제 정보] 가장 먼 노드 (Level 3)
 * [사용 알고리즘] BFS, 그래프 탐색
 * 
 * [풀이 핵심]
 * 1. 노드 수가 최대 20,000개이므로 메모리와 시간 효율을 위해 인접 리스트(ArrayList[]) 형태로 간선 정보를 저장.
 * 2. 1번 노드에서 출발하여 모든 간선의 가중치가 1인 최단 거리를 구하므로 BFS 탐색이 적합.
 * 3. 별도의 visited 배열 없이 dist 배열의 초기값(0)을 활용해 방문 여부 체크와 최단 거리 갱신을 동시에 처리.
 * 4. BFS 완료 후 dist 배열에서 구한 최장 거리(maxDist)와 같은 값을 가지는 노드의 개수를 카운트하여 반환.
 * 
 * [시간 복잡도] O(V + E) -> V: 노드 수(<= 20,000), E: 간선 수(<= 50,000)
 */
class Solution {
    ArrayList<Integer>[] adj;
    
    public int solution(int n, int[][] edge) {
        adj = new ArrayList[n + 1];
        
        // 인접 리스트 초기화
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            int u = e[0];
            int v = e[1];
            
            adj[u].add(v);
            adj[v].add(u);
        }
        
        return bfs(n);
    }
    private int bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);  // 1번 노드에서 출발
        
        // dist[i]: 1번 노드에서 i번 노드까지의 최단 거리
        // 거리 계산, 방문 체크(0 이상이면 방문한 것)
        int[] dist = new int[n + 1];
        dist[1] = 1;  // 1번 노드에서 출발
        
        int maxDist = 0;
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            
            for (int next : adj[curr]) {
                if (dist[next] == 0) {
                    dist[next] = dist[curr] + 1;  // 거리 갱신
                    maxDist = Math.max(maxDist, dist[next]);  // 최장 거리 갱신
                    q.offer(next);
                }
            }
        }
        
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == maxDist)
                cnt++;
        }
        
        return cnt;
    }
}