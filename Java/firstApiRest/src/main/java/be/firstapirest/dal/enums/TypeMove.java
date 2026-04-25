package be.firstapirest.dal.enums;

public enum TypeMove {
    IN("In"),
    OUT("Out");

    private String value;

    TypeMove(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
