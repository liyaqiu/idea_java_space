package design.designiterator;

public class PersonalBean {
    private String name;
    private int age;

    public PersonalBean(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonalBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
