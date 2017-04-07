import java.util.*;

/**
 * Created by sogoesagain on 2017. 3. 23..
 *
 * 현실 세계에서 신문을 구독하는 방식을 생각해 notifyObservers()와 updata() 메소드를 모델링 하였다.
 * BreakingNewsPublisher는 Map(HashMap)을 이용해 Observer(구독자)들과 구독자들이 받아볼 뉴스(신문)를 관리한다.
 * Map의 key는 Observer 객체, value는 Queue<String>다.
 * Queue에는 해당 구독자가 받아볼 뉴스(신문)들이 먼저 들어온 순서대로 저장되어있다.
 * Publisher는 Observer의 getInterval() 메소드를 이용해 구독자가 신문이 얼마나 쌓였을 때 받아볼 것인지 알 수 있다.
 * Publisher는 새로운 신문이 들어오면 모든 구독자의 Queue에 신문을 저장한 뒤 (setNewFeed())
 * 모든 구독자에 대해 Queue에 쌓여있는 신문의 개수(readyQueue.size())와 구독자가 요청한 한꺼번에 받아볼 신문 개수(newsClient.getInterval())를 비교한다.
 * 만일 쌓여있는 신문의 개수와 구독자가 요청한 개수가 같은 것이 있다면 해당 구독자에게 그동안 보관했던 신문들을 전달한다. (notifyObservers())
 * 구독자는 update()를 통해 BreakingNewsPublisher로부터 받은 신문들을 가져와 읽는다.
 */
public class BreakingNewsPublisher implements NewsPublisher {
    Map<Observer, Queue<String>> observers = new HashMap<>();

    @Override
    public void addObserver(Observer o) {
        observers.put(o, new LinkedList<>());
    }

    @Override
    public void deleteObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Map.Entry<Observer, Queue<String>> entry : observers.entrySet()) {
            NewsClient newsClient = (NewsClient) entry.getKey();
            Queue<String> readyQueue = entry.getValue();

            if (newsClient.getInterval() <= readyQueue.size()) {
                newsClient.update(null, entry.getValue());
            }
        }
    }

    @Override
    public void setNewFeed(String newFeed) {
        for (Map.Entry<Observer, Queue<String>> entry : observers.entrySet()) {
            Queue<String> readyQueue = entry.getValue();
            readyQueue.offer(newFeed);
        }
        notifyObservers();
    }
}
