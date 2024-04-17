module de.studi.azulcomputer {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens de.studi.azulcomputer.frontend to javafx.fxml;
    exports de.studi.azulcomputer.frontend;
    exports de.studi.azulcomputer.adapters;
    opens de.studi.azulcomputer.adapters to javafx.fxml;
}