import java.util.ArrayList;
import java.util.List;

class ObservablePattern {
    public static void main(String[] args) {
        BankImpl bank = new BankImpl();
        bank.addCreditor(new CreditorImpl(1));
        bank.addCreditor(new CreditorImpl(2));
        bank.addCreditor(new CreditorImpl(3));


        bank.sendNotifications();

    }
}

interface Creditor {

    void handle();
}

class CreditorImpl implements Creditor {

    private int id;

    public CreditorImpl(int id) {
        this.id = id;
    }

    @Override
    public void handle() {
        System.out.println("get push");
    }
}

interface Bank {

    void addCreditor(Creditor creditor);

    void removeCreditor(Creditor creditor);

    void sendNotifications();

}

class BankImpl implements Bank {

    private List<Creditor> creditors;

    public BankImpl() {
        this.creditors = new ArrayList<Creditor>();
    }

    @Override
    public void addCreditor(Creditor creditor) {

        creditors.add(creditor);
    }

    @Override
    public void removeCreditor(Creditor creditor) {
        creditors.remove(creditor);
    }

    @Override
    public void sendNotifications() {
        for (Creditor creditor :
                creditors) {
            creditor.handle();
        }
    }
}

