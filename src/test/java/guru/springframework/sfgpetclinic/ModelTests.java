package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("model")
public interface ModelTests {

    @BeforeEach
    default void beforeEach_ConsoleOutput(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
    }
}
