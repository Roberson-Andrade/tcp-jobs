module org.jobs {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.json;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens org.jobs.app to javafx.fxml;
    exports org.jobs.app;
    exports org.jobs.pages;
    opens org.jobs.pages to javafx.fxml;
}

