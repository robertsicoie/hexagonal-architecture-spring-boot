package consulting.convex.hexagonal;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;
import static com.tngtech.archunit.library.DependencyRules.NO_CLASSES_SHOULD_DEPEND_UPPER_PACKAGES;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;


import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(
    packages = {"consulting.convex.hexagonal"},
    importOptions = DoNotIncludeTests.class
)
public class ArchitectureTest {

  @ArchTest
  static final ArchRule hexagonal_architecture_is_respected = onionArchitecture()
      .domainModels("..domain.model..")
      .domainServices("..domain.service..", "..domain.port..")
      .applicationServices("..application..")
      .adapter("rest", "..adapter.rest..")
      .adapter("db", "..adapter.db..");

  @ArchTest
  static final ArchRule no_access_to_upper_packages = NO_CLASSES_SHOULD_DEPEND_UPPER_PACKAGES;

  @ArchTest
  static final ArchRule no_classes_should_throw_generic_exceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

  @ArchTest
  static final ArchRule domain_should_not_depend_on_adapters = classes().that()
      .resideInAnyPackage("..domain..")
      .should().onlyDependOnClassesThat().resideOutsideOfPackage("..adapter..");

  @ArchTest
  static final ArchRule controllers_should_be_suffixed =
      classes()
          .that().areAnnotatedWith(RestController.class)
          .should().haveSimpleNameEndingWith("Controller");

  @ArchTest
  static final ArchRule controllers_should_not_depend_on_each_others = classes()
      .that().areAnnotatedWith(RestController.class)
      .should().onlyDependOnClassesThat().areNotAnnotatedWith(RestController.class);
}
