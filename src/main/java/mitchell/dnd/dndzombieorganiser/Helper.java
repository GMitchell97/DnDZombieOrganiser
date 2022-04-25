package mitchell.dnd.dndzombieorganiser;

import mitchell.dnd.dndzombieorganiser.data.Data;
import mitchell.dnd.dndzombieorganiser.data.Zombie;

import java.util.List;

public class Helper {

    private Data data;

    public Helper(Data data) {
        this.data = data;
    }

    public List<Zombie> getZombieList() {
        return data.getZombieList();
    }

}
