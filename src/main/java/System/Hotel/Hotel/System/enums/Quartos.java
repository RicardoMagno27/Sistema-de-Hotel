package System.Hotel.Hotel.System.enums;

public enum Quartos {

    SIMPLES("simples"),
    SUITE("suite"),
COMPARTILHADO("compartilhado");

    private String quartos;

    private Quartos(String quartos) {
        this.quartos = quartos;
    }


}
