package io.simplelocalize.cli.processor;

import io.simplelocalize.cli.exception.NoProcessorMatchException;
import io.simplelocalize.cli.processor.processor.AndroidProcessor;
import io.simplelocalize.cli.processor.processor.EjsProcessor;
import io.simplelocalize.cli.processor.processor.IEighteenNextProcessor;
import io.simplelocalize.cli.processor.processor.YahooReactIntlProcessor;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectProcessorFactory {
  private ProjectProcessorFactory() {
  }

  public static ProjectProcessor createForType(String projectType) {
    Objects.requireNonNull(projectType, "Could not create ProjectProcessor for null project type");

    Set<ProjectProcessor> processors = Set.of(
            new YahooReactIntlProcessor(),
            new AndroidProcessor(),
            new EjsProcessor(),
            new IEighteenNextProcessor()
    );

    List<String> supportedProjectTypesList = processors.stream().map(ProjectProcessor::getProjectTypeSupport).collect(Collectors.toList());
    String supportedProjectTypes = String.join(",", supportedProjectTypesList);

    for (ProjectProcessor processor : processors) {
      String projectTypeSupport = processor.getProjectTypeSupport();
      boolean isSupportsProjectType = projectTypeSupport.equalsIgnoreCase(projectType);
      if (isSupportsProjectType) {
        return processor;
      }
    }

    throw new NoProcessorMatchException("Could not find matching project processor for type: " + projectType + " please use on of these: " + supportedProjectTypes);
  }


}
