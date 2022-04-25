package mitchell.dnd.dndzombieorganiser.data;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<Zombie> zombieList = new ArrayList<>();

    public List<Zombie> getZombieList() {
        return zombieList;
    }

    public void setZombieList(List<Zombie> zombieList) {
        this.zombieList = zombieList;
    }
}
