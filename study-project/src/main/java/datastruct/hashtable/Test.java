package datastruct.hashtable;

public class Test {
    public static void main(String[] args) {
//        MyHashSet<Person> myHashSet = new MyHashSet<>();
//        Person person = new Person("1", "李雅秋", 1);
//        myHashSet.add(person);
//        person.setHashValue(2);
//        myHashSet.add(person);
//
//       myHashSet.add(new Person("2", "李雅秋2",1));
//        myHashSet.showList();


        MyHashTable<Person> hashTable = new MyHashTable<Person>(10);
        hashTable.add(new Person("1", "李雅秋",1));
        hashTable.add(new Person("1", "李雅秋",1));
        hashTable.showList();
    }
}



class  Person{
    private String id;
    private String name;

    public Person(String id, String name, int hashValue) {
        this.id = id;
        this.name = name;
        this.hashValue = hashValue;
    }

    private int hashValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHashValue() {
        return hashValue;
    }

    public void setHashValue(int hashValue) {
        this.hashValue = hashValue;
    }



//
//    @Override
//    public int hashCode() {
//        System.out.println("hashcode:"+hashValue);
//        return hashValue;
//    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hashValue=" + hashValue +
                '}';
    }
}
