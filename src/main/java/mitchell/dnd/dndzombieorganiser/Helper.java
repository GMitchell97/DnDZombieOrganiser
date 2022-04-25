package mitchell.dnd.dndzombieorganiser;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    private Data data;

    public Helper(Data data) {
        this.data = data;
    }

    public List<Zombie> getZombieList() {
        return data.zombieList;
    }

}
