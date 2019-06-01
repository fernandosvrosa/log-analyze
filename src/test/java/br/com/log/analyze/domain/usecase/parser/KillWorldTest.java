package br.com.log.analyze.domain.usecase.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import br.com.log.analyze.domain.entity.Game;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.Test;

public class KillWorldTest {

  private static final String SUCCESS  = " 20:54 Kill: 1022 2 22: <world> killed Isgalamido by MOD_TRIGGER_HURT\n";

  private static final String ERROR = " 22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH\n";


  @Test
  public void testIsThisLineTrue() {
    KillWorld target = new KillWorld();
    boolean result = target.isThisLine(SUCCESS);
    assertTrue(result);
  }


  @Test
  public void testIsThisLineFalse() {
    KillWorld target = new KillWorld();
    boolean result = target.isThisLine(ERROR);
    assertFalse(result);
  }

  @Test
  public void testExecute() {
    Game result = Game.builder().name("Game 1").totalKills(0).players(new HashSet<>())
        .kills(new HashMap<>()).build();
    KillWorld target = new KillWorld();
    target.execute(SUCCESS, result);
    String playerAssert = "Isgalamido";
    assertEquals(1, result.getKills().size());
    Integer kill = result.getKills().get(playerAssert);
    assertEquals(-1, (int) kill);

  }

}