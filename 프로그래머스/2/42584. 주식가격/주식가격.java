import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] time = new int[n];
        
        for (int i = 0; i < n-1; i++) {
            int cnt = 0;
            boolean isDec = false;
            for (int j = i+1; j < n; j++) {
                if (prices[i] <= prices[j])
                    cnt++;
                else {
                    cnt++;
                    time[i] = cnt;
                    isDec = true;
                    break;
                }
            }
            if (!isDec) time[i] = cnt;
        }        
        
        return time;
    }
}