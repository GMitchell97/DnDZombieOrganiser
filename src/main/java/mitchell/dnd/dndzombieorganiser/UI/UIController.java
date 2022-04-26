package mitchell.dnd.dndzombieorganiser.UI;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import mitchell.dnd.dndzombieorganiser.Helper;
import mitchell.dnd.dndzombieorganiser.data.Config;
import mitchell.dnd.dndzombieorganiser.data.DataDTO;
import mitchell.dnd.dndzombieorganiser.data.FileHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UIController {

    Config config;
    DataDTO data;
    FileHandler fileHandler = new FileHandler();

    @FXML
    private TableView<ZombieWrapper> ZombieTable;

    @FXML
    public void initialize() {

        config = new Config();
        loadZombieTable();
    }

    @FXML
    protected void loadZombieTable() {

        if (!config.getSavePath().toString().equals("")) {
            data = fileHandler.loadSave(config.getSavePath());
        }

        ZombieTable.setEditable(true);
        ZombieTableSchema schema = new ZombieTableSchema();

        List<TableColumn<ZombieWrapper, String>> columns = new ArrayList<>();
        for (ZombieTableSchema.Schema sc: schema.ZombieTableSchema) {
            int index = columns.size();
            columns.add(new TableColumn<>(sc.header));
            columns.get(index).setCellValueFactory(
                    new PropertyValueFactory<ZombieWrapper, String>(sc.variableName));
            columns.get(index).setCellFactory(TextFieldTableCell.forTableColumn());
            if (sc.editable) {
                columns.get(index).setOnEditCommit(sc.editEventHandler);
            }
            columns.get(index).setEditable(sc.editable);
        }

        if (data != null) {
            ZombieTable.setItems(FXCollections.observableList(data.getZombiesWithWrapper()));
        }
        ZombieTable.getColumns().clear();
        ZombieTable.getColumns().addAll(columns);
    }

    @FXML
    protected void openSaveClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Save Files", "*.json")
        );
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            config.saveSavePath(file.toPath());
            loadZombieTable();
        }
    }

    @FXML
    protected void saveSaveClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Save Files", "*.json")
        );
        File file = fileChooser.showSaveDialog(null);

        if (file != null && data != null) {
            fileHandler.saveSave(file.toPath(), data);
        }
    }
}