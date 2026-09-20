import java.util.*;
/*
 * [문제 정보] [1차] 캐시 (Level 2)
 * [사용 알고리즘] LRU, ArrayList
 *
 * [풀이 핵심]
 * 1. 캐시의 앞쪽을 가장 오래된 도시, 뒤쪽을 가장 최근 도시로 관리.
 * 2. cache hit이면 해당 도시를 삭제한 뒤 맨 뒤에 다시 추가.
 * 3. cache miss이고 캐시가 가득 찼다면 가장 오래된 도시를 제거 후 새 도시 추가.
 * 4. 도시 이름은 대소문자를 구분하지 않으므로 소문자로 변환하여 처리.
 *
 * [시간 복잡도] O(N * cacheSize) (= contains/remove 탐색 O(cacheSize) × 도시 수 N)
 */
class Solution {
    public int solution(int cacheSize, String[] cities) {
        // cacheSize가 0이면 캐시를 사용할 수 없으므로 모든 요청이 cache miss
        if (cacheSize == 0) return 5 * cities.length;
        
        List<String> li = new ArrayList<>();
        int time = 0;
        
        for (String city : cities) {
            city = city.toLowerCase();  // 도시 이름은 대소문자를 구분하지 않으므로, 소문자로 통일

            if (li.contains(city)) {  // cache hit
                li.remove(city);
                li.add(city);
                time += 1;
            } else {  // cache miss
                if (li.size() >= cacheSize) li.remove(0);

                li.add(city);
                time += 5;
            }
        }
        
        return time;
    }
}