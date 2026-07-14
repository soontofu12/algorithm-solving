import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        int a = 0, b = 0;
        while (b != B.length) {
            if (A[a] < B[b]) {
                a++;
                b++;
                answer++;
            }else if (A[a] >= B[b])
                b++;
        }
        
        
        return answer;
    }
}