import java.util.*;
/*
 * [문제 정보] 단어 변환 (Level 3)
 * [사용 알고리즘] BFS
 * 
 * [풀이 핵심]
 * 1. int[] {words 배열 인덱스, 변환 횟수}를 Queue에 넣어 관리.
 * 2. begin 단어는 words에 없으므로 시작 시점에는 인덱스 -1로 큐에 삽입.
 * 3. words 배열을 순회하며 '아직 방문하지 않았고', '현재 단어와 1글자만 다른 단어'로 이동.
 * 4. target 단어를 가장 먼저 만나면 그때의 변환 횟수를 반환.
 * 
 * [시간 복잡도] O(N * L) - N: words의 개수 (최대 50), L: 단어의 길이 (최대 10)
 */
class Solution {
    
    // 두 단어가 정확히 한 단어만 일치하는지 확인하는 메서드
    private boolean canConvert(String word1, String word2) {
        int diffCnt = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i))
                diffCnt++;
        }
        return diffCnt == 1;
    }
    
    static boolean[] visited;  // 방문 처리
    
    public int solution(String begin, String target, String[] words) {
        boolean containsTarget = false;
        for (String w : words) {
            if (w.equals(target)) {
                containsTarget = true;
                break;
            }
        }
        
        if (!containsTarget) return 0;  // target 단어가 없다면 0 반환
        
        visited = new boolean[words.length];
        return bfs(-1, 0, begin, target, words);
    }
    
    public int bfs(int currIndex, int cnt, String begin, String target, String[] words) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {currIndex, cnt});
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int currIdx = curr[0];
            int currCnt = curr[1];
            
            String currWord = (currIdx == -1) ? begin : words[currIdx];
            
            if (currWord.equals(target))
                return currCnt;
            
            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && canConvert(currWord, words[i])) {
                    visited[i] = true;
                    q.offer(new int[] {i, currCnt + 1});
                }
            }
        }
        
        return 0;
    }
}