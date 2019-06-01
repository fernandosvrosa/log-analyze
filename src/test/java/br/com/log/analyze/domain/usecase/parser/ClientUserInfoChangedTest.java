package br.com.log.analyze.domain.usecase.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import br.com.log.analyze.domain.entity.Game;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.Test;

public class ClientUserInfoChangedTest {

  private static final String SUCCESS = " 20:38 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\uriel/zael\\hmodel\\uriel/zael\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0\n";

  private static final String ERROR = " 22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH\n";

  @Test
  public void testIsThisLineTrue() {
    ClientUserInfoChanged target = new ClientUserInfoChanged();
    boolean result = target.isThisLine(SUCCESS);
    assertTrue(result);
  }


  @Test
  public void testIsThisLineFalse() {
    ClientUserInfoChanged target = new ClientUserInfoChanged();
    boolean result = target.isThisLine(ERROR);
    assertFalse(result);
  }

  @Test
  public void testExecute() {
    Game result = Game.builder().name("Game 1").totalKills(0).players(new HashSet<>())
        .kills(new HashMap<>()).build();
    ClientUserInfoChanged target = new ClientUserInfoChanged();
    target.execute(SUCCESS, result);
    String playerAssert = "Isgalamido";
    assertEquals(1, result.getPlayers().size());
    assertTrue(result.getPlayers().stream().filter(player -> playerAssert.equals(player.getName()))
        .findFirst().isPresent());
    assertTrue(result.getKills().get(playerAssert) != null);

  }

}