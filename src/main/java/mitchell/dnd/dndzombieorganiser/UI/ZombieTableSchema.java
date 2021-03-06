package mitchell.dnd.dndzombieorganiser.UI;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;

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
            new Schema("ID", "ID", false),
            new Schema("Name", "Name", true, t -> ((ZombieWrapper) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setName(t.getNewValue())),
            new Schema("AC", "AC", false),
            new Schema("HP", "HP", false),
            new Schema("Str", "strength", false),
            new Schema("Dex", "dexterity", false),
            new Schema("Con", "constitution", false),
            new Schema("Int", "intelligence", false),
            new Schema("Wis", "wisdom", false),
            new Schema("Chr", "charisma", false)

    );
}
