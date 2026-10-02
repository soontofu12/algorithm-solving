import java.util.*;
/*
 * [문제 정보] 요격 시스템 (Level 2)
 * [사용 알고리즘] 그리디, 정렬
 *
 * [풀이 핵심]
 * 1. 각 미사일 구간을 끝점(end) 기준 오름차순으로 정렬.
 * 2. 가장 먼저 끝나는 미사일의 end 직전에 요격한다고 생각하고 currEnd에 저장.
 * 3. 다음 미사일의 start가 currEnd보다 작으면 기존 요격으로 함께 처리 가능.
 * 4. start가 currEnd 이상이면 기존 요격으로 처리할 수 없으므로 새로운 요격 미사일을 발사하고 currEnd를 갱신.
 * 5. 구간은 (s, e) 형태의 개구간이므로 start == currEnd인 경우에도 새 요격이 필요.
 *
 * [시간 복잡도] O(N log N)
 * (= targets 정렬 O(N log N) + 전체 순회 O(N))
 */
class Solution {
    public int solution(int[][] targets) {
        // end 기준 오름차순 정렬
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));
        
        int ans = 1;  // 요격 미사일 개수, 첫 미사일은 요격에 포함
        int currEnd = targets[0][1];  // 첫 미사일의 end 지점
        
        for (int i = 1; i < targets.length; i++) {
            int nextStart = targets[i][0];
            int nextEnd = targets[i][1];
            
            // 기존 요격 위치로 함께 요격 가능한 경우
            if (nextStart < currEnd)
                continue;
            
            // 기존 요격으로 처리할 수 없으므로 새로운 요격 미사일 발사
            else {
                currEnd = nextEnd;
                ans++;
            }
        }
        
        return ans;
    }
}