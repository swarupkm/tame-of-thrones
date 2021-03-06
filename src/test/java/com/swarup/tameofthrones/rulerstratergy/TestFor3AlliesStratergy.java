package com.swarup.tameofthrones.rulerstratergy;

import com.swarup.tameofthrones.Kingdom;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static com.swarup.tameofthrones.rulerstratergy.RulerStrategies.atleast3AlliesRulerStrategy;
import static org.assertj.core.api.Assertions.assertThat;

public class TestFor3AlliesStratergy {

    @Test
    public void should_decide_ruling_kingdom_when_a_kingdom_has_at_least_3_allies() {
        Kingdom kingdom1 = new Kingdom("K1", "E1");
        Kingdom kingdom2 = new Kingdom("K2", "E2");
        Kingdom kingdom3 = new Kingdom("K3", "E3");
        Kingdom kingdom4 = new Kingdom("K4", "E4");

        kingdom1.addAlly(kingdom2);
        kingdom1.addAlly(kingdom3);
        kingdom1.addAlly(kingdom4);

        Kingdom ruler = atleast3AlliesRulerStrategy(kingdomSet(kingdom1, kingdom2, kingdom3, kingdom4));

        assertThat(ruler).isEqualTo(kingdom1);


    }

    @Test
    public void should_have_no_ruling_kingdom_when_neither_kingdom_has_at_least_3_allies() {
        Kingdom kingdom1 = new Kingdom("K1", "E1");
        Kingdom kingdom2 = new Kingdom("K2", "E2");
        Kingdom kingdom3 = new Kingdom("K3", "E3");
        Kingdom kingdom4 = new Kingdom("K4", "E4");

        kingdom1.addAlly(kingdom2);

        kingdom2.addAlly(kingdom3);

        kingdom4.addAlly(kingdom3);


        Kingdom ruler = atleast3AlliesRulerStrategy(kingdomSet(kingdom1, kingdom2, kingdom3, kingdom4));

        assertThat(ruler).isEqualTo(Kingdom.nullKingdom());

    }

    private Set<Kingdom> kingdomSet(Kingdom... receivers) {
        Set<Kingdom> kingdoms = new HashSet<>();
        Stream.of(receivers).forEach(kingdom -> kingdoms.add(kingdom));
        return kingdoms;
    }
}