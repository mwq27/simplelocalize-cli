package io.simplelocalize.cli.processor.keys;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Set;

public class IEighteenNextKeyExtractorTest {
  private final IEighteenNextKeyExtractor keyExtractor = new IEighteenNextKeyExtractor();

  @Test
  public void shouldExtractKeysFromLines() throws Exception {
    //given
    ClassLoader classLoader = this.getClass().getClassLoader();
    URL resource = classLoader.getResource("i18next/AboutPage.jsx");
    String path = resource.getPath();


    //when
    Set<String> keys = keyExtractor.extractKeysFromFile(Paths.get(path));

    //then
    Assertions.assertThat(keys)
            .hasSize(4)
            .containsExactlyInAnyOrder(
                    "Welcome to React",
                    "simpleContentJSX",
                    "userMessagesUnreadJSX",
                    "nameTitleJSX"
            );
  }
}
