module com.mycompany.cashnote {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens com.mycompany.cashnote to javafx.fxml;
    exports com.mycompany.cashnote;
}
