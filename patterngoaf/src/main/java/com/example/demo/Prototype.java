import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

class Scratch {
    public static void main(String[] args) {
        HashMap<Product, Integer> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(new Product("p1", 1), 1);
        objectObjectHashMap.put(new Product("p2", 2), 2);
        objectObjectHashMap.put(new Product("p3", 3), 3);
        objectObjectHashMap.put(new Product("p4", 4), 4);


        Order order = new Order(LocalDateTime.now(), objectObjectHashMap, OrderStatus.NEW);

        Order order1 = new Order(order);
        System.out.println(order);
        System.out.println("------");
        System.out.println(order1);

        System.out.println();
        System.out.println();

        order1.setStatus(OrderStatus.SENT);
        order1.getProdcts2count().entrySet().forEach(k->k.setValue(k.getValue()+100));
        System.out.println(order);
        System.out.println("------");
        System.out.println(order1);

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

    public Order(Order order) {
        this.status = order.getStatus();
        this.changeTime = order.changeTime;

        this.prodcts2count = new HashMap<>();

        order.prodcts2count.forEach((k,v)->{
            this.prodcts2count.put(new Product(k), v);
        });


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
        this.price = product.price;
        this.name = product.name;
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