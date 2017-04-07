import java.util.Observer;

/**
 * Created by sogoesagain on 2017. 3. 23..
 */
public interface Subject {
    void addObserver(Observer o);

    void deleteObserver(Observer o);

    void notifyObservers();              // pull 고려

    void notifyObservers(Object data);   // push 고려
}
