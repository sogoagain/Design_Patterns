/**
 * Created by sogoagain on 2017. 4. 22..
 */

public class Whip extends CondimentDecorator {

    Whip(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    @Override
    public int cost() {
        return beverage.cost() + 300;
    }
}

