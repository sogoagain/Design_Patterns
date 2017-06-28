/**
 * Created by sogoagain on 2017. 3. 16..
 */
public class Test {
    public static void main(String[] args) {
        Player player1 = new Player(new GeneticStrategy());
        // Player player1 = new Player(new LastResultBasedStrategy());

        Player player2 = new Player(new SequentialStrategy());
        // Player player2 = new Player(new SemiGeneticStrategy());

        for (int i = 0; i < 1000; i++) {
            HandType h1 = player1.nextHand();
            HandType h2 = player2.nextHand();
            System.out.printf("사용자1: %s VS 사용자2: %s", h1, h2);
            if (h1 == h2) {
                System.out.println(" > 결과: 무승부");
                player1.setResult(ResultType.DRAWN);
                player2.setResult(ResultType.DRAWN);
            } else if (h1.winValueOf() == h2) {
                System.out.println(" > 결과: 사용자2 승");
                player1.setResult(ResultType.LOST);
                player2.setResult(ResultType.WON);
            } else {
                System.out.println(" > 결과: 사용자1 승");
                player1.setResult(ResultType.WON);
                player2.setResult(ResultType.LOST);
            }
        }

        System.out.println("사용자1 결과: " + player1);
        System.out.println("사용자2 결과: " + player2);
    }
}
