import java.util.*;
/*
 * [문제 정보] 여행경로 (Level 3)
 * [사용 알고리즘] DFS, 백트래킹
 * 
 * [풀이 핵심]
 * 1. 알파벳 순서가 앞서는 경로를 먼저 탐색하기 위해 tickets 배열을 도착지(1번 인덱스) 기준으로 사전순 정렬.
 * 2. 항상 ICN 공항에서 출발하며, 사용한 티켓 수가 전체 티켓 수와 같아질 때까지 DFS 탐색.
 * 3. 탐색 시 route 문자열에 공백(" ")을 구분자로 공항을 이어붙임 (백트래킹 시 자동 복구).
 * 4. 정렬을 했기 때문에, 가장 먼저 완주하는 경로가 곧 정답이 되므로 answer 변수에 저장 후 탐색 종료.
 * 
 * [시간 복잡도] O(N log N + N!) - N: 티켓의 개수 (최대 10,000개이지만 실제 가지치기로 인해 속도가 매우 빠름)
 */
class Solution {
    static boolean[] visited;
    static String answer;  // 경로
    
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        
        // (도착지 기준)알파벳 순으로 정렬
        Arrays.sort(tickets, (o1, o2) -> o1[1].compareTo(o2[1]));
        
        answer = "";
        dfs("ICN", "ICN", 0, tickets);
        
        return answer.split(" ");
    }
    
    public void dfs(String currLoc, String route, int cnt, String[][] tickets) {
        if (!answer.isEmpty()) return;
        
        if (cnt == tickets.length) {
            answer = route;
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(currLoc)) {
                visited[i] = true;
                
                dfs(tickets[i][1], route + " " + tickets[i][1], cnt + 1, tickets);
                
                visited[i] = false;
            }
        }
    }
}