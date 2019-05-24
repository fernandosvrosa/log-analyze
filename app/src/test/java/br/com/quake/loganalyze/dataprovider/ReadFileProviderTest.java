package br.com.quake.loganalyze.dataprovider;

import static org.junit.Assert.*;
import static  org.mockito.Mockito.*;

import br.com.quake.loganalyze.dataprovider.exception.FileNotFoundException;
import java.io.File;
import org.junit.Test;

public class ReadFileProviderTest {


  @Test
  public void testLinesSuccess(){
    FilePathProperties filePathProperties = mock(FilePathProperties.class);
    String resources = getClass().getResource("/log/").getPath();
    when(filePathProperties.getPath()).thenReturn(resources);
    ReadFileProvider target = new ReadFileProvider(filePathProperties);
    var result = target.getLines("test.log");
    assertTrue(result.count() > 1);
  }


  @Test(expected = FileNotFoundException.class)
  public void testLinesFileNotFoundException(){
    FilePathProperties filePathProperties = mock(FilePathProperties.class);
    String resources = getClass().getResource("/log/").getPath();
    when(filePathProperties.getPath()).thenReturn(resources);
    ReadFileProvider target = new ReadFileProvider(filePathProperties);
    var result = target.getLines("test2.log");
  }

}