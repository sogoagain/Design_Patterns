/**
 * Created by sogoagain on 2017. 3. 16..
 */
// 가위바위보 전략 인터페이스
public interface PlayingStrategy {
    HandType nextHand(); // 다음에 낼 손을 반환한다.
    void recordHistory(ResultType currResult); // 지난 게임의 결과를 전달받는다.
    void initializeByPlayer();  // 플레이어가 직접 내용을 초기화할 필요가 있는 전략에 사용된다. (SequentialStrategy)
}
