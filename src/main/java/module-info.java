module ch.challangerson.javaproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.zaxxer.hikari;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;

    opens ch.challangerson to javafx.fxml;
    exports ch.challangerson;
}