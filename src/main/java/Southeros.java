import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Southeros {

    private static Set<Kingdom> kingdoms = new HashSet<>();

    public static String ruler() {
        Kingdom kingdom = kingdoms().stream().filter(Kingdom::isKingTheRuler).findFirst().orElse(Kingdom.emptyKingdom());
        return kingdom.king();

    }

    public static Set<Kingdom> kingdoms() {
        return kingdoms;
    }

    public static Kingdom getKingdom(String name) {
        return kingdoms().stream().filter(kingdom -> kingdom.name().equalsIgnoreCase(name)).findFirst().orElse(Kingdom.emptyKingdom());
    }

    public static void register(Kingdom... kingdoms) {
        Arrays.asList(kingdoms).forEach(k -> Southeros.kingdoms.add(k));
    }

    public static void clearKingdoms() {
        kingdoms.clear();
    }
}

