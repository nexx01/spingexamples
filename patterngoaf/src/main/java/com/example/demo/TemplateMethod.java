class TemplateMethod {
    public static void main(String[] args) {

        ElectricDevice lamp = new Lamp();

        lamp.switchOff();
        lamp.switchOff();
        lamp.switchOn();
        lamp.switchOn();
        lamp.switchOn();
        lamp.switchOff();
        System.out.println("--------");
        lamp.toogle();
        lamp.toogle();
        lamp.toogle();
        lamp.toogle();
        lamp.toogle();
        lamp.toogle();

    }
}

abstract class ElectricDevice{


    public final void toogle() {
        boolean state = isSwitchedOn();

        if(state){
            switchOff();
        }

        if(!state){
            switchOn();
        }


    }

    abstract void switchOn();

    abstract void switchOff();

    abstract boolean isSwitchedOn();
}

class Lamp extends ElectricDevice {

    private boolean state;

    @Override
    void switchOn() {
        this.state = true;

        System.out.println("lamp is on");
    }

    @Override
    void switchOff() {
        this.state = false;
        System.out.println("lamp is off");
    }

    @Override
    boolean isSwitchedOn() {
        return state;
    }
}