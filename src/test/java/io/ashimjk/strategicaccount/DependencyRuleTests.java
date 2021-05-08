package io.ashimjk.strategicaccount;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import io.ashimjk.strategicaccount.archunit.HexagonalArchitecture;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static io.ashimjk.strategicaccount.archunit.ArchitectureElement.BASE_PACKAGE;

class DependencyRuleTests {

    @Test
    void validateRegistrationContextArchitecture() {
        HexagonalArchitecture.boundedContext(BASE_PACKAGE + ".account")

                             .withDomainLayer("domain")

                             .withAdaptersLayer("adapter")
                             .incoming("in.web")
                             .outgoing("out.persistence")
                             .and()

                             .withApplicationLayer("application")
                             .services("service")
                             .incomingPorts("port.in")
                             .outgoingPorts("port.out")
                             .and()

                             .withConfiguration("configuration")
                             .check(new ClassFileImporter().importPackages(BASE_PACKAGE + ".."));
    }

    @Test
    void testPackageDependencies() {
        noClasses()
                .that()
                .resideInAPackage(BASE_PACKAGE + ".domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(BASE_PACKAGE + ".application..")
                .check(new ClassFileImporter().importPackages(BASE_PACKAGE + ".."));
    }

}