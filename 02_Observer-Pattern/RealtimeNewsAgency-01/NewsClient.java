import java.util.Observable;
import java.util.Observer;

/**
 * Created by sogoagain on 2017. 3. 23..
 */
public class NewsClient implements Observer {
    private String id;

    NewsClient(String id) {
        this.id = id;
    }

    @Override
    public void update(Observable o, Object arg) {
        String latestFeed;
        // latestFeed = (String) arg;   // push

        // pull
        if (o instanceof SportsNewsPublisher) {
            latestFeed = ((SportsNewsPublisher) o).getLatestFeed();
        } else {
            latestFeed = ((ITNewsPublisher) arg).getLatestFeed();
        }
        System.out.println(id + ": " + latestFeed);
    }

    @Override
    public boolean equals(Object obj) {
        // 현재 객체의 클래스와 다른 타입의 클래스가 매개변수로 들어와도 논리적으로 오류 없이 수행하기 위해 false를 반환함.
        if (obj == null || obj.getClass() != getClass()) return false;
        // 수행성능의 optimize를 위해 같은 주소를 가리키는 객체가 들어오면 true를 반환하여 멤버변수를 비교하는 과정을 넘어감
        if (obj == this) return true;

        // 멤버변수 비교
        return (((NewsClient) obj).id).equals(this.id);
    }
}
