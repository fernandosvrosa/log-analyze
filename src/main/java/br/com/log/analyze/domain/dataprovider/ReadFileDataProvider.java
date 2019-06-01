package br.com.log.analyze.domain.dataprovider;

import java.util.stream.Stream;


public interface ReadFileDataProvider {

  Stream<String> getLines(final String fileName);

}
