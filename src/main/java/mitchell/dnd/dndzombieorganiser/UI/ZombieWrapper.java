package mitchell.dnd.dndzombieorganiser.UI;

import javafx.beans.property.SimpleStringProperty;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;

public class ZombieWrapper {

    private SimpleStringProperty ID;
    private SimpleStringProperty Name;
    private SimpleStringProperty AC;
    private SimpleStringProperty HP;
    private SimpleStringProperty strength;
    private SimpleStringProperty dexterity;
    private SimpleStringProperty constitution;
    private SimpleStringProperty intelligence;
    private SimpleStringProperty wisdom;
    private SimpleStringProperty charisma;

    private final ZombieDTO zombie;

    public ZombieWrapper(ZombieDTO z) {
        zombie = z;
        loadVariables();
    }

    private void loadVariables() {
        ID = new SimpleStringProperty(getID());
        Name = new SimpleStringProperty(getName());
        AC = new SimpleStringProperty(getAC());
        HP = new SimpleStringProperty(getHP());
        strength = new SimpleStringProperty(getStrength());
        dexterity = new SimpleStringProperty(getDexterity());
        constitution = new SimpleStringProperty(getConstitution());
        intelligence = new SimpleStringProperty(getIntelligence());
        wisdom = new SimpleStringProperty(getWisdom());
        charisma = new SimpleStringProperty(getCharisma());
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

    public String getID() {
        return zombie.getId();
    }

    public SimpleStringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        zombie.setId(ID);
    }

    public String getStrength() {
        return zombie.getStrength();
    }

    public SimpleStringProperty strengthProperty() {
        return strength;
    }

    public void setStrength(String strength) {
        zombie.setStrength(strength);
    }

    public String getDexterity() {
        return zombie.getDexterity();
    }

    public SimpleStringProperty dexterityProperty() {
        return dexterity;
    }

    public void setDexterity(String dexterity) {
        zombie.setDexterity(dexterity);
    }

    public String getConstitution() {
        return zombie.getConstitution();
    }

    public SimpleStringProperty constitutionProperty() {
        return constitution;
    }

    public void setConstitution(String constitution) {
        zombie.setConstitution(constitution);
    }

    public String getIntelligence() {
        return zombie.getIntelligence();
    }

    public SimpleStringProperty intelligenceProperty() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        zombie.setIntelligence(intelligence);
    }

    public String getWisdom() {
        return zombie.getWisdom();
    }

    public SimpleStringProperty wisdomProperty() {
        return wisdom;
    }

    public void setWisdom(String wisdom) {
        zombie.setWisdom(wisdom);
    }

    public String getCharisma() {
        return zombie.getCharisma();
    }

    public SimpleStringProperty charismaProperty() {
        return charisma;
    }

    public void setCharisma(String charisma) {
        zombie.setCharisma(charisma);
    }
}
