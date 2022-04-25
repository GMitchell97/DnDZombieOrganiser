package mitchell.dnd.dndzombieorganiser.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import mitchell.dnd.dndzombieorganiser.Helper;
import mitchell.dnd.dndzombieorganiser.data.FileHandler;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    Helper helper;

    @FXML
    private TableView<ZombieWrapper> ZombieTable;

    @FXML
    public void initialize() {

        LoadZombieTable();
    }

    @FXML
    protected void LoadZombieTable() {

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

        FileHandler fileHandler = new FileHandler();
        ZombieTable.setItems(FXCollections.observableList(fileHandler.loadSave("Test").getZombiesWithWrapper()));
        ZombieTable.getColumns().addAll(columns);
    }
}