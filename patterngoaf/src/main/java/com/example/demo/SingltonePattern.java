import java.io.*;

class Scratch {
    public static void main(String[] args) {
        
    }
}

final class RemoteConector  implements Externalizable {

    private static RemoteConector instance;

    private RemoteConector() {
    }

    public void connect(String address) {

    }

    public static synchronized RemoteConector getInstance() {
        if (instance == null) {
            instance = new RemoteConector();
        }
        return instance;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        throw new UnsupportedEncodingException("Serilization is denied");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        throw new UnsupportedEncodingException("Serilization is denied");
    }
}