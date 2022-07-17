class StatePattern {
    public static void main(String[] args) {

        Order order = new Order();
        System.out.println(order.getState());

        order.setSendedState();
        order.setDeliveredState();
        order.setCanceleedState();

    }
}


class Order {
    private State state;

    public Order() {
        this.state = new NewState();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setNewState() {
        state.setNewState(this);
    }
    public void setCanceleedState() {
        state.setCanceledState(this);
    }
    public void setSendedState() {
        state.setSended(this);
    }
    public void setDeliveredState() {
        state.setDeliveredState(this);
    }
}

interface State{

    void setNewState(Order order);

    void setCanceledState(Order order);

    void setDeliveredState(Order order);

    void setSended(Order order);
}

class NewState implements  State{

    private String stateName;


    @Override
    public void setNewState(Order order) {
        if(order.getState() instanceof SendedState ){
            order.setState(new NewState());
            System.out.println("Статус заказа перевед в new");
        }   else {
            System.out.println("Order with status "+order.getState() + "" +
                    "can not change to new state");
        }
    }

    @Override
    public void setCanceledState(Order order) {
        order.setState(new CanceledState());
        System.out.println("Заказ отметен");
    }

    @Override
    public void setDeliveredState(Order order) {
            System.out.println("Order with status "+order.getState() + "" +
                    "can not delivered");
    }

    @Override
    public void setSended(Order order) {
        order.setState(new SendedState());
        System.out.println("Order state changed to sended");
    }
}

class SendedState implements State{
    @Override
    public void setNewState(Order order) {
        order.setState(new NewState());
        System.out.println("Order state changed to new");
    }

    @Override
    public void setCanceledState(Order order) {
        order.setState(new CanceledState());
        System.out.println("Заказ отметен");
    }

    @Override
    public void setDeliveredState(Order order) {
        order.setState(new DeliveredState());
        System.out.println("Order state changed to delivered");

    }

    @Override
    public void setSended(Order order) {
        System.out.println("Order state already  sended");
    }
}

class CanceledState implements State{
    @Override
    public void setNewState(Order order) {
        System.out.println("Order state can't changed to new");
    }

    @Override
    public void setCanceledState(Order order) {
        System.out.println("Заказ alredy is canceled");
    }

    @Override
    public void setDeliveredState(Order order) {
        System.out.println("Order state can't changed to delivered");

    }

    @Override
    public void setSended(Order order) {
        System.out.println("Order state can't changed to sened");

    }
}

class DeliveredState implements State{
    @Override
    public void setNewState(Order order) {
        System.out.println("Order state can't changed to new");
    }

    @Override
    public void setCanceledState(Order order) {
        order.setState(new CanceledState());
        System.out.println("Заказ отметен");
    }

    @Override
    public void setDeliveredState(Order order) {
        System.out.println("Order state already is delivered");

    }

    @Override
    public void setSended(Order order) {
        order.setState(new SendedState());
        System.out.println("Order state  changed to sended");

    }
}
