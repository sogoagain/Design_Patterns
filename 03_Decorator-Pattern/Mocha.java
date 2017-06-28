/**
 * Created by sogoagain on 2017. 4. 22..
 */

public class Mocha extends CondimentDecorator {

    Mocha(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public int cost() {
        return beverage.cost() + 200;
    }
}

