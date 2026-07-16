import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < phone_book.length; i++) {
            map.put(phone_book[i], i);
        }
        
        outer: for (int i = 0; i < phone_book.length; i++) {
            String currNum = phone_book[i];
            for (int j = 0; j < currNum.length(); j++) {
                String prefix = currNum.substring(0, j);
                if (map.containsKey(prefix)) {
                    answer = false;
                    break outer;
                }
            }
        }
        
        return answer;
    }
}