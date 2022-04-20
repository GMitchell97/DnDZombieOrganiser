package mitchell.dnd.dndzombieorganiser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    Controller controller;

    @FXML
    private TableView<ZombieData> ZombieTable;

    @FXML
    public void initialize() {

        LoadZombieTable();
    }

    @FXML
    protected void LoadZombieTable() {

        ZombieTable.setEditable(true);

        TableColumn<ZombieData, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<ZombieData, String>("Name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ZombieData, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ZombieData, String> t) {
                        ((ZombieData) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setName(t.getNewValue());
                    }
                }
        );
        nameColumn.setEditable(true);

        TableColumn<ZombieData, String> hpColumn = new TableColumn<>("HP");
        hpColumn.setCellValueFactory(
                new PropertyValueFactory<ZombieData, String>("HP"));
        hpColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        hpColumn.setEditable(false);

        TableColumn<ZombieData, String> acColumn = new TableColumn<>("AC");
        acColumn.setCellValueFactory(
                new PropertyValueFactory<ZombieData, String>("AC"));
        acColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        acColumn.setEditable(false);

        ZombieTable.setItems(FXCollections.observableArrayList(
                new ZombieData("gerard", "34", "16"),
                new ZombieData("fred", "34", "16")
        ));
        ZombieTable.getColumns().addAll(nameColumn, hpColumn, acColumn);
    }
}