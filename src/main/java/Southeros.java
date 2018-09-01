import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Southeros {

    public static String ruler() {
        Kingdom kingdom = kingdoms().stream().filter(Kingdom::isKingTheRuler).findFirst().orElse(Kingdom.emptyKingdom());
        return kingdom.king();

    }

    public static List<Kingdom> kingdoms() {
        return KingdomSet.getKingdoms();
    }

    public static Kingdom getKingdom(String name) {
        return kingdoms().stream().filter(kingdom -> kingdom.name().equalsIgnoreCase(name)).findFirst().orElse(Kingdom.emptyKingdom());
    }
}

enum KingdomSet {
    LAND(new Kingdom("LAND", "Panda", null)),
    WATER(new Kingdom("WATER", "Octopus", null)),
    ICE(new Kingdom("ICE", "Mammoth", null)),
    AIR(new Kingdom("AIR", "Owl", null)),
    FIRE(new Kingdom("FIRE", "Dragon", null)),
    SPACE(new Kingdom("SPACE", "Gorilla", "Sham"));

    private final Kingdom kingdom;

    KingdomSet(Kingdom kingdom) {

        this.kingdom = kingdom;
    }

    static List<Kingdom> getKingdoms() {
        return Arrays.asList(KingdomSet.values()).stream().map(KingdomSet::getKingdom).collect(Collectors.toList());
    }

    private Kingdom getKingdom() {
        return kingdom;
    }
}

