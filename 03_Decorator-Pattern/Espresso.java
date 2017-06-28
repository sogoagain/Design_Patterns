/**
 * Created by sogoagain on 2017. 4. 22..
 */

public class Espresso extends Beverage {

    Espresso() {
        setDescription("Espresso");
    }

    @Override
    public int cost() {
        return 700;
    }
}

