package ly.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyThread {
    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(3);
        service.submit(new Runnable() {
            public void run() {
                while (true){
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("---------");
                }
            }
        });
        service.submit(new Runnable() {
            public void run() {
                while (true){
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.err.println("---------");
                }
            }
        });
        service.shutdown();
    }
}
