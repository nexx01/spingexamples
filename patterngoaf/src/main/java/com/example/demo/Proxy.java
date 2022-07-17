
class Proxy {
    public static void main(String[] args) {
        MailSender proxyMailer = new ProxyMailer("sub");
        proxyMailer.send("sub", "bode", "email");
    }
}

interface MailSender{
    boolean send(String subject, String body, String email);
}

class Mailer implements MailSender {
    @Override
    public boolean send(String subject, String body, String email) {
        return true;
    }
}

class ProxyMailer implements MailSender {

    private String serviceAddress="service address";
    private Mailer mailer;


    public ProxyMailer(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    @Override
    public boolean send(String subject, String body, String email) {
        System.out.println("Sended on service Address");
        mailer = new Mailer();
        return mailer.send(subject, body, serviceAddress);
    }
}