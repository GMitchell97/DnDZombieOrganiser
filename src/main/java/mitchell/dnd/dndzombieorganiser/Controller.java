package mitchell.dnd.dndzombieorganiser;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Data data;

    public Controller(Data data) {
        this.data = data;
    }

    public List<Zombie> getZombieList() {
        return data.zombieList;
    }

}
