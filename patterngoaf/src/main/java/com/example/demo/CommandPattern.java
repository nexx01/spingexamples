import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class CommandPattern {
    public static void main(String[] args) throws InterruptedException {

        AircraftCommand
                engineStarter = new EngineStarter(1, 2);

        AircraftCommand upWing = new UpWing();

        ArrayDeque<AircraftCommand> objects = new ArrayDeque<>();
        objects.add(engineStarter);
        objects.add(upWing);
        CommandPool commandPool = new CommandPool(objects);
        commandPool.start();

         Thread.sleep(60000);
    }
}

interface AircraftCommand{
    long getDelay();
}

class EngineStarter implements AircraftCommand {
    long delay;
    int numberAround;

    public EngineStarter(long delay, int numberAround) {
        this.delay = delay;
        this.numberAround = numberAround;
    }

    @Override
    public long getDelay() {
        return delay;
    }

    public int getNumberAround() {
        return numberAround;
    }
}

class UpWing implements AircraftCommand{

    long delay;
    int ungle;


    @Override
    public long getDelay() {
        return delay;
    }

    public int getUngle() {
        return ungle;
    }
}


class CommandPool extends Thread{
    private Queue<AircraftCommand> aircraftCommands;

    public CommandPool(Queue<AircraftCommand> aircraftCommands) {
        this.aircraftCommands = aircraftCommands;
    }

    public void addCommand(AircraftCommand command) {
        aircraftCommands.offer(command);
    }

    @Override
    public void run() {
        while (true) {
            if (aircraftCommands.peek() != null) {
                AircraftCommand poll = aircraftCommands.poll();
                poll.getDelay();
                try {
                    Thread.sleep(poll.getDelay());
                    Thread thread = new Thread(() -> System.out.println("1"));
                    thread.start();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }



            }
        }
    }
}

class CommandExecutor {


}
