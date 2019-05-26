package br.com.log.analyze.app.dataprovider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties("file")
public class FilePathProperties {

  private String path;

}