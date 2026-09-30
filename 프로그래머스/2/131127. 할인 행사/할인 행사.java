import java.util.*;
/*
 * [문제 정보] 할인 행사 (Level 2)
 * [사용 알고리즘] HashMap, 완전탐색
 *
 * [풀이 핵심]
 * 1. 회원가입 시작일을 하루씩 옮기며 연속된 10일의 할인 상품을 확인.
 * 2. 각 10일 구간의 상품별 개수를 HashMap에 저장.
 * 3. want의 각 상품이 필요한 개수 number와 정확히 일치하는지 확인.
 * 4. 모든 조건을 만족하면 해당 날짜에 회원가입할 수 있으므로 정답 증가.
 *
 * [시간 복잡도] O(N * (10 + W)) ≈ O(N)
 * (= 각 시작일마다 10개 상품 집계 + want 길이 W(최대 10) 확인)
 */
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int regiCnt = 0;
        
        for (int i = 0; i <= discount.length-10; i++) {
            Map<String, Integer> dcMap = new HashMap<>();
            
            // 현재 시작일부터 10일 동안 할인되는 상품 개수 집계
            for (int j = i; j < i+10; j++) {
                dcMap.put(discount[j], dcMap.getOrDefault(discount[j], 0) + 1);
            }
            
            boolean isRegi = true;
            
            // 원하는 모든 상품이 필요한 개수와 정확히 일치하는지 확인
            for (int k = 0; k < want.length; k++) {
                if (dcMap.getOrDefault(want[k], 0) != number[k]) {
                    isRegi = false;
                    break;
                }
            }
            
            if (isRegi) regiCnt++;
        }
        
        return regiCnt;
    }
}