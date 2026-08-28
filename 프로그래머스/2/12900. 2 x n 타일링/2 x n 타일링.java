import java.util.*;
/*
 * [문제 정보] 2 x n 타일링 (Level 2)
 * [사용 알고리즘] DP(동적 계획법)
 *
 * [풀이 핵심]
 * 1. arr[i]를 2 x i 직사각형을 채우는 경우의 수로 정의.
 * 2. i = 1, 2일 때는 각각 1, 2로 초기화.
 * 3. i >= 3부터는 세로 타일 1개를 놓는 경우와 가로 타일 2개를 놓는 경우를 더해
 *    arr[i] = arr[i-1] + arr[i-2]로 계산.
 * 4. 수가 커지므로 매 계산마다 1,000,000,007로 나눈 나머지를 저장.
 *
 * [시간 복잡도] O(N) (= 1부터 N까지 한 번 순회)
 */
class Solution {
    public int solution(int n) {
        int[] arr = new int[n+1];
        for (int i = 1; i < arr.length; i++) {
            if (i <= 2) {
                arr[i] = i;
            } else {
                arr[i] = (arr[i-1] + arr[i-2]) % 1000000007;
            }
        }
        
        return arr[n];
    }
}