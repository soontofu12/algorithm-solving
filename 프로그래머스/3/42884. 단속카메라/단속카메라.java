import java.util.*;
/*
 * [문제 정보] 단속카메라 (Level 3)
 * [사용 알고리즘] 탐욕법(Greedy), 정렬
 *
 * [풀이 핵심]
 * 1. 차량을 진출 지점 기준으로 오름차순 정렬.
 * 2. 현재 카메라가 차량의 진입 지점보다 앞에 있으면, 해당 차량의 진출 지점에 새 카메라 설치.
 * 3. 진출 지점에 설치하면 이후 차량까지 함께 단속할 가능성을 최대화할 수 있음.
 *
 * [시간 복잡도] O(N log N) (= 정렬 O(N log N) + 순회 O(N)).
 */
class Solution {
    public int solution(int[][] routes) {
        // 진출 지점을 기준으로 오름차순 정렬
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        int currCam = Integer.MIN_VALUE;
        int cnt = 0;
        for (int i = 0; i < routes.length; i++) {
            // 현재 카메라로 단속할 수 없다면 진출 지점에 새 카메라 설치
            if (currCam < routes[i][0]) {
                currCam = routes[i][1];
                cnt++;
            }
        }
        
        return cnt;
    }
}