import java.util.*;
/*
 * [문제 정보] [3차] 압축 (Level 2)
 * [사용 알고리즘] HashMap, 문자열 처리
 *
 * [풀이 핵심]
 * 1. A ~ Z를 1 ~ 26번으로 사전에 미리 저장한다.
 * 2. 현재 위치 i에서 시작해 문자열을 한 글자씩 늘려가며 사전에 존재하는 가장 긴 문자열 w를 찾는다.
 * 3. w의 사전 번호를 정답에 추가한다.
 * 4. w 다음 문자가 존재한다면 w + c를 새로운 사전 번호로 등록한다.
 * 5. 처리한 문자열 w의 다음 위치로 이동하며 위 과정을 반복한다.
 *
 * [시간 복잡도]
 * O(N^2) - substring 생성 및 사전 탐색을 반복하므로 최악의 경우 O(N^2)
 */
class Solution {
    public List<Integer> solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();

        // A ~ Z를 1~26번으로 초기화
        for (int i = 0; i < 26; i++) {
            String s = String.valueOf((char) ('A' + i));
            dict.put(s, i + 1);
        }

        List<Integer> ans = new ArrayList<>();

        int nextDict = 27;
        int i = 0;

        while (i < msg.length()) {
            int j = i + 1;

            // 사전에 존재하는 가장 긴 문자열 찾기
            while (j <= msg.length() && dict.containsKey(msg.substring(i, j))) {
                j++;
            }

            // j는 사전에 없는 문자열의 끝 위치이므로 실제로 존재하는 가장 긴 문자열은 j-1까지
            String w = msg.substring(i, j - 1);
            
            // w의 사전 색인 출력
            ans.add(dict.get(w));

            // 아직 문자열이 남아 있다면 w+c를 사전에 추가
            if (j <= msg.length()) {
                String wc = msg.substring(i, j);
                dict.put(wc, nextDict++);
            }

            // 처리한 문자열 다음 위치로 이동
            i = j-1;
        }

        return ans;
    }
}