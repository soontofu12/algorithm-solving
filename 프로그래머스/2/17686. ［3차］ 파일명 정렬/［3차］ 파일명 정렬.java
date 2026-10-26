/*
 * [문제 정보] [3차] 파일명 정렬 (Level 2)
 * [사용 알고리즘] 문자열 파싱, Comparator 정렬
 *
 * [풀이 핵심]
 * 1. 정렬 기준이 HEAD와 NUMBER에만 있으므로,
 *    각 파일명을 처음부터 순회하면서 HEAD / NUMBER / TAIL로 직접 분리한다.
 * 2. HEAD는 첫 숫자가 등장하기 전까지,
 *    NUMBER는 숫자가 연속되는 구간까지 substring으로 잘라 저장한다.
 * 3. 파일명 원본 자체를 정렬하기보다
 *    splitName[i] = {HEAD, NUMBER, TAIL} 형태로 저장해
 *    Comparator에서 필요한 값에 바로 접근할 수 있도록 한다.
 * 4. HEAD는 대소문자를 구분하지 않고 비교하고,
 *    HEAD가 같을 때만 NUMBER를 int로 변환해 오름차순 비교한다.
 * 5. HEAD와 NUMBER가 모두 같으면 Comparator가 0을 반환하도록 해
 *    기존 입력 순서를 유지한다.
 * 6. 정렬 후에는 분리해둔 HEAD + NUMBER + TAIL을 다시 합쳐
 *    원래 파일명 형태로 반환한다.
 *
 * [시간 복잡도]
 * O(N log N)
 * - N개의 파일명을 분리한 뒤 Comparator를 이용해 정렬하므로
 *   전체 시간 복잡도는 정렬에 의해 O(N log N)이다.
 */
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