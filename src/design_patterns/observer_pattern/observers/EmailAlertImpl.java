package design_patterns.observer_pattern.observers;

import design_patterns.observer_pattern.observable.StocksObservable;

public class EmailAlertImpl implements NotificationAlertObserver{
    String emailId;
    StocksObservable stocksObservable;

    public EmailAlertImpl(String emailId, StocksObservable stocksObservable) {
        this.emailId = emailId;
        this.stocksObservable = stocksObservable;
    }

    @Override
    public void update() {
        sendAlert(emailId, "Alert send to");
    }

    private void sendAlert(String emailId, String msg) {
        System.out.println(msg + ":" + emailId);
    }
}
