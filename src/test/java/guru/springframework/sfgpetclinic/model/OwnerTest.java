package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerTest implements ModelTests {

    @Test
    void dependentAssertions() {

        Owner owner = new Owner(1l, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1231231233");

        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName(), "First Name Failed"),
                        () -> assertEquals("Buck", owner.getLastName(), "Last Name Failed")
                ),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Key West", owner.getCity(), "City Failed"),
                        () -> assertEquals("1231231233", owner.getTelephone(), "Telephone Failed")
                )
        );
    }

    @ParameterizedTest(name = "{index}. {arguments}")
    @DisplayName("Value Source Test - ")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @DisplayName("Enum Source Test")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @DisplayName("CSV Input Test")
    @CsvSource({
            "FL, 1, 1",
            "DE, 1, 1",
            "OH, 1, 1",
            "MI, 1, 1"
    })
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " - " + val1 + ":" + val2);
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @DisplayName("CSV File Test")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFromFileTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " - " + val1 + ":" + val2);
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @DisplayName("Method Provider Test")
    @MethodSource("getArgs")
    void fromMethodTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " - " + val1 + ":" + val2);
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @DisplayName("Custom Provider Test")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " - " + val1 + ":" + val2);
    }

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of("FL", 1, 1),
                Arguments.of("OH", 2, 2),
                Arguments.of("MI", 3, 7)
        );
    }
}