package mitchell.dnd.dndzombieorganiser.data.properties;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {

    private Properties config = new Properties();

    public Config() {
        try (InputStream file = Config.class.getResourceAsStream("config.properties")) {
            config.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Path getSavePath() {
        String path = config.getProperty("savePath");
        return Paths.get(path);
    }

    public void saveSavePath(String savePath) {
        config.setProperty("savePath", savePath);
        try {
            config.store(new FileOutputStream(Config.class.getResource("config.properties").getPath()),"Set save path: " + savePath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSavePath(Path savePath) {
        saveSavePath(savePath.toString());
    }

    public void setDefaults() {
        saveSavePath("");
    }
}
