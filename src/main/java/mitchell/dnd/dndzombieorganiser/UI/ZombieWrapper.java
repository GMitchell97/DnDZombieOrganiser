package mitchell.dnd.dndzombieorganiser.UI;

import javafx.beans.property.SimpleStringProperty;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;
import mitchell.dnd.dndzombieorganiser.data.Ability;

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
        return Integer.toString(zombie.getAbilityScores().stream().filter(a -> a.getName().equals("strength")).mapToInt(Ability::getValue).findFirst().orElse(0));
    }

    public SimpleStringProperty strengthProperty() {
        return strength;
    }

    public void setStrength(String strength) {
        zombie.getAbilityScores().stream().filter(a -> a.getName().equals("strength")).findFirst().ifPresent(ability -> ability.setValue(Integer.parseInt(strength)));
    }

    public String getDexterity() {
        return Integer.toString(zombie.getAbilityScores().stream().filter(a -> a.getName().equals("dexterity")).mapToInt(Ability::getValue).findFirst().orElse(0));
    }

    public SimpleStringProperty dexterityProperty() {
        return dexterity;
    }

    public void setDexterity(String dexterity) {
        zombie.getAbilityScores().stream().filter(a -> a.getName().equals("dexterity")).findFirst().ifPresent(ability -> ability.setValue(Integer.parseInt(dexterity)));
    }

    public String getConstitution() {
        return Integer.toString(zombie.getAbilityScores().stream().filter(a -> a.getName().equals("constitution")).mapToInt(Ability::getValue).findFirst().orElse(0));
    }

    public SimpleStringProperty constitutionProperty() {
        return constitution;
    }

    public void setConstitution(String constitution) {
        zombie.getAbilityScores().stream().filter(a -> a.getName().equals("constitution")).findFirst().ifPresent(ability -> ability.setValue(Integer.parseInt(constitution)));
    }

    public String getIntelligence() {
        return Integer.toString(zombie.getAbilityScores().stream().filter(a -> a.getName().equals("intelligence")).mapToInt(Ability::getValue).findFirst().orElse(0));
    }

    public SimpleStringProperty intelligenceProperty() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        zombie.getAbilityScores().stream().filter(a -> a.getName().equals("intelligence")).findFirst().ifPresent(ability -> ability.setValue(Integer.parseInt(intelligence)));
    }

    public String getWisdom() {
        return Integer.toString(zombie.getAbilityScores().stream().filter(a -> a.getName().equals("wisdom")).mapToInt(Ability::getValue).findFirst().orElse(0));
    }

    public SimpleStringProperty wisdomProperty() {
        return wisdom;
    }

    public void setWisdom(String wisdom) {
        zombie.getAbilityScores().stream().filter(a -> a.getName().equals("wisdom")).findFirst().ifPresent(ability -> ability.setValue(Integer.parseInt(wisdom)));
    }

    public String getCharisma() {
        return Integer.toString(zombie.getAbilityScores().stream().filter(a -> a.getName().equals("charisma")).mapToInt(Ability::getValue).findFirst().orElse(0));
    }

    public SimpleStringProperty charismaProperty() {
        return charisma;
    }

    public void setCharisma(String charisma) {
        zombie.getAbilityScores().stream().filter(a -> a.getName().equals("charisma")).findFirst().ifPresent(ability -> ability.setValue(Integer.parseInt(charisma)));
    }
}
