package mitchell.dnd.dndzombieorganiser.UI;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mitchell.dnd.dndzombieorganiser.Constants;
import mitchell.dnd.dndzombieorganiser.core.Helper;
import mitchell.dnd.dndzombieorganiser.data.dto.DataDTO;

import java.io.IOException;
import java.util.Map;

import static mitchell.dnd.dndzombieorganiser.core.Helper.*;

public class UIAddZombieController {

    private DataDTO data;

    @FXML
    protected TextField CreatureType;
    @FXML
    protected ComboBox<String> CreatureRace;
    @FXML
    protected ComboBox<String> Armour;
    @FXML
    protected TextField Melee;
    @FXML
    protected TextField Ranged;

    private boolean isValid;

    @FXML
    public void initialize() {
        CreatureRace.setItems(FXCollections.observableArrayList(Constants.RACES));
        CreatureRace.setValue("human");
        Armour.setItems(FXCollections.observableArrayList(Constants.ARMOUR));
        Armour.setValue("none");
    }

    @FXML
    protected void validateClick() {
        isValid = true;
        ValidateField(validateCreatureType(CreatureType.getText()), CreatureType);
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
             Map<String, String> args = new java.util.HashMap<>(Map.of(
                     "type", CreatureType.getText(),
                     "race", CreatureRace.getValue(),
                     "armour", Armour.getValue()
             ));
            if (!Melee.getText().equals("")) {
                args.put("melee", Melee.getText());
            }
            if (!Ranged.getText().equals("")) {
                args.put("ranged", Ranged.getText());
            }
            Helper.addZombie(data, args);
                    ((Stage) CreatureRace.getScene().getWindow()).close();
        }
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}