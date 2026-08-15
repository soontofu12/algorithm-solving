import java.util.*;

/*
 * [문제 정보] 땅따먹기 (Level 2)
 * [사용 알고리즘] 다이나믹 프로그래밍 (DP / Bottom-Up)
 * 
 * [풀이 핵심]
 * 1. 매 행마다 단순히 최댓값을 하나만 고르는 그리디(Greedy) 방식은 이후 행의 더 큰 값을 놓치는 반례가 발생함.
 * 2. i번째 행의 각 열(0~3)에 도달했을 때의 최대 점수를 구하기 위해 바로 이전의 행(i-1)에서 같은 열을 제외한 3개 열의 최댓값을 누적하여 갱신.
 * 3. 1번째 행부터 마지막 행까지 누적 계산을 완료한 후, 마지막 행의 4개 열 중 최댓값을 반환.
 * 
 * [시간 복잡도] O(N) - N이 최대 100,000일 때 100,000 * 4 = 약 400,000번 연산으로 매우 빠름.
 */
class Solution {
    int solution(int[][] land) {
        // 1번째 행부터 마지막 행까지 내려가며 이전 행의 다른 열 최댓값을 누적
        for (int i = 1; i < land.length; i++) {
            land[i][0] += Math.max(land[i - 1][1], Math.max(land[i - 1][2], land[i - 1][3]));
            land[i][1] += Math.max(land[i - 1][0], Math.max(land[i - 1][2], land[i - 1][3]));
            land[i][2] += Math.max(land[i - 1][0], Math.max(land[i - 1][1], land[i - 1][3]));
            land[i][3] += Math.max(land[i - 1][0], Math.max(land[i - 1][1], land[i - 1][2]));
        }

        // 마지막 행의 4개 열 중 가장 큰 값이 최댓값
        int lastRow = land.length - 1;
        return Math.max(land[lastRow][0], Math.max(land[lastRow][1], Math.max(land[lastRow][2], land[lastRow][3])));
    }
}