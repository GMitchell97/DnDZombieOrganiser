package fileTests;

import mitchell.dnd.dndzombieorganiser.data.DataDTO;
import mitchell.dnd.dndzombieorganiser.data.FileHandler;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Scanner;

public class FileTests {

    private FileHandler handler = new FileHandler();


    @Test
    public void FileSaveAndLoad() {
        DataDTO dataDTO = new DataDTO();
        dataDTO.getZombies().add(new ZombieDTO());
        handler.Save("Test1.json", dataDTO);
        DataDTO testDTO = handler.loadSave("Test1.json");

        Assertions.assertEquals(0, testDTO.getZombies().size());
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (Files.exists(Paths.get("res/saves/Test1.json"))) {
            Files.delete(Paths.get("res/saves/Test1.json"));
        }
    }
}
