import java.util.*;
/*
 * [문제 정보] 방문 길이 (Level 2)
 * [사용 알고리즘] 구현, Set (시뮬레이션)
 * 
 * [풀이 핵심]
 * 1. 단순히 방문한 '좌표'가 아니라, 거쳐간 '길(선분)'을 기록해야 함.
 * 2. A->B와 B->A 두 방향 모두 Set에 저장하여 중복을 제거.
 * 3. 경계(-5 ~ 5)를 벗어나는 명령은 무시.
 * 4. 최종 답: (Set에 저장된 전체 방향 수) / 2.
 * 
 * [시간 복잡도] O(N) - dirs의 길이(<= 500)만큼 반복하므로 매우 빠름.
 */
class Solution {
    public int solution(String dirs) {
        // 중복된 길을 제거하기 위한 HashSet
        Set<String> visitedPaths = new HashSet<>();

        // 시작 좌표: (0, 0)
        int currX = 0, currY = 0;

        for (int i = 0; i < dirs.length(); i++) {
            char command = dirs.charAt(i);

            int nextX = currX;
            int nextY = currY;
            
            // 이동 좌표 계산
            if (command == 'U') nextY++;
            else if (command == 'D') nextY--;
            else if (command == 'R') nextX++;
            else if (command == 'L') nextX--;

            if (nextX < -5 || nextX > 5 || nextY < -5 || nextY > 5) {
                continue;
            }

            // 양방향 길을 문자열 형태로 생성
            String path1 = "(" + currX + "," + currY + ")" + "->" + "(" + nextX + "," + nextY + ")";
            String path2 = "(" + nextX + "," + nextY + ")" + "->" + "(" + currX + "," + currY + ")";

            visitedPaths.add(path1);
            visitedPaths.add(path2);

            // 현재 위치 갱신
            currX = nextX;
            currY = nextY;
        }

        return visitedPaths.size() / 2;
    }
}