import bagel.AbstractGame;
import bagel.Input;

public class ShadowLife extends AbstractGame {
    @Override
    protected void update(Input input) {
    }

    public static void main(String[] args) {
        new ShadowLife().run();
        int x = 5;
        x = (int) (x / (2 * 1.0)) * 2;
        System.out.println(x);
    }
}
