package br.com.log.analyze.domain.dataprovider;

import java.util.stream.Stream;
import org.springframework.stereotype.Service;


public interface ReadFileDataProvider {

  Stream<String> getLines(final String fileName);

}
