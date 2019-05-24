package br.com.quake.loganalyze.dataprovider;


import br.com.quake.loganalyze.dataprovider.exception.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadFileProvider implements ReadFileDataProvider {

  private final FilePathProperties fileBase;


  @Override
  public Stream<String> getLines(final String fileName) {
    Stream<String> lines = null;
    Path path = Paths.get(fileBase.getPath(), fileName);
    try {
      lines = Files.lines(path, StandardCharsets.UTF_8);

    } catch (IOException e) {
      throw new FileNotFoundException("Error read file ", e);
    }
    return lines;
  }
}
