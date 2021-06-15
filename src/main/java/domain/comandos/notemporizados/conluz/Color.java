package domain.comandos.notemporizados.conluz;

public enum Color {
    ROJO,
    VERDE,
    AMARILLO;

    public String equivalente(Color unColor){
        switch (unColor){
            case AMARILLO: return "amarillo";
            case VERDE: return "verde";
            default: return "rojo";
        }
    }
}