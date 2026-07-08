import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) {
                q.offer(arr[i]);
            }
        }
        
        int[] answer;
        if (!q.isEmpty()) {
            answer = new int[q.size()];
            for (int i = 0; i < answer.length; i++) {
                answer[i] = q.poll();
            }
            Arrays.sort(answer); 
        } else {
            answer = new int[] {-1};
        }
        
        return answer;
    }
}