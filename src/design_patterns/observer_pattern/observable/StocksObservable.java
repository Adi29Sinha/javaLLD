package design_patterns.observer_pattern.observable;

import design_patterns.observer_pattern.observers.NotificationAlertObserver;

public interface StocksObservable {
    public void add(NotificationAlertObserver observer);

    public void remove(NotificationAlertObserver observer);

    public void notifySubscribers();

    public void setCount(int newStockAdded);
}
