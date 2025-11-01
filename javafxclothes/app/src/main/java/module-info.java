module clothes.ai {
    requires javafx.controls;
    requires javafx.fxml;

    opens clothes.ai to javafx.fxml;
    exports clothes.ai;
}
