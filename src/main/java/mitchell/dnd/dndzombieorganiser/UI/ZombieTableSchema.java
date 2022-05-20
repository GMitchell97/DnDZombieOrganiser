package mitchell.dnd.dndzombieorganiser.UI;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;

import java.util.Arrays;
import java.util.List;

public class ZombieTableSchema {

    public class Schema {
        public String header;
        public String variableName;
        public int width;
        public Boolean editable;
        public EventHandler<TableColumn.CellEditEvent<ZombieWrapper, String>> editEventHandler;

        public Schema(String header, String variableName, int width, Boolean editable, EventHandler<TableColumn.CellEditEvent<ZombieWrapper, String>> editEventHandler) {
            this.header = header;
            this.variableName = variableName;
            this.width = width;
            this.editable = editable;
            this.editEventHandler = editEventHandler;
        }

        public Schema(String header, String variableName, int width, Boolean editable) {
            this.header = header;
            this.variableName = variableName;
            this.width = width;
            this.editable = editable;
        }
    }

    public List<Schema> ZombieTableSchema = Arrays.asList(
            new Schema("ID", "ID",30, false),
            new Schema("Name", "Name", 60, true, t -> ((ZombieWrapper) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setName(t.getNewValue())),
            new Schema("AC", "AC", 60, false),
            new Schema("HP", "HP", 30, false),
            new Schema("Speed", "speed", 60, false),
            new Schema("Attack", "attack", 200, false),
            new Schema("Str", "strength", 30, false),
            new Schema("Dex", "dexterity", 30, false),
            new Schema("Con", "constitution", 30, false),
            new Schema("Int", "intelligence", 30, false),
            new Schema("Wis", "wisdom", 30, false),
            new Schema("Chr", "charisma", 30, false)

    );
}
