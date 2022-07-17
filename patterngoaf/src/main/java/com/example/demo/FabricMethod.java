import java.time.LocalDateTime;

class Scratch {
    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        System.out.println(carFactory.getCar(CarType.SEDAN));
    }
}

interface Car{
    int getWeight();

    LocalDateTime getDateTime();

    double engineCapacity();

}

class CarFactory{
    public Car getCar(CarType type) {
        Car car = null;
        switch (type){
            case SEDAN:
                car = new Sedan(LocalDateTime.now(), 3);
                break;
            case JEEP:
                car = new Jeep(LocalDateTime.now(), 5);
                break;
            default:
                throw new  IllegalArgumentException("Wrong car type " + type);
        }
        return car;
    }
}

class Sedan implements Car {

    private final LocalDateTime localDateTime;
    private final int weight;

    public Sedan(LocalDateTime localDateTime, int weight) {
        this.localDateTime = localDateTime;
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    @Override
    public double engineCapacity() {
        return 0;
    }

    @Override
    public String toString() {
        return "Sedan{" +
                "localDateTime=" + localDateTime +
                ", weight=" + weight +
                '}';
    }
}

class Jeep implements Car {

    private final LocalDateTime localDateTime;
    private final int weight;

    public Jeep(LocalDateTime localDateTime, int weight) {
        this.localDateTime = localDateTime;
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public LocalDateTime getDateTime() {
        return localDateTime;
    }

    @Override
    public double engineCapacity() {
        return 0;
    }

    @Override
    public String toString() {
        return "Jeep{" +
                "localDateTime=" + localDateTime +
                ", weight=" + weight +
                '}';
    }
}

 enum  CarType{
    SEDAN,JEEP;
}

