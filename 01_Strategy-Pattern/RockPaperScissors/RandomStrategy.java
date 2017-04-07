import java.util.Random;

/**
 * Created by sogoesagain on 2017. 3. 16..
 */
public class RandomStrategy implements PlayingStrategy {
    // 난수생성기
    private Random randomGen = new Random();

    // 다음에 내야할 손을 난수를 이용해 결정한다.
    public HandType nextHand() {
        return HandType.valueOf(randomGen.nextInt(3));
    }

    // 지난 결과값을 받아오는 메소드
    // 지난 결과에 상관 없이 매번 난수를 이용해 결정하므로 RandomStrategy에서는 필요없다.
    public void recordHistory(ResultType currResult) {
    }

    // User로 부터 전략을 설정하는 메소드
    // RandomStrategy에서는 필요없다.
    public void initializeByPlayer() {
    }
}
