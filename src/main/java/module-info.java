module mitchell.dnd.dndzombieorganiser {
    requires javafx.controls;
    requires javafx.fxml;


    opens mitchell.dnd.dndzombieorganiser to javafx.fxml;
    exports mitchell.dnd.dndzombieorganiser;
}