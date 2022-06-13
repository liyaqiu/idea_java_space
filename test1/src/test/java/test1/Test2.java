package test1;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lyq
 * @date 2021/7/6 5:20
 */
public class Test2 {
    @Test
    public void test0(){
        boolean bl = true;
        assert bl == true;
    }
    @Test
    public void test01(){
        boolean bl = true;
        Assert.assertEquals(bl, true);
    }
    @Test
    public void test02(){
        boolean bl = true;
        Assert.assertEquals(bl, true);
        System.out.println(bl);
    }


}
