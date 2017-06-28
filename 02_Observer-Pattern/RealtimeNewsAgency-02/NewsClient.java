import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

/**
 * Created by sogoagain on 2017. 3. 23..
 *
 * 구독자는 update()를 통해 BreakingNewsPublisher로부터 받은 신문을 가져와 읽는다.
 */
public class NewsClient implements Observer {
    private String id;
    private int interval = 0;

    NewsClient(String id) {
        this.id = id;
    }

    @Override
    public void update(Observable o, Object arg) {
        Queue<String> news = (Queue<String>) arg;

        // 뉴스 출력
        System.out.println("구독자: " + id);
        while (!news.isEmpty()) {
            System.out.println(news.poll());
        }
        System.out.println("====================\n");
    }

    void setInterval(int interval) {
        this.interval = interval;
    }

    int getInterval() {
        return interval;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) return false;
        if (o == this) return true;
        NewsClient newsClient = (NewsClient) o;
        return id.equals(newsClient.id);
    }
}
