import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSoutheros {

    @Test
    public void should_have_6_kingdoms() {
        assertThat(Southeros.kingdoms()).hasSize(6);
    }

    @Test
    public void should_have_no_rulers_when_no_kingdom_has_at_least_3_allies() {
        assertThat(Southeros.ruler()).isEqualTo("None");
    }

    @Test
    public void should_have_rules_when_one_kingdom_has_atleast_3_allies() {
        Kingdom space = Southeros.getKingdom("SPACE");
        Kingdom water = Southeros.getKingdom("WATER");
        Kingdom ice = Southeros.getKingdom("ICE");
        Kingdom air = Southeros.getKingdom("AIR");

        space.addAlly(water);
        space.addAlly(ice);
        space.addAlly(air);

        assertThat(Southeros.ruler()).isEqualTo(space.king());

    }
}
