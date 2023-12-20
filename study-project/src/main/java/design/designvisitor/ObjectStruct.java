package design.designvisitor;

import java.util.ArrayList;
import java.util.List;

public class ObjectStruct {
    List<Person> list = new ArrayList();

    {
        list.add(new WomanPerson("女人衣衣1"));
//        list.add(new WomanPerson("女人衣衣2"));
//        list.add(new WomanPerson("女人衣衣3"));
//        list.add(new ManPerson("男人杰杰1"));
//        list.add(new ManPerson("男人杰杰2"));
    }

    public void print(Action action){
        for (Person person : list) {
            person.accept(action);
        }
    }
}
