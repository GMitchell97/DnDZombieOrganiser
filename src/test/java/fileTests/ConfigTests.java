package fileTests;

import mitchell.dnd.dndzombieorganiser.data.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class ConfigTests {

    private Config config;

    @BeforeEach
    public void setup() {
        config = new Config();
    }

    @Test
    public void getSavePath() {
        Assertions.assertEquals("",config.getSavePath().toString());
    }

    @Test
    public void setSavePath() {
        config.saveSavePath(Paths.get("D:\\"));
        Assertions.assertEquals("D:\\",config.getSavePath().toString());
    }

    @AfterEach
    public void tearDown() {
        config.setDefaults();
    }
}
