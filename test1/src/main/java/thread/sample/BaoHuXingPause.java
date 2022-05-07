package thread.sample;

import java.util.Random;

/**
 * 保护性暂停
 * */
public class BaoHuXingPause {
    public static void main(String[] args) {
        Message message = new Task().exec(new Runnable() {
            @Override
            public void run() {
                System.out.println("你去帮我打包一份快递");
            }
        });

        System.out.println("取到的消息--->"+message.getMsg());
        System.out.println("end");
    }

}

class Task{
    Message message = new Message();
    public Message exec(Runnable runnable){
        new Thread("product"){
            @Override
            public void run() {
                runnable.run();
                message.setMsg("我已经按照你的吩咐执行完成.......");
            }
        }.start();
        return message;
    }
}


class Message{
    private volatile String msg;

    public synchronized String getMsg() {
        //防止虚假唤醒
        while (msg==null){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String result = msg;
        msg=null;
        return result;
    }

    public synchronized void setMsg(String msg) {
        try {
            Thread.sleep(3000);
            System.out.println("加工。。。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.msg = msg;
        this.notify();
    }
}


