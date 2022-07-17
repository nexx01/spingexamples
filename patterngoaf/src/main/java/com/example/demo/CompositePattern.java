import java.util.List;

class Composite {
    public static void main(String[] args) {
        ServerStay serverStay1 = new ServerStay(List.of(new Server(List.of(new Disk(9)))));
        ServerStay serverStay2 = new ServerStay(List.of(new Server(List.of(new Disk(9)))));
        DataCenter dataCenter = new DataCenter(List.of(new ServerStay(List.of(new Server(List.of(new Disk(9))))),
                serverStay2,serverStay2));

        System.out.println(dataCenter.getVolume());


    }
}

interface Storage{
    int getVolume();
}

class DataCenter implements Storage {

    List<ServerStay> serverStays;

    public DataCenter(List<ServerStay> serverStays) {
        this.serverStays = serverStays;
    }

    @Override
    public int getVolume() {
        return serverStays.stream()
                .map(Storage::getVolume)
                .reduce((s1,s2)->s1+s2)
                .orElse(0);
    }
}

class ServerStay implements Storage {

    List<Server> servers;

    public ServerStay(List<Server> servers) {
        this.servers = servers;
    }

    @Override
    public int getVolume() {
        return servers.stream()
                .map(Storage::getVolume)
                .reduce(Integer::sum)
                .orElse(0);
    }
}

class Server implements Storage {

    List<Disk> disks;

    public Server(List<Disk> disks) {
        this.disks = disks;
    }

    @Override
    public int getVolume() {
        return disks.stream()
                .map(Storage::getVolume)
                .reduce(Integer::sum)
                .orElse(0);
    }
}

class Disk implements Storage {

    public Disk(int size) {
        this.size = size;
    }

    int size;

    @Override
    public int getVolume() {
        return size;
    }
}