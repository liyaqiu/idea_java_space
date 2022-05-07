package thread.newtest;

import java.io.*;
import java.util.ArrayList;

class DD implements Serializable{

}

public class Test1111 extends DD {


    public static final long serialVersionUID = -1679098422945941590L;

    public String string = new String("李雅秋");
    public Haha haha = new Haha();

    protected Test1111 deepClone() throws IOException, ClassNotFoundException {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
        Test1111 test1111 = (Test1111) ois.readObject();
        return test1111;
    }

}


