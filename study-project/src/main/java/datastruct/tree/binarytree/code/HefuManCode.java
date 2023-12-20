package datastruct.tree.binarytree.code;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
/***
 * 赫夫曼编码是可变长编码。实现无损压缩
 * */
public class HefuManCode {
    private Map<Byte, String> codeMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
//        byte[] bytes = new HefuManCode().encodeAndDecode("我们我们我们我们我们".getBytes(StandardCharsets.UTF_8));
//        System.out.println(new String(bytes,"utf-8"));


        HefuManCode hefuManCode = new HefuManCode();
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\资料2\\数据结构与算法\\222.png"));
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        new HefuManCode().encodeAndDecode(bytes) ;
// FileOutputStream fos = new FileOutputStream(new File("C:\\\\Users\\\\admin\\\\Desktop\\\\资料2\\\\数据结构与算法\\\\aa.png"));
        //fos.write(decode.getBytes(StandardCharsets.UTF_8));

//
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("C:\\Users\\admin\\Desktop\\资料2\\数据结构与算法\\aa.zip")));
//        oos.writeObject(bytes);
       // oos.writeObject();
    }

    public byte[] encodeAndDecode(byte[] bytes) throws FileNotFoundException {
        System.out.println("编码前的字节长度:" + bytes.length);
        //构建赫夫曼树
        Node node = buildTree(bytes);
        //前缀打印赫夫曼树
        printInfix(node);
        //构建赫夫曼编码表
        buildCode(node, "", "");
        //打印赫夫曼编码表
        printCodeMap();
        //将字符串编码
        String encodeStr = encode(bytes);
        //System.out.println(encodeStr);
        //获得一个编码过的字节数组
        bytes = encodeToByte(encodeStr);
        System.out.println("编码后的字节数组:" + bytes.length);
        //System.out.println(Arrays.toString(bytes));
        //将字节数组解码成二进制补码字符串
        String decodeStr = decodeToString(bytes);
        //System.out.println(decodeStr);
        //解码二进制的字符串
        //System.out.println(decode(decodeStr));
        bytes = decode(decodeStr);
        return bytes;
    }

    public byte[] decode(String decodeStr) {
        List<Byte> list = new ArrayList<>();
        Map<String, Byte> decodeMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : codeMap.entrySet()) {
            decodeMap.put(entry.getValue(), entry.getKey());
        }
        int start = 0;
        int end = 1;
        while (start < decodeStr.length()) {
            Byte aByte = decodeMap.get(decodeStr.substring(start, end));
            if (aByte != null) {
                list.add(aByte);
                start = end;
            }
            end++;
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    public String decodeToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        //最后一个的长度
        int lastLength = bytes[bytes.length - 1];
        System.out.println(lastLength);
        for (int i = 0; i < bytes.length - 1; i++) {
            String binaryStr = Integer.toBinaryString(bytes[i] | 256);
            if (i == bytes.length - 2) {
                //最后这个补位需要知道长度为多少
                sb.append(binaryStr.substring(binaryStr.length() - lastLength));
                continue;
            }
            sb.append(binaryStr.substring(binaryStr.length() - 8));
        }
        return sb.toString();
    }


    public byte[] encodeToByte(String encodeStr) {
        int size = 0;
        if ((encodeStr.length() % 8 == 0)) {
            size = encodeStr.length() / 8;
        } else {
            size = encodeStr.length() / 8 + 1;
        }
        //最后一个byte用来标识最后一个字符有多少位
        byte[] b = new byte[size + 1];
        int counter = 0;

        for (int i = 0; i < encodeStr.length(); i += 8) {
            //最后一次需要记录尾部的二进制是n<=8位数
            if (counter == size - 1) {
                String lastStr = encodeStr.substring(i);
                b[size] = (byte) lastStr.length();
                System.out.println("最后一个二进制长度为:" + lastStr.length());
                b[counter] = (byte) Integer.parseInt(encodeStr.substring(i), 2);
                continue;
            }
            //这里是以反码的方式传入byte
            b[counter] = (byte) Integer.parseInt(encodeStr.substring(i, i + 8), 2);
            counter++;
        }
        return b;
    }

    public String encode(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(codeMap.get(bytes[i]));
        }
        return sb.toString();
    }


    public void printCodeMap() {
        for (Map.Entry<Byte, String> entry : codeMap.entrySet()) {
            System.out.println("打印--"+entry.getKey() + " " + entry.getValue());
        }
    }

    public void buildCode(Node node, String codeValue, String codeStr) {
        if (node == null) {
            return;
        }
        codeStr += codeValue;
        if (node.getCh() != null) {
            codeMap.put(node.getCh(), codeStr);
        }
        buildCode(node.getLeft(), "0", codeStr);
        buildCode(node.getRigth(), "1", codeStr);
    }

    public void printInfix(Node node) {
        if (node == null) {
            return;
        }
        if(node.getCh()!=null){
            System.out.println(node);
        }

        printInfix(node.getLeft());
        printInfix(node.getRigth());
    }

    public Node buildTree(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<>();
        for (int i = 0; i < bytes.length; i++) {
            Integer count = map.get(bytes[i]);
            if (count == null) {
                map.put(bytes[i], 1);
                continue;
            }
            map.put(bytes[i], ++count);
        }

        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getValue(), entry.getKey()));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes, (x, y) -> x.getValue() - y.getValue());
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node node = new Node(left.getValue() + right.getValue());
            node.setLeft(left);
            node.setRigth(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(node);
        }
        return nodes.get(0);
    }

}

class Node {
    private int value;
    private Byte ch;
    private Node left;
    private Node rigth;

    public Node(int value, Byte ch) {
        this.value = value;
        this.ch = ch;
    }


    public Byte getCh() {
        return ch;
    }

    public void setCh(Byte ch) {
        this.ch = ch;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRigth() {
        return rigth;
    }

    public void setRigth(Node rigth) {
        this.rigth = rigth;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", ch=" + ch +
                '}';
    }
}
