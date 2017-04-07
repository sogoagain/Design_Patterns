import java.util.Observable;
import java.util.Observer;

/**
 * Created by sogoesagain on 2017. 3. 23..
 */

class ImprovedObservable extends Observable {
    public void setChanged() {
        super.setChanged();
    }
}

public class ITNewsPublisher implements NewsPublisher {
    private ImprovedObservable observable = new ImprovedObservable();
    private String latestFeed;

    @Override
    public void setNewFeed(String newFeed) {
        latestFeed = newFeed;
        observable.setChanged();
        // observable.notifyObservers(latestFeed);  //push
        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        observable.addObserver(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        observable.deleteObserver(o);

    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers(this);
    }

    @Override
    public void notifyObservers(Object data) {
        observable.notifyObservers(data);
    }

    String getLatestFeed() {
        return latestFeed;
    }
}
