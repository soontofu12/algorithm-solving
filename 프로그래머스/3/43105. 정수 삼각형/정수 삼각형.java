import java.util.*;
/*
 * [문제 정보] 정수 삼각형 (Level 3)
 * [사용 알고리즘] 다이나믹 프로그래밍 (DP / Bottom-Up 역방향)
 * 
 * [풀이 핵심]
 * 1. 꼭대기에서 내려오는 방식은 (i, j)에서 양쪽 끝(j=0, j=i)의 인덱스 예외 처리가 필요하고 마지막 행에서 최댓값을 찾아야 함.
 * 2. 반대로 바닥의 바로 윗 행(lastRow - 1)부터 꼭대기(0행)까지 거꾸로 올라가며 DP를 수행하면:
 *    - 현재 칸(i, j)은 바로 아래 두 칸 (i+1, j)와 (i+1, j+1) 중 큰 값을 선택해 누적.
 *    - 인덱스 예외 처리가 필요 없음.
 * 3. 0행 0열에 도달했을 때의 값이 곧 전체 경로의 최댓값이 되므로 즉시 triangle[0][0] 반환.
 * 
 * [시간 복잡도] O(N^2) - 삼각형 내부의 전체 원소 개수(N*(N+1)/2)만큼만 순회하므로 약 125,000번 연산으로 매우 빠름.
 * [공간 복잡도] O(1) - 원본 triangle 배열을 In-place로 갱신하여 추가 메모리 사용 없음.
 */
class Solution {
    public int solution(int[][] triangle) {
        int lastRaw = triangle.length - 1;
        
        // 밑에서 두 번째 행부터 0행까지 역순으로 순회
        for (int i = lastRaw - 1; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                // 바로 아랫줄의 대각선 왼쪽(j), 대각선 오른쪽(j+1) 중 큰 값을 현재 칸에 누적
                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
            }
        }
        
        return triangle[0][0];  // 최상단의 누적값을 반환
    }
}