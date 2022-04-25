package mitchell.dnd.dndzombieorganiser;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

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

        ZombieTable.setItems(FXCollections.observableArrayList(
                new ZombieWrapper(new Zombie(34,16)),
                new ZombieWrapper(new Zombie(34, 16))
        ));
        ZombieTable.getColumns().addAll(columns);
    }
}