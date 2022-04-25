package mitchell.dnd.dndzombieorganiser.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FileHandler {

    private final String PATH = "res/saves/";

    public DataDTO loadSave(String saveName) {
        return loadSave(PATH, saveName);
    }

    public DataDTO loadSave(String path, String saveName) {
        ObjectMapper mapper = new ObjectMapper();
        DataDTO json = null;
        try {
            json = mapper.readValue(Paths.get(path + saveName).toFile(), DataDTO.class);
        } catch (IOException e) {
            System.out.println("Error loading save");
            e.printStackTrace();
            json = new DataDTO();
        }
        return json;
    }

    public void Save(String saveName, DataDTO dataDTO) {
        Save(PATH, saveName, dataDTO);
    }

    public void Save(String path, String saveName, DataDTO dataDTO) {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String fileName = path + saveName;

        try {
            if (!Files.exists(Paths.get(fileName))) {
                if (!Files.exists(Paths.get(path))) {
                    Files.createDirectories(Paths.get(path));
                }
                Files.createFile(Paths.get(fileName));
            }
            String json = writer.writeValueAsString(dataDTO);
            FileWriter save = new FileWriter(fileName);
            save.write(json);
            save.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
