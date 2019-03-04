package name;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

public class SaleInput extends RealmObject {

   private String name, place, date;
    private int invoice, total, received, balance;
    @Ignore
    private int sessionId;

    public int getSessionId() {
        return sessionId;
    }

    public String getName(){return name;}
    public String getPlace(){return place;}

    public int getBalance() {
        return balance;
    }

    public String getDate() {
        return date;
    }

    public int getInvoice() {
        return invoice;
    }

    public int getTotal() {
        return total;
    }

    public int getReceived() {
        return received;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setReceived(int received) {
        this.received = received;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
