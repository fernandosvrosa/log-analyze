package br.com.log.analyze.domain.entity;


import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document("gameResult")
public class GameResult {

  @Id
  private final String id;

  private  final List<Game> games;

}
