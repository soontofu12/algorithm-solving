/*
 * [문제 정보] 다음 큰 숫자 (Level 2)
 * [사용 알고리즘] 완전탐색, 비트 연산
 *
 * [풀이 핵심]
 * 1. n의 이진수에서 1의 개수를 Integer.bitCount()로 계산.
 * 2. n보다 큰 수를 하나씩 증가시키며 각 수의 1의 개수를 확인.
 * 3. n과 1의 개수가 같은 첫 번째 숫자를 찾으면 반환.
 *
 * [시간 복잡도] O(K) (= 조건을 만족하는 다음 숫자까지 K개를 순차 탐색)
 */
class Solution {
    public int solution(int n) {
        int answer = n;
        
        // n의 이진수에서 1의 개수
        int cnt = Integer.bitCount(n);
        
        while (true) {
            answer++;
            
            // 현재 숫자의 이진수에서 1의 개수
            int ansCnt = Integer.bitCount(answer);
            if (cnt == ansCnt) break;
        }
        
        return answer;
    }
}