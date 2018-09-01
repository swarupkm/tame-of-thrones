import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class KingdomMessageCombinationTest {
    @Test
    public void should_send_message_to_another_kingdom_which_has_emblem_characters_and_be_successful() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        Kingdom kingdom2 = new Kingdom("FIRE", "snake", null);

        assertThat(kingdom1.sendMessageTo(kingdom2 , "snakensake")).isTrue();

    }

    @Test
    public void should_send_message_to_another_kingdom_which_does_not_emblem_characters_and_not_be_successful() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        Kingdom kingdom2 = new Kingdom("FIRE", "snake", null);

        assertThat(kingdom1.sendMessageTo(kingdom2 , "htgerbx")).isFalse();

    }

    @Test
    public void should_send_message_to_another_kingdom_which_has_less_emblem_characters_and_not_be_successful() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        Kingdom kingdom2 = new Kingdom("FIRE", "snakesnake", null);

        assertThat(kingdom1.sendMessageTo(kingdom2 , "snake")).isFalse();

    }


}
