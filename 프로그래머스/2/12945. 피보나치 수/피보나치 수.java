import java.util.*;
/*
 * [문제 정보] 피보나치 수 (Level 2)
 * [사용 알고리즘] DP(Dynamic Programming)
 * 
 * [풀이 핵심]
 * 1. N이 최대 100,000으로 매우 크므로 DP 반복문 O(N) 활용.
 * 2. 마지막에만 % 1234567을 수행하면 계산 중간에 Long 범위(<=9,223,372,036,854,775,807)를 초과해 오버플로우가 발생함.
 * 3. 따라서 더하는 매 순간마다 '% 1234567'을 적용해 안전하게 int 범위 내에서 계산.
 * 
 * [시간 복잡도] O(N)
 */
class Solution {
    public int solution(int n) {
        int[] arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (i < 2) arr[i] = i;
            else arr[i] = (arr[i-2] + arr[i-1]) % 1234567;  // 1234567로 나눈 나머지를 배열에 저장
        }
        
        return arr[n];
    }
}