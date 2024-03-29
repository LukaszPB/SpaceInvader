module com.example.projekt_ztp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.projekt_ztp to javafx.fxml;
    exports com.example.projekt_ztp;
    exports com.example.projekt_ztp.controllers;
    opens com.example.projekt_ztp.controllers to javafx.fxml;
    exports com.example.projekt_ztp.builder;
    opens com.example.projekt_ztp.builder to javafx.fxml;
}