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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static mitchell.dnd.dndzombieorganiser.core.Helper.*;

public class UIAddZombieController {

    private DataDTO data;

    @FXML
    protected TextField CreatureType;
    @FXML
    protected TextField CreatureRace;
    @FXML
    protected TextField Armour;
    @FXML
    protected TextField Melee;
    @FXML
    protected TextField Ranged;

    private boolean isValid;
    @FXML
    protected void validateClick() {
        isValid = true;
        ValidateField(validateCreatureType(CreatureType.getText()), CreatureType);
        ValidateField(validateCreatureRace(CreatureRace.getText()), CreatureRace);
        ValidateField(validateArmour(Armour.getText()), Armour);
        ValidateField(validateWeapon(Melee.getText()), Melee);
        ValidateField(validateWeapon(Ranged.getText()), Ranged);
    }

    private void ValidateField(boolean isValid, TextField field) {
            if (isValid) {
                field.setStyle("-fx-text-fill: green;");
            } else {
                field.setStyle("-fx-text-fill: red;");
                this.isValid = false;
            }
    }

    @FXML
    protected void addZombie() throws IOException, InterruptedException {
        validateClick();
        if (isValid) {
            Helper.addZombie(data, Map.of(
                    "type", CreatureType.getText(),
                    "race", CreatureRace.getText()
            ));
            ((Stage) CreatureRace.getScene().getWindow()).close();
        }
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}