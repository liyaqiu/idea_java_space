package test1;

import org.apache.tools.ant.taskdefs.condition.IsFalse;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author lyq
 * @date 2021/7/6 5:20
 */
public class Test1 {
    @Test
    public void test0(){
        boolean bl = true;
        assert bl == true;
    }
    @Test
    public void test01(){
        boolean bl = false;
        Assert.assertEquals(bl, true);
    }
}
