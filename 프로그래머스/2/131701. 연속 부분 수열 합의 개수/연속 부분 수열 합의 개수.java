import java.util.*;
/*
 * [문제 정보] 연속 부분 수열 합의 개수 (Level 2)
 * [사용 알고리즘] 완전 탐색, HashSet
 *
 * [풀이 핵심]
 * 1. 원형 수열을 처리하기 위해 elements를 이어 붙인 형태의 배열 생성.
 * 2. 각 시작점에서 길이 1부터 N까지 연속해서 더하며 모든 부분 수열의 합 계산.
 * 3. 계산된 합을 HashSet에 저장하여 중복 제거.
 * 4. 모든 탐색이 끝난 후 HashSet의 크기를 반환.
 *
 * [시간 복잡도] O(N^2) (= N개의 시작점에서 최대 N개 원소를 순회)
 */
class Solution {
    public int solution(int[] elements) {
        int len = elements.length;
        
        // 원형 수열 탐색을 위해 배열 확장
        int[] cir = new int[len * 2 - 1];
        for (int i = 0; i < cir.length; i++) {
            if (i < len)
                cir[i] = elements[i];
            else
                cir[i] = elements[i-len];
        }
        
        HashSet<Integer> set = new HashSet<>();
        
        // 각 시작점에서 길이 1 ~ N의 연속 부분합 계산
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for(int j = 0; j < len; j++) {
                sum += cir[i+j];
                set.add(sum);
            }
        }
        
        return set.size();
    }
}