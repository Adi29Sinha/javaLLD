import design_patterns.observer_pattern.observable.StocksObservable;
import design_patterns.observer_pattern.observable.iPhoneStockObservable;
import design_patterns.observer_pattern.observers.EmailAlertImpl;
import design_patterns.observer_pattern.observers.NotificationAlertObserver;
import misc.SumOfMillionValues;

public class Main {
    public static void main(String[] args) {

        SumOfMillionValues sum = new SumOfMillionValues();
        sum.sumOfMillionValue();

        StocksObservable stocksObservable = new iPhoneStockObservable();
        NotificationAlertObserver observer1 = new EmailAlertImpl("adi@gmail.com", stocksObservable);
        NotificationAlertObserver observer2 = new EmailAlertImpl("adiSinha@gmail.com", stocksObservable);
        stocksObservable.add(observer1);
        stocksObservable.add(observer2);
        stocksObservable.setCount(10);
        stocksObservable.setCount(30);
        stocksObservable.setCount(20);

    }
}