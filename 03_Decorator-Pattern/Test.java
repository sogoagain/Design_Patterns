/**
 * Created by sogoesagain on 2017. 4. 22..
 */

public class Test {

    public static void main(String[] args) {

        Beverage mochaWhipHouseBlend = new HouseBlend();
        mochaWhipHouseBlend = new Mocha(mochaWhipHouseBlend);
        mochaWhipHouseBlend = new Whip(mochaWhipHouseBlend);
        System.out.printf("%s: %,d원\n", mochaWhipHouseBlend.getDescription(),
                mochaWhipHouseBlend.cost());

        Beverage whipMochaHouseBlend = new HouseBlend();
        whipMochaHouseBlend = new Whip(whipMochaHouseBlend);
        whipMochaHouseBlend = new Mocha(whipMochaHouseBlend);
        System.out.printf("%s: %,d원\n", whipMochaHouseBlend.getDescription(),
                whipMochaHouseBlend.cost());

        Beverage mochaWhipEspresso = new Espresso();
        mochaWhipEspresso = new Mocha(mochaWhipEspresso);
        mochaWhipEspresso = new Whip(mochaWhipEspresso);
        System.out.printf("%s: %,d원\n", mochaWhipEspresso.getDescription(),
                mochaWhipEspresso.cost());

        Beverage whipEspresso = new Espresso();
        whipEspresso = new Whip(whipEspresso);
        System.out.printf("%s: %,d원\n", whipEspresso.getDescription(),
                whipEspresso.cost());

        Beverage espresso = new Espresso();
        Beverage houseBlend = new HouseBlend();

        Beverage milkMochaWhipEspresso = new Espresso();
        milkMochaWhipEspresso = new Milk(milkMochaWhipEspresso);
        milkMochaWhipEspresso = new Mocha(milkMochaWhipEspresso);
        milkMochaWhipEspresso = new Whip(milkMochaWhipEspresso);
        System.out.printf("%s: %,d원\n", milkMochaWhipEspresso.getDescription(),
                milkMochaWhipEspresso.cost());

        Beverage whipMilkMochaEspresso = new Espresso();
        whipMilkMochaEspresso = new Whip(whipMilkMochaEspresso);
        whipMilkMochaEspresso = new Milk(whipMilkMochaEspresso);
        whipMilkMochaEspresso = new Mocha(whipMilkMochaEspresso);
        System.out.printf("%s: %,d원\n", whipMilkMochaEspresso.getDescription(),
                whipMilkMochaEspresso.cost());

        Beverage whipWhipEspresso = new Espresso();
        whipWhipEspresso = new Whip(whipWhipEspresso);
        whipWhipEspresso = new Whip(whipWhipEspresso);
        System.out.printf("%s: %,d원\n", whipWhipEspresso.getDescription(),
                whipWhipEspresso.cost());

        System.out.println();
        System.out.printf("HouseBlend(Mocha, Whip) %s HouseBlend(Whip, Mocha)\n",
                (mochaWhipHouseBlend.equals(whipMochaHouseBlend)) ? "==":"!=");

        System.out.printf("HouseBlend(Mocha, Whip) %s Espresso(Mocha, Whip)\n",
                (mochaWhipHouseBlend.equals(mochaWhipEspresso)) ?  "==":"!=");

        System.out.printf("Espresso(Mocha, Whip) %s Espresso(Whip)\n",
                (mochaWhipEspresso.equals(whipEspresso)) ?  "==":"!=");

        System.out.printf("Espresso() %s HouseBlend()\n",
                (espresso.equals(houseBlend)) ?  "==":"!=");

        System.out.printf("Espresso(Milk, Mocha, Whip) %s Espresso(Whip, Milk, Mocha)\n",
                (milkMochaWhipEspresso.equals(whipMilkMochaEspresso)) ?  "==":"!=");

        System.out.printf("Espresso(Whip, Whip) %s Espresso(Whip)\n",
                (whipWhipEspresso.equals(whipEspresso)) ?  "==":"!=");
    }
}

