module com.program2.studystruct {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.program2.studystruct to javafx.fxml;
    exports com.program2.studystruct;
}