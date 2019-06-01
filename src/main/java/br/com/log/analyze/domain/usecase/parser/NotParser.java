package br.com.log.analyze.domain.usecase.parser;

import static br.com.log.analyze.domain.entity.GameReferences.CLIENT_USER_INFO_PATTERN;
import static br.com.log.analyze.domain.entity.GameReferences.KILL_INFO_PATTERN;
import static br.com.log.analyze.domain.entity.GameReferences.buildLinePatternFor;

import br.com.log.analyze.domain.entity.Game;
import java.util.regex.Matcher;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class NotParser  implements ParserGame{

  @Override
  public boolean isThisLine(String line) {
    final Matcher clientUser = buildLinePatternFor(CLIENT_USER_INFO_PATTERN).matcher(line);
    final Matcher killPlayer = buildLinePatternFor(KILL_INFO_PATTERN).matcher(line);
    return !clientUser.find() && !killPlayer.find();
  }



  @Override
  public void execute(String line, Game game) {
    log.info("Ignore line:  " + line);
  }
}
