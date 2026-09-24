import java.util.*;
/*
 * [문제 정보] 주식가격 (Level 2)
 * [사용 알고리즘] Stack
 *
 * [풀이 핵심]
 * 1. Stack에 아직 가격이 떨어진 시점이 확정되지 않은 인덱스를 저장.
 * 2. 현재 가격이 Stack 최상단 인덱스의 가격보다 낮으면, 해당 인덱스를 pop하며 유지 시간을 계산.
 * 3. 유지 시간은 현재 인덱스 - 이전 인덱스로 계산.
 * 4. 반복문 종료 후 Stack에 남은 인덱스는 끝까지 가격이 떨어지지 않은 경우이므로 마지막 시점까지의 시간을 계산.
 *
 * [시간 복잡도] O(N) (= 각 인덱스가 Stack에 한 번 push되고 최대 한 번 pop됨)
 */
class Solution {
    public int[] solution(int[] prices) {
        int[] ans = new int[prices.length];
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            // 현재 시점에서 처음으로 가격이 떨어졌다면, 그 상황에 해당할 경우마다 시간을 계산
            while (!s.isEmpty() && prices[s.peek()] > prices[i]) {
                int prev = s.pop();
                ans[prev] = i - prev;
            }

            // 가격이 떨어지지 않았다면 인덱스 추가
            s.push(i);
        }

        // 끝까지 가격이 떨어지지 않은 인덱스 처리
        while (!s.isEmpty()) {
            int prev = s.pop();
            ans[prev] = (prices.length - 1) - prev;
        }

        return ans;
    }
}