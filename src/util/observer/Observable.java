package util.observer;

import java.util.Iterator;
import java.util.Vector;

// Observable class - part of the Observer design pattern
public class Observable {

    // A list of observers (subscribers) that are notified when the state changes
    protected Vector<IObserver> subscribers = new Vector<IObserver>(2);

    // Adds an observer to the list of subscribers
    public void addObserver(IObserver s) {
        subscribers.addElement(s);
    }

    // Removes an observer from the list of subscribers
    public void removeObserver(IObserver s) {
        subscribers.removeElement(s);
    }

    // Removes all observers from the list
    public void removeAllObservers() {
        subscribers.removeAllElements();
    }

    // Notifies all subscribed observers about a change
    public void notifyObservers() {
        // Iterate over all subscribers and call their update method
        for ( Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
            IObserver observer = iter.next();
            observer.update();
        }
    }
}