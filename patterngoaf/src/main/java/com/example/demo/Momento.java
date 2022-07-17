import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

class Momento {
    public static void main(String[] args) {
        HashMap<Product, Integer> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(new Product("p1", 1), 1);
        Order order = new Order(LocalDateTime.now(), objectObjectHashMap, OrderStatus.NEW);
        System.out.println(order);

        LocalDateTime changeTime = order.getChangeTime();
        History history = new History();
        history.add(order.save());

        objectObjectHashMap.put(new Product("p2", 2), 2);
        objectObjectHashMap.put(new Product("p3", 3), 3);
        objectObjectHashMap.put(new Product("p4", 4), 4);
        System.out.println("----------");
        System.out.println(order);

        System.out.println("----------");

        order.load(history.rollback(changeTime));

        System.out.println(order);
        //Order{changeTime=2022-07-17T13:00:07.780143200, prodcts2count={Product{name='p1', price=1}=1}, status=NEW}
        //
        // Save order
        //save document
        //----------
        //Order{changeTime=2022-07-17T13:00:07.780143200, prodcts2count={Product{name='p1', price=1}=1, Product{name='p2', price=2}=2, Product{name='p3', price=3}=3, Product{name='p4', price=4}=4}, status=NEW}
        //----------
        //Rollback last changed
        //Order{changeTime=2022-07-17T13:00:07.780143200, prodcts2count={Product{name='p1', price=1}=1}, status=NEW}

    }
}

class Order{
    private LocalDateTime changeTime;
    private HashMap<Product, Integer> prodcts2count;
    private OrderStatus status;

    public Order(LocalDateTime changeTime, HashMap<Product, Integer> prodcts2count, OrderStatus status) {
        this.changeTime = changeTime;
        this.prodcts2count = prodcts2count;
        this.status = status;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public HashMap<Product, Integer> getProdcts2count() {
        return prodcts2count;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void addProduct(Product product, int count) {
        prodcts2count.put(product, count);
        changeTime = LocalDateTime.now();
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderMemento save(){
        System.out.println(" Save order");
        HashMap<Product, Integer> products = new HashMap<>();
        prodcts2count.forEach((k,v)->{
            products.put(new Product(k), v);
        });

        return new OrderMemento(changeTime, products, status);
    }

    public void load(OrderMemento orderMemento) {
        changeTime = orderMemento.getChangeTime();
        prodcts2count = orderMemento.getProdcts2count();
        status = orderMemento.getStatus();
    }

    @Override
    public String toString() {
        return "Order{" +
                "changeTime=" + changeTime +
                ", prodcts2count=" + prodcts2count +
                ", status=" + status +
                '}';
    }
}

class Product{
    private final String name;
    private final int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product(Product product) {
        this.name= product.name;
        this.price = product.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

enum OrderStatus {
    NEW,
    SENT,
    DELIVERED,
    CANCELED
}

class OrderMemento{
    private LocalDateTime changeTime;
    private HashMap<Product, Integer> prodcts2count;
    private OrderStatus status;

    public OrderMemento(LocalDateTime changeTime, HashMap<Product, Integer> prodcts2count, OrderStatus status)
    {
        this.changeTime = changeTime;
        this.prodcts2count = prodcts2count;
        this.status = status;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public HashMap<Product, Integer> getProdcts2count() {
        return prodcts2count;
    }

    public OrderStatus getStatus() {
        return status;
    }
}

class History {
    private HashMap<LocalDateTime, OrderMemento>
            mementoHashMap = new HashMap<>();

    public void add(OrderMemento memento) {
        System.out.println("save document");
        this.mementoHashMap.put(memento.getChangeTime(), memento);
    }

    public OrderMemento rollback(LocalDateTime changeTime) {
        System.out.println("Rollback last changed");
        return this.mementoHashMap.get(changeTime);
    }
}
