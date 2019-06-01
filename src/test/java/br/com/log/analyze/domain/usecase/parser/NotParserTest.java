package br.com.log.analyze.domain.usecase.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class NotParserTest {


  private static final String SUCCESS = " 21:10 ClientDisconnect: ";

  private static final String ERROR = " 22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH\n";


  @Test
  public void testIsThisLineTrue() {
    NotParser target = new NotParser();
    boolean result = target.isThisLine(SUCCESS);
    assertTrue(result);
  }


  @Test
  public void testIsThisLineFalse() {
    NotParser target = new NotParser();
    boolean result = target.isThisLine(ERROR);
    assertFalse(result);
  }


}