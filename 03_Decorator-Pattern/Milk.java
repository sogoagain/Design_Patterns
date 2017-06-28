/**
 * Created by sogoagain on 2017. 4. 22..
 */

public class Milk extends CondimentDecorator {

    Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    @Override
    public int cost() {
        return beverage.cost() + 100;
    }
}

