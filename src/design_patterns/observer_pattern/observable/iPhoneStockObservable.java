package design_patterns.observer_pattern.observable;

import design_patterns.observer_pattern.observers.NotificationAlertObserver;

import java.util.ArrayList;
import java.util.List;

public class iPhoneStockObservable implements StocksObservable{

    public List<NotificationAlertObserver> observerList = new ArrayList<>();
    public int stockCount = 0;

    @Override
    public void add(NotificationAlertObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for (NotificationAlertObserver ob : observerList) {
            ob.update();
        }
    }

    @Override
    public void setCount(int newStockAdded) {
        if (stockCount == 0) {
            notifySubscribers();
        }
        stockCount = newStockAdded;
    }
}
