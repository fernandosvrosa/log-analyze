package br.com.log.analyze.domain.entity;

import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Game {

  private  String name;

  private Integer totalKills;

  private  Set<Player> players;

  private  Map<String, Integer> kills;


  public void addPlayers (String name){
    Player player = new Player(name);
    players.add(player);
  }

  public void addKillPlayer(String name){
    Integer kill = kills.get(name);
    kill = kill != null ? kill : 0;
    kills.put(name, kill + 1);
    totalKills +=1;
  }

  public void removeKillPlayer(String name){
    Integer kill = kills.get(name);
    kill = kill != null ? kill : 0;
    kills.put(name, kill - 1);
    totalKills +=1;
  }

}
