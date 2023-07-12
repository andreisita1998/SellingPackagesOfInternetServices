module com.example.vanzarepachetinternet {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vanzarepachetinternet to javafx.fxml;
    exports com.example.vanzarepachetinternet;
}