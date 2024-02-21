module org.example.azulcomputernico {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens org.example.azulcomputernico to javafx.fxml;
    exports org.example.azulcomputernico;
}