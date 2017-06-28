/**
 * Created by sogoagain on 2017. 3. 16..
 */
public class Player {
    private int[] resultCount = new int[3]; // 패, 무, 승
    private PlayingStrategy strategy;   // 플레이 전략

    // 설정한 전략으로 Player를 생성하는 생성자
    public Player(PlayingStrategy strategy) {
        setStrategy(strategy);
    }

    // 게임 결과 집계
    public void setResult(ResultType resultType) {
        ++resultCount[resultType.ordinal()];
        // 이번 게임 결과를 전략으로 보내준다.
        strategy.recordHistory(resultType);
    }

    // 총 게임 횟수 계산
    private int getGameCount() {
        int gameCount = 0;

        for (int i : resultCount) {
            gameCount += i;
        }

        return gameCount;
    }

    // 전략 설정
    public void setStrategy(PlayingStrategy strategy) {
        this.strategy = strategy;
        this.strategy.initializeByPlayer();
    }

    // 다음에 낼 손을 결정한다.
    public HandType nextHand() {
        return strategy.nextHand(); // 다음손은 전략에 의해 결정된다.
    }

    // 게임의 진행상태를 String으로 가공하여 반환함
    @Override
    public String toString() {
        return "게임수[" + getGameCount() + "] 승[" + resultCount[2] + "] 패[" + resultCount[0] + "] 무승부[" + resultCount[1] + "]";
    }

}
