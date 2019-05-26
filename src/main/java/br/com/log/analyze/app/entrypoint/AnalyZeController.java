package br.com.log.analyze.app.entrypoint;

import br.com.log.analyze.app.entrypoint.model.AnalyzeRequest;
import br.com.log.analyze.domain.entity.Analyse;
import br.com.log.analyze.domain.usecase.StartAnalyse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class AnalyZeController {


  private final StartAnalyse startAnalyse;


  @PostMapping("/analyze")
  public Mono<Analyse> analyze(AnalyzeRequest request) {
    return startAnalyse.execute(request.getName());
  }

}
