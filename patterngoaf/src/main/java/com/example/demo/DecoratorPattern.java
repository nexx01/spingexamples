class DecoratorPattern {
    public static void main(String[] args) {
        PhoneNotifier phoneNotifier = new PhoneNotifier(new MessengerNotifier("1111","message"));

        phoneNotifier.notifySubscriber();
    }
}

interface Notifier{
    boolean notifySubscriber();
}

final class MessengerNotifier implements Notifier{
private   String phone;
private   String text;

    public MessengerNotifier(String phone, String text) {
        this.phone = phone;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean notifySubscriber() {
        System.out.println("messegnger send");
        return false;
    }
}

class PhoneNotifier implements Notifier {

    private final MessengerNotifier messengerNotifier;

    public PhoneNotifier(MessengerNotifier messengerNotifier) {
        this.messengerNotifier = messengerNotifier;
    }

    @Override
    public boolean notifySubscriber() {
        return messengerNotifier.notifySubscriber() ? true : sendSms();
    }

    boolean sendSms() {
        boolean isValidLength = messengerNotifier.getText().length() < 160;
        if(isValidLength){
            System.out.println("sms send with text: "+messengerNotifier.getText());
        }
        return isValidLength?true:false;
    }

}



