import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceAndCallable {
    
    static class Task implements Callable<Integer> {
        int num;
        
        Task(int num) {
            this.num = num;
        }
        
        @Override
        public Integer call() throws Exception {
            Thread.sleep(500);
            int fact = 1;
            for (int i = 2; i <= num; i++)
                fact *= i;
            System.out.println(num + "! = " + fact);
            return fact;
        }
    }
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Integer>> futures = new ArrayList<>();
        
        System.out.println("Submitting tasks...");
        for (int i = 1; i <= 5; i++)
            futures.add(executor.submit(new Task(i)));
        
        System.out.println("Collecting results...\n");
        
        int sum = 0;
        for (int i = 0; i < futures.size(); i++) {
            try {
                Integer result = futures.get(i).get();
                sum += result;
                System.out.println("Task " + (i + 1) + " result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        System.out.println("\nTotal: " + sum);
        executor.shutdown();
    }
}
