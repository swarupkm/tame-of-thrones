import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class IntegrationTest {

    @Before
    public void setup() {
        Southeros.register(new Kingdom("LAND", "Panda", null),
                new Kingdom("WATER", "Octopus", null),
                new Kingdom("ICE", "Mammoth", null),
                new Kingdom("AIR", "Owl", null),
                new Kingdom("FIRE", "Dragon", null),
                new Kingdom("SPACE", "Gorilla", "Sham"));

    }

    @Test
    public void should_pass_for_sample_i_o() {
        Kingdom space = Southeros.getKingdom("SPACE");
        Kingdom land = Southeros.getKingdom("LAND");
        Kingdom air = Southeros.getKingdom("AIR");
        Kingdom ice = Southeros.getKingdom("ICE");

        space.sendMessageTo(air , "oaaawaala");
        space.sendMessageTo(land , "a1d22n333a4444p");
        space.sendMessageTo(ice , "zmzmzmzaztzozh");

        assertThat(Southeros.ruler()).isEqualTo(space.king());
        assertThat(space.allies()).containsExactlyInAnyOrder(land,air,ice);

    }

    @Test
    public void should_pass_for_sample_i_o_2(){
        Kingdom space = Southeros.getKingdom("SPACE");
        Kingdom land = Southeros.getKingdom("LAND");
        Kingdom air = Southeros.getKingdom("AIR");
        Kingdom ice = Southeros.getKingdom("ICE");
        Kingdom water = Southeros.getKingdom("WATER");
        Kingdom fire = Southeros.getKingdom("FIRE");

        space.sendMessageTo(air,"Let’s swing the sword together");
        space.sendMessageTo(land,"Die or play the tame of thrones");
        space.sendMessageTo(ice,"Ahoy! Fight for me with men and money");
        space.sendMessageTo(water,"Summer is coming");
        space.sendMessageTo(fire,"Drag on Martin!");

        assertThat(Southeros.ruler()).isEqualTo(space.king());
        assertThat(space.allies()).containsExactlyInAnyOrder(land,air,ice,fire);

    }

    @After
    public void tearDown() throws Exception {
        Southeros.clearKingdoms();
    }

}
