module mitchell.dnd.dndzombieorganiser {
    requires javafx.controls;
    requires javafx.fxml;


    opens mitchell.dnd.dndzombieorganiser to javafx.fxml;
    exports mitchell.dnd.dndzombieorganiser;
    exports mitchell.dnd.dndzombieorganiser.UI;
    opens mitchell.dnd.dndzombieorganiser.UI to javafx.fxml;
    exports mitchell.dnd.dndzombieorganiser.data;
    opens mitchell.dnd.dndzombieorganiser.data to javafx.fxml;
}