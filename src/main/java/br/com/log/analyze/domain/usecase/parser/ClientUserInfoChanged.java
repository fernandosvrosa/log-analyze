package br.com.log.analyze.domain.usecase.parser;

import static br.com.log.analyze.domain.entity.GameReferences.CLIENT_USER_INFO_PATTERN;
import static br.com.log.analyze.domain.entity.GameReferences.buildLinePatternFor;

import br.com.log.analyze.domain.entity.Game;
import java.util.regex.Matcher;
import org.springframework.stereotype.Component;


public class ClientUserInfoChanged implements ParserGame {

  @Override
  public boolean isThisLine(String line) {
    final Matcher matcher = getMatcher(line);
    return matcher.matches();
  }

  private Matcher getMatcher(String line) {
    return buildLinePatternFor(CLIENT_USER_INFO_PATTERN).matcher(line);
  }

  @Override
  public void execute(String line, Game game) {
    final Matcher matcher = getMatcher(line);
    String name = matcher.group(3).trim();
    final String userName = parsePlayerName(name);
    game.addPlayers(userName);

  }

  private String parsePlayerName(final String userInfo) {
    if (userInfo.length() <= 0) {
      return "";
    }

    final int playerNameStart = userInfo.indexOf("n\\");
    if (playerNameStart <= 0) {
      return "";
    }

    final int playerNameEnd = userInfo.indexOf("\\t\\");
    if (playerNameEnd <= 0) {
      return "";
    }

    return userInfo.substring(playerNameStart + 2, playerNameEnd).trim();
  }
}
