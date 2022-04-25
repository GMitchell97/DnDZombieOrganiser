package mitchell.dnd.dndzombieorganiser;

import mitchell.dnd.dndzombieorganiser.data.DataDTO;
import mitchell.dnd.dndzombieorganiser.data.ZombieDTO;

import java.util.List;

public class Helper {

    private DataDTO dataDTO;

    public Helper(DataDTO dataDTO) {
        this.dataDTO = dataDTO;
    }

    public List<ZombieDTO> getZombieList() {
        return dataDTO.getZombies();
    }

}
