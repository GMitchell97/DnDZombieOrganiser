package mitchell.dnd.dndzombieorganiser;

import java.util.ArrayList;
import java.util.List;

public class Zombie {

    private int HP = 0;
    private int AC = 8;

    public Zombie(int HP, int AC) {
        this.HP = HP;
        this.AC = AC;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAC() {
        return AC;
    }

    public void setAC(int AC) {
        this.AC = AC;
    }
}
