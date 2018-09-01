import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class KingdomTest {
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
        kingdom1.addAlly(Kingdom.emptyKingdom());
    }

    @Test
    public void should_have_ruled_the_universe_if_kingdom_has_atleast_3_allies() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        Kingdom kingdom2 = new Kingdom("FIRE", "snake", null);
        Kingdom kingdom3 = new Kingdom("WATER", "fish", null);
        Kingdom kingdom4 = new Kingdom("WOOD", "snake", null);
        kingdom1.addAlly(kingdom2);
        kingdom1.addAlly(kingdom3);
        kingdom1.addAlly(kingdom4);

        assertThat(kingdom1.isKingTheRuler()).isTrue();
    }

    @Test
    public void should_not_rule_the_universe_if_kingdom_has_less_than_3_allies() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        Kingdom kingdom2 = new Kingdom("FIRE", "snake", null);
        Kingdom kingdom3 = new Kingdom("WATER", "fish", null);
        kingdom1.addAlly(kingdom2);
        kingdom1.addAlly(kingdom3);

        assertThat(kingdom1.isKingTheRuler()).isFalse();
    }

}