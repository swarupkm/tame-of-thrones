import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Kingdom {
    private static final int MIN_ALLIES_FOR_BEING_RULER = 3;
    private final String name;
    private final String emblem;
    private String king;
    private List<Kingdom> allies = new ArrayList<>();

    public Kingdom(String name, String emblem, String king) {
        this.name = name;
        this.emblem = emblem;
        this.king = king;
    }

    public void addAlly(Kingdom otherKingdom) {
        if (otherKingdom.name() == null || otherKingdom.name() == this.name()) {
            throw new InvalidAllyException();
        }

        if (!hasAlly(otherKingdom)) {
            allies.add(otherKingdom);
            otherKingdom.addAlly(this);
        }
    }

    boolean hasAlly(Kingdom otherKingdom) {
        return allies.contains(otherKingdom);
    }


    public boolean isKingTheRuler() {
        if (allies.size() >= MIN_ALLIES_FOR_BEING_RULER)
            return true;
        return false;
    }

    public String king() {
        return king;
    }

    public String name() {
        return name;
    }

    public static Kingdom emptyKingdom() {
        return new Kingdom(null,null,"None");
    }

    public boolean sendMessageTo(Kingdom otherKingdom, String message) {
        return otherKingdom.respondTo(message);
    }

    private boolean respondTo(String message) {
        HashMap<String, Integer> messageMap = Utils.characterIntegerHashMap(message);
        HashMap<String, Integer> emblemMap = Utils.characterIntegerHashMap(emblem);
        return logic(emblemMap, messageMap); //TODO : method to be extracted as a strategy pattern
    }

    private boolean logic(HashMap<String, Integer> emblemMap, HashMap<String, Integer> messageMap) {
        for (String emblemChar : emblemMap.keySet()) {
            if (!messageMap.containsKey(emblemChar) || messageMap.get(emblemChar) < emblemMap.get(emblemChar)) {
                return false;
            }
        }
        return true;
    }
}

