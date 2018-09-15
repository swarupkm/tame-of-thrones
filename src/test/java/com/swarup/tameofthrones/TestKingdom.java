package com.swarup.tameofthrones;

import com.swarup.tameofthrones.exceptions.InvalidAllyException;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.swarup.tameofthrones.rulerstratergy.RulerStrategies.atleast3AlliesRulerStrategy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestKingdom {
    @Test
    public void should_add_alies_to_kingdom() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        Kingdom kingdom2 = new Kingdom("FIRE", "snake", null);
        kingdom1.addAlly(kingdom2);

        assertThat(kingdom1.hasAlly(kingdom2)).isTrue();
        assertThat(kingdom2.hasAlly(kingdom1)).isTrue();
    }

    @Test(expected = InvalidAllyException.class)
    public void should_not_add_itself() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        kingdom1.addAlly(kingdom1);
    }

    @Test(expected = InvalidAllyException.class)
    public void should_not_add_empty_kingdom_as_ally() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        kingdom1.addAlly(Kingdom.nullKingdom());
    }

    @Test
    public void should_have_ruled_the_universe_if_kingdom_has_atleast_3_allies() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        Kingdom kingdom2 = new Kingdom("FIRE", "snake", null);
        Kingdom kingdom3 = new Kingdom("WATER", "fish", null);
        Kingdom kingdom4 = new Kingdom("WOOD", "snake", null);

        Set<Kingdom> kingdomSet = new HashSet<>();
        kingdomSet.addAll(Arrays.asList(kingdom1, kingdom2, kingdom3, kingdom4));

        kingdom1.addAlly(kingdom2);
        kingdom1.addAlly(kingdom3);
        kingdom1.addAlly(kingdom4);

        assertThat(atleast3AlliesRulerStrategy(kingdomSet)).isEqualTo(kingdom1);

    }

    @Test
    public void should_not_rule_the_universe_if_kingdom_has_less_than_3_allies() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        Kingdom kingdom2 = new Kingdom("FIRE", "snake", null);
        Kingdom kingdom3 = new Kingdom("WATER", "fish", null);

        Set<Kingdom> kingdomSet = new HashSet<>();
        kingdomSet.addAll(Arrays.asList(kingdom1, kingdom2, kingdom3));

        kingdom1.addAlly(kingdom2);
        kingdom1.addAlly(kingdom3);


        assertThat(atleast3AlliesRulerStrategy(kingdomSet)).isEqualTo(Kingdom.nullKingdom());
    }

}