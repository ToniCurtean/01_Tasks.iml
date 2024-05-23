module tasks {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires log4j;


    opens tasks.model;
    exports tasks.model;
    opens tasks.view;
    exports tasks.view;
    opens tasks.controller;
    exports tasks.controller;

    opens tasks.services;
    exports tasks.services;
}
