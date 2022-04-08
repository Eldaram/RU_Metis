package src.main.java.errors;

public class NotValidDice extends Exception{
    public NotValidDice(Integer position) {
        super("There has been an error on the " + position.toString() + " char.");
        pos = position;
    }

    private Integer pos;

    public Integer getPos() {
        return pos;
    }
}
