import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> gemTypes = new HashSet<>(Arrays.asList(gems));
        int totalTypes = gemTypes.size();
        
        Map<String, Integer> gemMap = new HashMap<>();
        int left = 0, right = 0;
        int start = 0, end = gems.length - 1;
        int minLen = gems.length + 1;
        
        while (right < gems.length) {
            gemMap.put(gems[right], gemMap.getOrDefault(gems[right], 0) + 1);
            right++;
            
            while (gemMap.size() == totalTypes) {
                int currLen = right - left;
                if (currLen < minLen) {
                    minLen = currLen;
                    start = left;
                    end = right - 1;
                }
                
                gemMap.put(gems[left], gemMap.get(gems[left]) - 1);
                if (gemMap.get(gems[left]) == 0) {
                    gemMap.remove(gems[left]);
                }
                left++;
            }
        }
        
        return new int[] {start+1, end+1};
    }
}