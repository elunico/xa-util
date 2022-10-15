package co.eluni.xautil;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

class Tag {
    public Tag(String value) {
        this.value = new SimpleStringProperty(value);
    }

SimpleStringProperty value;
}
