package design.designiterator;

import java.util.Iterator;
import java.util.List;

public class PersonalIterator implements Iterator<PersonalBean> {
    private  List<PersonalBean> list ;
    private int index = -1;

    public PersonalIterator(List<PersonalBean> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        if(index < list.size()-1){
            index++;
            return true;
        }
        return false;
    }

    @Override
    public PersonalBean next() {
        return list.get(index);
    }
}
