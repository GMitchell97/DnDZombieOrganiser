module mitchell.dnd.dndzombieorganiser {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.annotation;
    requires java.net.http;


    opens mitchell.dnd.dndzombieorganiser to javafx.fxml;
    exports mitchell.dnd.dndzombieorganiser;
    exports mitchell.dnd.dndzombieorganiser.UI;
    opens mitchell.dnd.dndzombieorganiser.UI to javafx.fxml;
    exports mitchell.dnd.dndzombieorganiser.data;
    opens mitchell.dnd.dndzombieorganiser.data to javafx.fxml, com.fasterxml.jackson.databind;
    exports mitchell.dnd.dndzombieorganiser.core;
    opens mitchell.dnd.dndzombieorganiser.core to com.fasterxml.jackson.databind, javafx.fxml;
}