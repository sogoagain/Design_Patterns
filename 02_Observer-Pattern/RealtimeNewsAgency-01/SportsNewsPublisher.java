import java.util.Observable;

/**
 * Created by sogoagain on 2017. 3. 23..
 */
public class SportsNewsPublisher extends Observable implements NewsPublisher {
    private String latestFeed;

    @Override
    public void setNewFeed(String newFeed) {
        latestFeed = newFeed;
        setChanged();   // 밖에서 Subject를 조작할 때 유용함.
        // notifyObservers(latestFeed); //push
        notifyObservers();   //pull
    }

    String getLatestFeed() {
        return latestFeed;
    }
}
