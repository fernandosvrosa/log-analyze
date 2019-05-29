package br.com.log.analyze.domain.entity;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class Game {

  private final String name;

  private final List<Player> players;

  private final Map<String, Integer> kills;

}
