package mitchell.dnd.dndzombieorganiser.UI;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mitchell.dnd.dndzombieorganiser.data.properties.Config;
import mitchell.dnd.dndzombieorganiser.data.dto.DataDTO;
import mitchell.dnd.dndzombieorganiser.data.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UIMainController {

    Config config;
    DataDTO data;
    FileHandler fileHandler = new FileHandler();

    @FXML
    private TableView<ZombieWrapper> ZombieTable;

    @FXML
    public void initialize() {

        config = new Config();
        loadData();
        loadZombieTable();
    }

    @FXML
    protected void loadZombieTable() {

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
            columns.get(index).setPrefWidth(sc.width);
            columns.get(index).setStyle("-fx-alignment: CENTER;");
        }

        loadTableData();
        ZombieTable.getColumns().clear();
        ZombieTable.getColumns().addAll(columns);
    }

    private void loadData() {
        if (!config.getSavePath().toString().equals("")) {
            data = fileHandler.loadSave(config.getSavePath());
        } else {
            data = new DataDTO();
        }
    }

    private void loadTableData() {
        ZombieTable.getItems().clear();
        if (data != null) {
            ZombieTable.setItems(FXCollections.observableList(data.getZombiesWithWrapper()));
        }
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
            loadData();
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

    @FXML
    protected void newSaveClick() {
        config.saveSavePath("");
        loadData();
        loadZombieTable();
    }

    @FXML
    protected void close() {
        ((Stage)ZombieTable.getScene().getWindow()).close();
    }

    @FXML
    protected void addZombie() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZOGApplication.class.getResource("AddZombie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        ((UIAddZombieController)fxmlLoader.getController()).setData(data);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.showAndWait();

        loadTableData();
    }
}