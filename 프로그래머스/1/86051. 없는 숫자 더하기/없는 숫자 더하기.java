class Solution {
    public int solution(int[] numbers) {
        int totalSum = 45;
        int currSum = 0;
        for (int i = 0; i < numbers.length; i++) {
            currSum += numbers[i];
        }
        int answer = totalSum - currSum;
        return answer;
    }
}