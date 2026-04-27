package be.firstapirest.dl.enums;

import lombok.Getter;

@Getter
public enum TypeMove {
    IN("In"),
    OUT("Out");

    private final String value;

    TypeMove(String value) {
        this.value = value;
    }

}
