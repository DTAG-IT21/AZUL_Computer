module org.example.azulcomputernico {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens de.studi.azulcomputer.frontend to javafx.fxml;
    exports de.studi.azulcomputer.frontend;
}