import java.util.*;
/*
 * [문제 정보] [3차] n진수 게임 (Level 2)
 * [사용 알고리즘] 진법 변환, 문자열 처리
 *
 * [풀이 핵심]
 * 1. 0부터 시작하는 숫자를 차례대로 n진수 문자열로 변환하여 하나의 문자열에 이어 붙인다.
 * 2. 튜브가 t개의 숫자를 말하기 위해 필요한 마지막 위치는
 *    m * (t - 1) + p 이므로, 해당 길이 이상이 될 때까지 문자열을 생성한다.
 * 3. 진법 변환 시 나머지가 0~9이면 숫자 문자로,
 *    10~15이면 A~F로 변환한다.
 * 4. 나머지를 구하는 순서는 실제 n진수 표현과 반대이므로
 *    변환한 문자열을 뒤집어서 사용한다.
 * 5. 튜브의 첫 번째 위치는 p - 1이며,
 *    이후 m칸씩 이동하면서 총 t개의 문자를 선택한다.
 *
 * [시간 복잡도]
 * O(m * t)
 * - 필요한 문자열 길이가 약 m * t이고,
 *   각 숫자의 진법 변환 길이는 제한된 범위에서 처리된다.
 */
class Solution {
    public String solution(int n, int t, int m, int p) {
        // 튜브가 마지막으로 말할 위치까지 문자열을 만들어야 함
        int needLen = m * (t - 1) + p;

        StringBuilder sb = new StringBuilder();

        int num = 0;

        while (sb.length() < needLen) {
            sb.append(convert(num, n));
            num++;
        }

        StringBuilder ans = new StringBuilder();

        // 튜브의 첫 번째 순서는 p이므로 배열 인덱스는 p - 1
        int idx = p - 1;

        while (ans.length() < t) {
            ans.append(sb.charAt(idx));
            idx += m;
        }

        return ans.toString();
    }

    // 10진수 num을 n진수 문자열로 변환
    private String convert(int num, int n) {
        if (num == 0) return "0";

        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int remain = num % n;

            if (remain < 10) {
                sb.append((char) ('0' + remain));
            } else {
                sb.append((char) ('A' + remain - 10));
            }

            num /= n;
        }

        return sb.reverse().toString();
    }
}