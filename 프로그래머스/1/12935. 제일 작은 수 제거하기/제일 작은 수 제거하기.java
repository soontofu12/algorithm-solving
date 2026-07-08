class Solution {
    public int[] solution(int[] arr) {
        int[] answer = new int[arr.length - 1];
        if (arr.length > 1) {
            int min = Integer.MAX_VALUE;
            int minIdx = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < min) {
                    min = arr[i];
                    minIdx = i;
                }
            }
            int idx = 0;
            for (int i = 0; i < arr.length; i++) {
                if (i != minIdx) {
                    answer[idx] = arr[i];
                    idx++;
                }
            }
        }else {
            answer = new int[] {-1};
        }
        
        return answer;
    }
}