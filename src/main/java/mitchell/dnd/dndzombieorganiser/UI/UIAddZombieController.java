package mitchell.dnd.dndzombieorganiser.UI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mitchell.dnd.dndzombieorganiser.core.Helper;
import mitchell.dnd.dndzombieorganiser.api.CallManager;
import mitchell.dnd.dndzombieorganiser.data.DataDTO;

import java.io.IOException;

import static mitchell.dnd.dndzombieorganiser.core.Helper.*;

public class UIAddZombieController {

    private DataDTO data;

    @FXML
    protected TextField CreatureType;

    @FXML
    protected TextField CreatureRace;

    @FXML
    protected void validateClick(ActionEvent event) throws IOException, InterruptedException {
        if (((Button)event.getSource()).getId().equals("TypeValidate")) {
            if (validateCreatureType(CreatureType.getText())) {
                CreatureType.setStyle("-fx-text-fill: green;");
            } else {
                CreatureType.setStyle("-fx-text-fill: red;");
            }
        } else if (((Button)event.getSource()).getId().equals("RaceValidate")) {
            if (validateCreatureRace(CreatureRace.getText())) {
                CreatureRace.setStyle("-fx-text-fill: green;");
            } else {
                CreatureRace.setStyle("-fx-text-fill: red;");
            }
        }
    }

    @FXML
    protected void addZombie() throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();

        CallManager callMan = getCreatureType(CreatureType.getText());
        if (callMan.getStatusCode() == 200) {
            JsonNode typeJson = mapper.readTree(callMan.getJson());
            callMan = getCreatureRace(CreatureRace.getText());
            if (callMan.getStatusCode() == 200) {
                JsonNode raceJson = mapper.readTree(callMan.getJson());
                Helper.addZombie(data, raceJson, typeJson);
            }
        }

        ((Stage)CreatureRace.getScene().getWindow()).close();
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}