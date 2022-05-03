package UnitTests.DataTests;

import mitchell.dnd.dndzombieorganiser.data.dto.DataDTO;
import mitchell.dnd.dndzombieorganiser.data.FileHandler;
import mitchell.dnd.dndzombieorganiser.data.dto.ZombieDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileTests {

    private FileHandler handler = new FileHandler();


    @Test
    public void FileSaveAndLoadSingleZombie() {
        DataDTO dataDTO = new DataDTO();
        dataDTO.getZombies().add(new ZombieDTO());

        dataDTO.getZombies().get(0).setName("Gerard");
        dataDTO.getZombies().get(0).setId("1");
        dataDTO.getZombies().get(0).setHp("30");
        dataDTO.getZombies().get(0).setAc("12");

        handler.saveSave("Test1.json", dataDTO);
        DataDTO testDTO = handler.loadSave("Test1.json");

        Assertions.assertEquals(1, testDTO.getZombies().size());
        Assertions.assertEquals("Gerard", testDTO.getZombies().get(0).getName());
        Assertions.assertEquals("1", testDTO.getZombies().get(0).getId());
        Assertions.assertEquals("30", testDTO.getZombies().get(0).getHp());
        Assertions.assertEquals("12", testDTO.getZombies().get(0).getAc());
    }

    @Test
    public void FileSaveAndLoadMultipleZombie() {
        DataDTO dataDTO = new DataDTO();
        dataDTO.getZombies().addAll(List.of(new ZombieDTO(), new ZombieDTO()));

        dataDTO.getZombies().get(0).setName("Gerard");
        dataDTO.getZombies().get(0).setId("1");
        dataDTO.getZombies().get(0).setHp("30");
        dataDTO.getZombies().get(0).setAc("12");
        dataDTO.getZombies().get(1).setName("Mike");
        dataDTO.getZombies().get(1).setId("2");
        dataDTO.getZombies().get(1).setHp("20");
        dataDTO.getZombies().get(1).setAc("16");

        handler.saveSave("Test1.json", dataDTO);
        DataDTO testDTO = handler.loadSave("Test1.json");

        Assertions.assertEquals(2, testDTO.getZombies().size());
        Assertions.assertEquals("Gerard", testDTO.getZombies().get(0).getName());
        Assertions.assertEquals("1", testDTO.getZombies().get(0).getId());
        Assertions.assertEquals("30", testDTO.getZombies().get(0).getHp());
        Assertions.assertEquals("12", testDTO.getZombies().get(0).getAc());

        Assertions.assertEquals("Mike", testDTO.getZombies().get(1).getName());
        Assertions.assertEquals("2", testDTO.getZombies().get(1).getId());
        Assertions.assertEquals("20", testDTO.getZombies().get(1).getHp());
        Assertions.assertEquals("16", testDTO.getZombies().get(1).getAc());
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (Files.exists(Paths.get("res/saves/Test1.json"))) {
            Files.delete(Paths.get("res/saves/Test1.json"));
        }
    }
}
