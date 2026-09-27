import java.util.*;
/*
 * [문제 정보] 택배상자 (Level 2)
 * [사용 알고리즘] Stack
 *
 * [풀이 핵심]
 * 1. 메인 컨테이너 벨트의 박스를 1번부터 순서대로 Stack에 push.
 * 2. Stack의 top이 현재 실어야 하는 order[idx]와 같으면 pop하여 트럭에 적재.
 * 3. 적재가 가능할 동안 계속 pop하면서 다음 순서의 박스를 확인.
 * 4. 더 이상 원하는 순서대로 꺼낼 수 없으면 지금까지 적재한 박스 개수(idx)를 반환.
 *
 * [시간 복잡도] O(N) (= 각 박스가 Stack에 최대 한 번 push되고 한 번 pop됨)
 */
class Solution {
    public int solution(int[] order) {
        Stack<Integer> s = new Stack<>();
        int idx = 0;  // 현재 실어야 하는 order의 인덱스 & 현재까지 실린 박스의 개수
        
        for (int b = 1; b <= order.length; b++) {
            s.push(b);  // 들어오는 박스를 보조 컨테이너 벨트에 저장
            
            // 현재 필요한 박스를 꺼낼 수 있는 동안 계속 적재
            while (!s.isEmpty() && s.peek() == order[idx]) {
                s.pop();
                idx++;
                
                // 모든 박스를 순서대로 실었다면 적재한 박스 개수 반환
                if (idx == order.length)
                    return idx;
            }
        }
        
        // 더 이상 원하는 순서대로 박스를 실을 수 없는 경우
        return idx;
    }
}