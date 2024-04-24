package System.Hotel.Hotel.System.enums;

public enum Status {
    APROVADO("aprovado"),
    NEGADO("negado"),
    AGUARDE("aguarde");

    private String status;

    private Status(String status) {
        this.status = status;
    }

}
