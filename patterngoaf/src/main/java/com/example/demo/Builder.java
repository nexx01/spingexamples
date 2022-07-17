class Builder {
    public static void main(String[] args) {
        Person person = new Person.PersonBuilder()
                .setAddress("ssssss")
                .setCityName("fffff")
                .build();

        System.out.println(person);
    }
}

class Person{
    String name;
    String login;
    String password;
    String birthDate;
    String cityName;
    String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", cityName='" + cityName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static class PersonBuilder{

        private Person person = new Person();

        public PersonBuilder setName(String name) {
            this.person.name = name;return this;
        }

        public PersonBuilder setLogin(String login) {
            this.person.login = login;return this;
        }

        public PersonBuilder setPassword(String password) {
            this.person.password = password;return this;
        }

        public PersonBuilder setBirthDate(String birthDate) {
            this.person.birthDate = birthDate;return this;
        }

        public PersonBuilder setCityName(String cityName) {
            this.person.cityName = cityName;return this;
        }

        public PersonBuilder setAddress(String address) {
            this.person.address = address; return this;
        }

        public Person build() {
            return this.person;
        }
    }
}