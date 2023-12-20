package socket.bio.tcp.test3;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private byte[] b = new byte[1024*10];

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
