package br.com.log.analyze.app.dataprovider;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import br.com.log.analyze.app.dataprovider.exception.FileNotFoundException;
import java.util.stream.Stream;
import org.junit.Test;

public class ReadFileProviderTest {


  @Test
  public void testLinesSuccess() {
    FilePathProperties filePathProperties = mock(FilePathProperties.class);
    String resources = getClass().getResource("/log/").getPath();
    when(filePathProperties.getPath()).thenReturn(resources);
    ReadFileProvider target = new ReadFileProvider(filePathProperties);
    Stream<String> result = target.getLines("test.log");
    assertTrue(result.count() > 1);
  }


  @Test(expected = FileNotFoundException.class)
  public void testLinesFileNotFoundException() {
    FilePathProperties filePathProperties = mock(FilePathProperties.class);
    String resources = getClass().getResource("/log/").getPath();
    when(filePathProperties.getPath()).thenReturn(resources);
    ReadFileProvider target = new ReadFileProvider(filePathProperties);
    target.getLines("test2.log");
  }

}