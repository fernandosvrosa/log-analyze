package br.com.log.analyze.app.entrypoint;

import br.com.log.analyze.app.dataprovider.mongo.repository.GameModelRepository;
import br.com.log.analyze.app.entrypoint.model.AnalyzeRequest;
import br.com.log.analyze.domain.entity.GameResult;
import br.com.log.analyze.domain.usecase.StartAnalyse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class AnalyZeController {


  private final StartAnalyse startAnalyse;

  private final GameModelRepository gameModelRepository;


  @PostMapping("/analyze")
  public Mono<GameResult> analyze(@RequestBody AnalyzeRequest request) {
    return startAnalyse.execute(request.getName());
  }


  @GetMapping("/analyze/{id}")
  public Mono<GameResult> getGameResult(@PathVariable String id) {
    return gameModelRepository.findById(id);
  }

}
