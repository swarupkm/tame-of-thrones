import java.util.HashMap;

public class Utils {
    public static HashMap<String, Integer> characterIntegerHashMap(String message) {
        char[] chars = message.toLowerCase().toCharArray();
        HashMap<String, Integer> messageMap = new HashMap<>();
        for (char c : chars) {
            String key = String.valueOf(c);
            messageMap.putIfAbsent(key, 0);
            Integer incrementedCount = messageMap.get(key) + 1;
            messageMap.put(key, incrementedCount);
        }
        return messageMap;
    }
}
