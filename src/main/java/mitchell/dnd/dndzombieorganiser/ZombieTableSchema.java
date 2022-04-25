package mitchell.dnd.dndzombieorganiser;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZombieTableSchema {

    public class Schema {
        public String header;
        public String variableName;
        public Boolean editable;
        public EventHandler<TableColumn.CellEditEvent<ZombieWrapper, String>> editEventHandler;

        public Schema(String header, String variableName, Boolean editable, EventHandler<TableColumn.CellEditEvent<ZombieWrapper, String>> editEventHandler) {
            this.header = header;
            this.variableName = variableName;
            this.editable = editable;
            this.editEventHandler = editEventHandler;
        }

        public Schema(String header, String variableName, Boolean editable) {
            this.header = header;
            this.variableName = variableName;
            this.editable = editable;
        }
    }

    public List<Schema> ZombieTableSchema = Arrays.asList(
            new Schema("Name", "Name", true, t -> ((ZombieWrapper) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setName(t.getNewValue())),
            new Schema("AC", "AC", false),
            new Schema("HP", "HP", false)
    );
}
