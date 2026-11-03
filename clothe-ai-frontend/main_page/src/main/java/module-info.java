module clothes.ai {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens clothes.ai to javafx.fxml;
    exports clothes.ai;
}
