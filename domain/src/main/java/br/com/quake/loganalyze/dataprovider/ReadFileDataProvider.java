package br.com.quake.loganalyze.dataprovider;

import java.util.stream.Stream;

public interface ReadFileDataProvider {

  Stream<String> getLines(final String fileName);

}
