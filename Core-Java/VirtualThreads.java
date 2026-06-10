import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreads {
    
    static void demonstrateVirtualThreads() {
        System.out.println("Virtual Threads (Java 21+)");
        int count = 50;
        CountDownLatch latch = new CountDownLatch(count);
        long start = System.currentTimeMillis();
        
        for (int i = 1; i <= count; i++) {
            final int num = i;
            try {
                Class<?> threadClass = Class.forName("java.lang.Thread");
                java.lang.reflect.Method method = threadClass.getMethod("startVirtualThread", Runnable.class);
                method.invoke(null, (Runnable) () -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {}
                    latch.countDown();
                });
            } catch (Exception e) {
                demonstrateWithPlatformThreads();
                return;
            }
        }
        
        try {
            latch.await();
        } catch (InterruptedException e) {}
        
        System.out.println("Completed in " + (System.currentTimeMillis() - start) + "ms");
    }
    
    static void demonstrateWithPlatformThreads() {
        System.out.println("Platform Threads (Java 8+)");
        int count = 50;
        CountDownLatch latch = new CountDownLatch(count);
        long start = System.currentTimeMillis();
        
        for (int i = 1; i <= count; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}
                latch.countDown();
            }).start();
        }
        
        try {
            latch.await();
        } catch (InterruptedException e) {}
        
        System.out.println("Completed in " + (System.currentTimeMillis() - start) + "ms");
    }
    
    static void demonstrateExecutorService() {
        System.out.println("\nExecutorService (Java 8+)");
        ExecutorService executor = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();
        
        for (int i = 1; i <= 50; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}
            });
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(1, java.util.concurrent.TimeUnit.MINUTES);
        } catch (InterruptedException e) {}
        
        System.out.println("Completed in " + (System.currentTimeMillis() - start) + "ms");
    }
    
    public static void main(String[] args) {
        demonstrateVirtualThreads();
        demonstrateExecutorService();
    }
}
