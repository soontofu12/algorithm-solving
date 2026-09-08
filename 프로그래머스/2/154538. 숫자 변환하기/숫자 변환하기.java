import java.util.*;
/*
 * [문제 정보] 숫자 변환하기 (Level 2)
 * [사용 알고리즘] BFS(너비 우선 탐색)
 *
 * [풀이 핵심]
 * 1. 현재 숫자에서 +n, *2, *3 연산으로 만들 수 있는 값을 BFS로 탐색.
 * 2. y를 초과하는 값 또는 이미 방문한 값은 확인하지 않음.
 * 3. BFS에서 y에 처음 도달했을 때의 연산 횟수(cnt)를 반환.
 * 4. x를 y로 만들 수 없다면 -1 반환.
 *
 * [시간 복잡도] O(Y) (= 1부터 Y까지의 값을 최대 한 번씩 방문)
 */
class Solution {
    static boolean[] visited;
    
    public int solution(int x, int y, int n) {
        visited = new boolean[y+1];
        
        return bfs(x, y, n);
    }
    
    private int bfs (int x, int y, int n) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, 0});  // {현재 숫자, 연산 횟수}
        visited[x] = true;
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int num = curr[0];
            int cnt = curr[1];
            
            // y에 도달했을 때의 최소 연산 횟수(cnt) 반환
            if (num == y) return cnt;
            
            int[] oper = new int[] {num + n, num * 2, num * 3};
            for (int op : oper) {
                if (op > y) continue;
                if (visited[op]) continue;
                
                visited[op] = true;
                q.offer(new int[] {op, cnt+1});
            }
        }
        return -1;
    }
}