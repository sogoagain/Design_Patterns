import java.util.*;

/**
 * Created by sogoagain on 2017. 4. 22..
 */
public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;

    CondimentDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    public abstract String getDescription();

    public Beverage removeCondiment() {
        return beverage;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        Beverage thisBeverage = this;
        Beverage otherBeverage = (Beverage) obj;
        List<String> thisDecorators = new ArrayList<>();
        List<String> otherDecorators = new ArrayList<>();

        thisDecorators.add(thisBeverage.getClass().getName());
        while (thisBeverage != thisBeverage.removeCondiment()) {
            thisDecorators.add(thisBeverage.getClass().getName());
            thisBeverage = thisBeverage.removeCondiment();
        }
        thisDecorators.add(thisBeverage.getClass().getName());

        otherDecorators.add(otherBeverage.getClass().getName());
        while (otherBeverage instanceof CondimentDecorator) {
            otherDecorators.add(otherBeverage.getClass().getName());
            otherBeverage = otherBeverage.removeCondiment();
        }
        otherDecorators.add(otherBeverage.getClass().getName());

        if(thisDecorators.size() != otherDecorators.size()) {
            return false;
        }

        thisDecorators.removeAll(otherDecorators);

        return thisDecorators.size() == 0;

    }
}

