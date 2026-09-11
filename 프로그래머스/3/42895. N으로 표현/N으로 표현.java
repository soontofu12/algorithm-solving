import java.util.*;
/*
 * [문제 정보] N으로 표현 (Level 3)
 * [사용 알고리즘] DP(동적 계획법), HashSet
 *
 * [풀이 핵심]
 * 1. dp[i]에 N을 정확히 i번 사용해서 만들 수 있는 모든 값을 저장.
 * 2. N, NN, NNN처럼 이어붙인 숫자를 각 dp[i]에 추가.
 * 3. dp[j]와 dp[i-j]의 값을 사칙연산하여 dp[i]에 저장.
 * 4. number가 처음 포함되는 i를 반환하고, 8번까지 없으면 -1 반환.
 *
 * [시간 복잡도] O(8 * K^2) (= 각 단계에서 두 Set의 모든 조합 탐색, K: Set 크기)
 */
class Solution {
    public int solution(int N, int number) {
        Set<Integer>[] dp = new HashSet[9];

        for (int i = 0; i <= 8; i++) {
            dp[i] = new HashSet<>();
        }

        int concat = 0;

        for (int i = 1; i <= 8; i++) {
            concat = concat * 10 + N;
            dp[i].add(concat);

            if (dp[i].contains(number)) return i;  // number가 만들어졌다면 i(= N의 개수) 반환

            for (int j = 1; j < i; j++) {
                for (int a : dp[j]) {
                    for (int b : dp[i - j]) {
                        dp[i].add(a + b);
                        dp[i].add(a - b);
                        dp[i].add(b - a);
                        dp[i].add(a * b);

                        if (b != 0) dp[i].add(a / b);
                        if (a != 0) dp[i].add(b / a);
                    }
                }
            }

            if (dp[i].contains(number)) return i;  // number가 만들어졌다면 i(= N의 개수) 반환
        }

        return -1;
    }
}