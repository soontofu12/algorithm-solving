import java.util.*;
/*
 * [문제 정보] 가장 큰 정사각형 찾기 (Level 2)
 * [사용 알고리즘] DP(동적 계획법)
 *
 * [풀이 핵심]
 * 1. board[i][j]가 1이면 해당 칸을 오른쪽 아래 꼭짓점으로 하는 정사각형 크기를 계산.
 * 2. 왼쪽, 위, 왼쪽 위 값 중 최솟값 + 1을 현재 칸에 저장(정사각형의 한 변의 길이).
 * 3. 계산된 한 변의 최댓값을 구한 뒤 제곱하여 넓이를 반환.
 *
 * [시간 복잡도] O(N * M) (= N행 M열 전체 순회)
 */
class Solution {
    public int solution(int [][]board) {
        int ans = 0;
        
        // 첫 번째 행과 열을 포함해 1이 존재하는지 확인
        outer: for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    ans = 1;
                    break outer;
                }
            }
        }
        
        // 현재 칸이 정사각형의 우측 하단 꼭짓점이 되는 정사각형의 한 변의 길이를 구함
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = Math.min(board[i][j-1], 
                                           Math.min(board[i-1][j-1], board[i-1][j])) + 1;
                    
                    ans = Math.max(ans, board[i][j]);
                }
            }
        }
        
        return ans * ans;
    }
}