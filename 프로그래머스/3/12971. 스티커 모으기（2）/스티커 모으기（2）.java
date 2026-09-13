import java.util.*;
/*
 * [문제 정보] 스티커 모으기(2) (Level 3)
 * [사용 알고리즘] DP(동적 계획법)
 *
 * [풀이 핵심]
 * 1. 원형 구조이므로 0번째를 선택하는 경우와 선택하지 않는 경우로 분리.
 * 2. 현재 스티커를 선택하지 않는 경우 dp[i-1],
 *    선택하는 경우 dp[i-2] + sticker[i] 중 최댓값을 저장.
 * 3. 0번째를 선택하면 마지막 스티커는 제외하고,
 *    0번째를 선택하지 않으면 마지막 스티커까지 탐색.
 * 4. 두 경우의 최댓값 중 큰 값을 반환.
 *
 * [시간 복잡도] O(N) (= 두 DP 배열을 각각 한 번씩 순회)
 */
class Solution {
    public int solution(int sticker[]) {
        int len = sticker.length;
        int[] dp0 = new int[len];  // 0번째를 선택하는 경우
        int[] dp1 = new int[len];  // 1번째를 선택하는 경우
        
        // 길이가 1인 경우
        if (len == 1) return sticker[0];
        
        // 0번째를 선택하는 경우
        dp0[0] = sticker[0];
        dp0[1] = sticker[0];
        for (int i = 2; i < len-1; i++) {
            dp0[i] = Math.max(dp0[i-1], dp0[i-2] + sticker[i]);
        }
        
        // 0번째를 선택하지 않고 1번째를 선택하는 경우
        dp1[0] = 0;
        dp1[1] = sticker[1];
        for (int i = 2; i < len; i++) {
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + sticker[i]);
        }
        
        return Math.max(dp0[len-2], dp1[len-1]);
    }
}