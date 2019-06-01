package br.com.log.analyze.app.dataprovider;


import br.com.log.analyze.app.dataprovider.exception.FileNotFoundException;
import br.com.log.analyze.domain.dataprovider.ReadFileDataProvider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReadFileProvider implements ReadFileDataProvider {

  private final FilePathProperties fileBase;


  @Override
  public Stream<String> getLines(final String fileName) {
    Stream<String> lines = null;
    var path = Paths.get(fileBase.getPath(), fileName);
    try {
      lines = Files.lines(path, StandardCharsets.UTF_8);

    } catch (IOException e) {
      throw new FileNotFoundException("Error read file ", e);
    }
    return lines;
  }
}
