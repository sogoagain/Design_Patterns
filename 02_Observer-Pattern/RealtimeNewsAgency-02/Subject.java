import java.util.Observer;

/**
 * Created by sogoesagain on 2017. 3. 23..
 */
public interface Subject {
    void addObserver(Observer o);
    void deleteObserver(Observer o);
    void notifyObservers();
}
