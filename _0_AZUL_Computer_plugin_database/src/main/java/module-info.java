module _0_AZUL_Computer_plugin_database {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.studi.azulcomputer.plugin.database to javafx.fxml;
    exports de.studi.azulcomputer.plugin.database;
}