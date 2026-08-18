import java.util.*;
/*
 * [문제 정보] 큰 수 만들기 (Level 2)
 * [사용 알고리즘] 그리디(Greedy), 단조 스택(Monotonic Stack)
 * 
 * [풀이 핵심]
 * 1. 앞자리에 최대한 큰 숫자가 올수록 전체 숫자가 커지는 그리디 원리 적용.
 * 2. 현재 숫자(c)가 스택의 맨 위보다 크고, 아직 제거 횟수가 남아있다면(k > 0)
 *    - 조건을 만족하는 동안 이전 숫자를 연속해서 pop()하며 제거 횟수(k)를 차감.
 * 3. 탐색이 끝난 후에도 숫자가 다 지워지지 않았다면
 *    - 스택의 밑바닥부터 목표 길이(number.length() - k)만큼만 잘라서 정답 생성.
 * 
 * [시간 복잡도] O(N) - 각 문자는 스택에 최대 1번 push, 최대 1번 pop되므로 O(N).
 */
class Solution {
    public String solution(String number, int k) {
        Stack<Character> s = new Stack<>();
        char[] cArr = number.toCharArray();
        
        for (char c : cArr) {
            // 나보다 작은 앞 숫자는 제거 횟수가 남아있는 한 연속 제거
            while (!s.isEmpty() && k > 0 && s.peek() < c) {
                s.pop();
                k--;
            }
            s.push(c);
        }
        
        // k가 남아있다면 뒤에서 그만큼 제외하여 결과로 조립
        StringBuilder sb = new StringBuilder();
        int finalLen = s.size() - k;
        
        for (int i = 0; i < finalLen; i++) {
            sb.append(s.get(i));
        }
        
        return sb.toString();
    }
}