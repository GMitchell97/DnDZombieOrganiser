package mitchell.dnd.dndzombieorganiser.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FileHandler {

    private final String PATH = "";

    public DataDTO loadSave(String saveName) {
        ObjectMapper mapper = new ObjectMapper();
        DataDTO json = null;
        try {
            json = mapper.readValue(FileHandler.class.getResource(PATH + saveName + ".json"), DataDTO.class);
        } catch (IOException e) {
            System.out.println("Error loading save");
            e.printStackTrace();
            json = new DataDTO();
        }
        return json;
    }

}
