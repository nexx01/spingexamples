class AbstractFactory {
    public static void main(String[] args) {
        BuildingFactory buildingFactory = new SchoolFactory();

        System.out.println(buildingFactory.createFundament());

        HouseFactory houseFactory = new HouseFactory();
        System.out.println(houseFactory.createFundament());
    }
}

interface House{ }

interface School{}

class ModernSchoolBuilding implements School {

}

class ClassicSchoolBuilding implements School {

}

class ModernHouseBuilding implements House {
}

class ClassicHouseBuilding implements House {

}

interface BuildingFactory {
    String createWindow();

    String createFundament();
}

class HouseFactory implements BuildingFactory {


    @Override
    public String createWindow() {
        return "houseWindow";
    }

    @Override
    public String createFundament() {
        return "housefundament";
    }
}

class SchoolFactory implements BuildingFactory {

    @Override
    public String createWindow() {
        return "schoolWindow";
    }

    @Override
    public String createFundament() {
        return "schoolFundament";
    }
}