package mitchell.dnd.dndzombieorganiser;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ZombieData {

    private final SimpleStringProperty Name;
    private final SimpleStringProperty AC;
    private final SimpleStringProperty HP;

    public ZombieData(String name, String hp, String ac) {
        Name = new SimpleStringProperty(name);
        AC = new SimpleStringProperty(ac);
        HP = new SimpleStringProperty(hp);

    }

    public String getAC() {
        return AC.get();
    }

    public SimpleStringProperty ACProperty() {
        return AC;
    }

    public void setAC(String AC) {
        this.AC.set(AC);
    }

    public String getHP() {
        return HP.get();
    }

    public SimpleStringProperty HPProperty() {
        return HP;
    }

    public void setHP(String HP) {
        this.HP.set(HP);
    }

    public String getName() {
        return Name.get();
    }

    public SimpleStringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        this.Name.set(name);
    }
}
