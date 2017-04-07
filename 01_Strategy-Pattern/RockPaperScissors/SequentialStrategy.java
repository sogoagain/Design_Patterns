import java.util.*;

/**
 * Created by sogoesagain on 2017. 3. 17..
 *
 * SequentialStrategy 전략은 GeneticStrategy의 성능을 입증하고자 작성했다.
 * 플레이어로부터 입력받은 일련의 패를 반복하며 내는 전략이다.
 * 플레이어로부터 입력받은 값을 바탕으로 전략을 초기화하기 위해 initializeByPlayer() 메소드를 PlayingStrategy 인터페이스에 추가하였다.
 * GeneticStrategy은 SequentialStrategy에 대해 좋은 성적을 거뒀다.
 * (GeneticStrategy의 NUMBER_OF_GENES값과 SequentialStrategy의 나열된 패의 수가 같을때)
 */
public class SequentialStrategy implements PlayingStrategy{
    private List<HandType> hands = new ArrayList<>();
    private Iterator<HandType> iteratorOfHands;

    @Override
    public HandType nextHand() {
        if(!iteratorOfHands.hasNext()) {
            iteratorOfHands = hands.iterator();
        }

        return iteratorOfHands.next();
    }

    @Override
    public void recordHistory(ResultType currResult){ }

    @Override
    public void initializeByPlayer() {
        System.out.println("[SequentialStrategy에서 사용할 패의 나열을 입력하세요. (예. 가위 보 보 바위)]");
        boolean isInputComplete = true;

        do {
            hands.clear();
            String input = new Scanner(System.in).nextLine();
            isInputComplete = true;
            StringTokenizer tokenizer = new StringTokenizer(input, " ");

            while (tokenizer.hasMoreTokens() && isInputComplete) {
                switch (tokenizer.nextToken()) {
                    case "가위":
                        hands.add(HandType.valueOf(0));
                        break;
                    case "바위":
                        hands.add(HandType.valueOf(1));
                        break;
                    case "보":
                        hands.add(HandType.valueOf(2));
                        break;
                    default:
                        System.out.println("['가위', '바위', '보'이외의 값을 입력했습니다. 다시입력하세요.]");
                        isInputComplete = false;
                        break;
                }
            }
        } while(!isInputComplete);
        iteratorOfHands = hands.iterator();
    }
}
