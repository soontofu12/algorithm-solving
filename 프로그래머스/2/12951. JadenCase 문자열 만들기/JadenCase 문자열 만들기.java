class Solution {
    public String solution(String s) {
        StringBuilder answerSb = new StringBuilder();
        char[] cArr = s.toCharArray();
        boolean isFirst = true;
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] == ' ') {
                isFirst = true;
            }else if (isFirst) {
                if (cArr[i] >= 97 && cArr[i] <= 122) { // 소문자 처리
                    cArr[i] = (char)(cArr[i] - 32);
                }
                isFirst = false;
            } else {
                if (cArr[i] >= 65 && cArr[i] <= 90) { // 대문자 처리
                    cArr[i] = (char)(cArr[i] + 32);
                }
            }
        }
        for (int i = 0; i < cArr.length; i++) {
            answerSb.append(cArr[i]);
        }
        
        String answer = answerSb.toString();
        return answer;
    }
}