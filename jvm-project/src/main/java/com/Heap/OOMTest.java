package com.Heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eric
 * @date 2022/5/17 18:38
 * -Xms300m -Xmx300m -XX:+PrintGCDetails
 *
 * 都没写则为堆区 52967K->17272K(294400K)
 *  堆区         回收前   回收后 总大小
 * PSYoungGen: 52967K->1240K(89600K)
 *  新生代区     回收前   回收后 总大小
 * ParOldGen: 151239K->81797K(204800K)
 *  老年代区     回收前   回收后 总大小
 * Metaspace: 3492K->3492K(1056768K)
 *  元空间     回收前   回收后 总大小
 * 0.4266590 secs GC
 *  垃圾回收时间
 * [GC (Allocation Failure)      [PSYoungGen: 52967K->1240K(89600K)] 52967K->17272K(294400K), 0.0488906 secs] [Times: user=1.17 sys=0.00, real=0.05 secs]
 * [GC (Allocation Failure)      [PSYoungGen: 62801K->1080K(89600K)] 214040K->152319K(294400K), 0.0262948 secs] [Times: user=0.30 sys=0.02, real=0.03 secs]
 * [GC (Allocation Failure)      [PSYoungGen: 1080K->1048K(89600K)] 152319K->152287K(294400K), 0.0300708 secs] [Times: user=0.31 sys=0.02, real=0.03 secs]
 * [Full GC (Allocation Failure) [PSYoungGen: 1048K->0K(89600K)] [ParOldGen: 151239K->81797K(204800K)] 152287K->81797K(294400K), [Metaspace: 3492K->3492K(1056768K)], 0.2752555 secs] [Times: user=1.31 sys=0.00, real=0.28 secs]
 * [Full GC         (Ergonomics) [PSYoungGen: 1536K->0K(89600K)] [ParOldGen: 203483K->122359K(204800K)] 205019K->122359K(294400K), [Metaspace: 3492K->3492K(1056768K)], 0.4165691 secs] [Times: user=2.98 sys=0.08, real=0.42 secs]
 * [GC (Allocation Failure)      [PSYoungGen: 0K->0K(89600K)] 122359K->122359K(294400K), 0.0007324 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(89600K)] [ParOldGen: 122359K->122340K(204800K)] 122359K->122340K(294400K), [Metaspace: 3492K->3492K(1056768K)], 0.4266590 secs] [Times: user=3.27 sys=0.05, real=0.43 secs]
 **/
public class OOMTest {
    public static void main(String[] args) {
        List<String> list  =new ArrayList<>();
        while (true){
            list.add("1111");
        }
    }

}
