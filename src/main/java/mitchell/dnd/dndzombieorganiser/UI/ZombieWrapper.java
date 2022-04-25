package mitchell.dnd.dndzombieorganiser.UI;

import javafx.beans.property.SimpleStringProperty;
import mitchell.dnd.dndzombieorganiser.data.Zombie;

public class ZombieWrapper {

    private SimpleStringProperty Name;
    private SimpleStringProperty AC;
    private SimpleStringProperty HP;

    private final Zombie zombie;

    public ZombieWrapper(Zombie z) {
        zombie = z;
        loadVariables();
    }

    private void loadVariables() {
        Name = new SimpleStringProperty("Zombie");
        AC = new SimpleStringProperty(getAC());
        HP = new SimpleStringProperty(getHP());
    }

    public String getAC() {
        return Integer.toString(zombie.getAC());
    }

    public SimpleStringProperty ACProperty() {
        return AC;
    }

    public void setAC(String AC) {
        zombie.setAC(Integer.parseInt(AC));
    }

    public String getHP() {
        return Integer.toString(zombie.getHP());
    }

    public SimpleStringProperty HPProperty() {
        return HP;
    }

    public void setHP(String HP) {
        zombie.setHP(Integer.parseInt(HP));
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
