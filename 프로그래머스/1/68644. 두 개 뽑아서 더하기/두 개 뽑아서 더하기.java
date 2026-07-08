import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numbers.length-1; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                if (q.isEmpty()) {
                    q.offer(numbers[i] + numbers[j]);
                }else if (!q.contains(numbers[i] + numbers[j])) {
                    q.offer(numbers[i] + numbers[j]);
                }
            }
        }
        int[] answer = new int[q.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = q.poll();
        }
        Arrays.sort(answer);
        
        return answer;
    }
}