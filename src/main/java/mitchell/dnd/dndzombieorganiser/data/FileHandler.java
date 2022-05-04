package mitchell.dnd.dndzombieorganiser.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import mitchell.dnd.dndzombieorganiser.data.dto.DataDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {

    private final String PATH = "res/saves/";


    public DataDTO loadSave(Path savePath) {
        return loadSave(savePath.toString(),"");
    }

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

    public void saveSave(Path path, DataDTO dataDTO) {
        saveSave(path.getParent().toString(), path.getFileName().toString(), dataDTO);
    }

    public void saveSave(String saveName, DataDTO dataDTO) {
        saveSave(PATH, saveName, dataDTO);
    }

    public void saveSave(String path, String saveName, DataDTO dataDTO) {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String fileName = path + "\\" + saveName;

        try {
            if (!Files.exists(Paths.get(fileName))) {
                if (!Files.exists(Paths.get(path))) {
                    Files.createDirectories(Paths.get(path));
                }
                Files.createFile(Paths.get(fileName));
            }
            dataDTO.saveRollHistory();
            String json = writer.writeValueAsString(dataDTO);
            FileWriter save = new FileWriter(fileName);
            save.write(json);
            save.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
