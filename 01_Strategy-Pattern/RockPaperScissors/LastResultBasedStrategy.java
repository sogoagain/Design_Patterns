import java.util.Random;

/**
 * Created by sogoesagain on 2017. 3. 16..
 */
public class LastResultBasedStrategy implements PlayingStrategy {
    private Random randomGen = new Random();    // 난수 생성기 (이긴경우, 비긴경우에 사용)
    /*
    preHand의 초기값은 상대방이 예측할 수 없도록 하기 위해 랜덤으로 설정한다.
    preResult의 값은 이긴 경우로 초기화한다.
     */
    private HandType prevHand = HandType.valueOf(randomGen.nextInt(3));
    private ResultType prevResult = ResultType.WON;

    // 다음에 낼 손을 이전 결과를 통해 결정한다.
    public HandType nextHand() {
        int dontUseHand;    // 내서는 안되는 손의 인덱스
        int nextHand = 0;   // 다음에 낼 손의 인덱스

        switch (prevResult) {
            // 이긴경우 현재 자신이 낸 손을 다시 내면 안된다. (상대방은 같은 손을 다시 내지 않을 것이라 가정)
            // 따라서 현재 낸 손 이외의 두 경우에서 랜덤으로 선택한다.
            case WON:
                dontUseHand = prevHand.ordinal();
                nextHand = (dontUseHand + (randomGen.nextInt(2) + 1)) % 3;
                break;
            // 비긴경우 현재 손을 이기는 손을 내면 안된다. (상대방은 같은 손을 다시 내지 않을 것이라 가정)
            // 따라서 현재 손을 이기는 손을 제외한 두 경우에서 랜덤으로 선택한다.
            case DRAWN:
                dontUseHand = prevHand.winValueOf().ordinal();
                nextHand = (dontUseHand + (randomGen.nextInt(2) + 1)) % 3;
                break;
            // 진경우 상대방 손을 이길 수 있는 손으로 결정한다.
            case LOST:
                nextHand = (prevHand.ordinal() + 2) % 3;
                break;
        }

        // 다음에 낼 손을 반환한다. (손을 내게되면 이전 결과의 손이 된다.)
        prevHand = HandType.valueOf(nextHand);
        return prevHand;
    }

    // 이전 게임의 결과를 받아온다.
    public void recordHistory(ResultType currResult) {
        prevResult = currResult;
    }

    @Override
    public void initializeByPlayer() {
    }
}
