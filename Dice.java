import java.util.Random;

public class Dice {
    private int sides;

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public Dice() {
    }

    public Dice(int sides) {
        this.sides = sides;
    }

    public double roll() {
        Random rand = new Random();
        return rand.nextInt(getSides()) + 1;
    }

}
