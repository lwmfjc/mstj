package ly.thread;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyThread {
    AtomicInteger i;
    AbstractQueuedSynchronizer synchronizer;
    public static void main(String[] args) {
        HashMap hashMap;
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
