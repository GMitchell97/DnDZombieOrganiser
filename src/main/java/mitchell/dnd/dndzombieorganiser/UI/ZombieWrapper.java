package mitchell.dnd.dndzombieorganiser.UI;

import javafx.beans.property.SimpleStringProperty;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;

public class ZombieWrapper {

    private SimpleStringProperty Name;
    private SimpleStringProperty AC;
    private SimpleStringProperty HP;

    private final ZombieDTO zombie;

    public ZombieWrapper(ZombieDTO z) {
        zombie = z;
        loadVariables();
    }

    private void loadVariables() {
        Name = new SimpleStringProperty(getName());
        AC = new SimpleStringProperty(getAC());
        HP = new SimpleStringProperty(getHP());
    }

    public String getAC() {
        return zombie.getAc();
    }

    public SimpleStringProperty ACProperty() {
        return AC;
    }

    public void setAC(String AC) {
        zombie.setAc(AC);
    }

    public String getHP() {
        return zombie.getHp();
    }

    public SimpleStringProperty HPProperty() {
        return HP;
    }

    public void setHP(String HP) {
        zombie.setHp(HP);
    }

    public String getName() {
        return zombie.getName();
    }

    public SimpleStringProperty nameProperty() {
        return Name;
    }

    public void setName(String name) {
        zombie.setName(name);
    }
}
