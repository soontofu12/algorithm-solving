import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        int len = files.length;
        
        /*
         * splitName[][0]: HEAD
         * splitName[][1]: NUMBER
         * splitName[][2]: TAIL
         */
        String[][] splitName = new String[len][3];
        StringBuilder sb = new StringBuilder();
        
        // HEAD, NUMBER, TAIL 나누기
        for (int i = 0; i < len; i++) {
            String str = files[i];
            
            int startIdx = 0;
            int endIdx = 0;
            
            // HEAD
            while (true) {
                char nextC = str.charAt(endIdx);
                if (Character.isDigit(nextC)) {
                    break;
                }
                endIdx++;
            }
            
            splitName[i][0] = str.substring(startIdx, endIdx);
            startIdx = endIdx;
            
            // NUMBER
            while (endIdx < str.length()) {
                char nextC = str.charAt(endIdx);

                if (!Character.isDigit(nextC)) {
                    break;
                }

                endIdx++;
            }
            
            splitName[i][1] = str.substring(startIdx, endIdx);
            startIdx = endIdx;
            
            // TAIL
            splitName[i][2] = str.substring(startIdx);
        }
        
        // 정렬기준: HEAD -> NUMBER, 둘 다 같다면 유지
        Arrays.sort(splitName, (a, b) -> {
            if (a[0].toLowerCase().equals(b[0].toLowerCase())) {
                return Integer.compare(Integer.parseInt(a[1]), Integer.parseInt(b[1]));
            }
            
            return a[0].compareToIgnoreCase(b[0]);
        });
        
        String[] ans = new String[len];
        for (int i = 0; i < len; i++) {
            ans[i] = splitName[i][0] + splitName[i][1] + splitName[i][2];
        }
        
        return ans;
    }
}