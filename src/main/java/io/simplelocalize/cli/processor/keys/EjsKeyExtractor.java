package io.simplelocalize.cli.processor.keys;

import io.simplelocalize.cli.util.FileContentUtil;
import io.simplelocalize.cli.util.FileReaderUtil;

import java.nio.file.Path;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EjsKeyExtractor implements KeyExtractor {

  @Override
  public Set<String> extractKeysFromFile(Path filePath) {
    String fileContent = FileReaderUtil.tryReadContent(filePath);
    fileContent = FileContentUtil.transformTextToOneLine(fileContent);
    return matchI18n(fileContent);

  }

  private Set<String> matchI18n(String fileContent) {
    fileContent = fileContent.replaceAll("\\s+", "");
    return Pattern.compile("(?<=<%=i18n\\.)(.*?)(?=%>)")
            .matcher(fileContent)
            .results()
            .map(MatchResult::group)
            .collect(Collectors.toSet());
  }
}
