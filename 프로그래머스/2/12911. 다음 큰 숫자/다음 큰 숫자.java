class Solution {
    public int solution(int n) {
        int answer = n;
        int cnt = Integer.bitCount(n);
        while (true) {
            answer = ++answer;
            int ansCnt = Integer.bitCount(answer);
            if (cnt == ansCnt)
                break;
        }
        
        return answer;
    }
}