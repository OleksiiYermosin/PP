package lab2;

import java.time.LocalTime;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CustomExecutor executor = new CustomExecutor(2);
        List<FutureResult<?>> results = executor.map((v) -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return v * 2;
        }, new Integer[]{1, 2, 3, 4});
        for (FutureResult<?> result : results) {
            System.out.println(result.getResult() + " " + LocalTime.now());
        }
        executor.shutdown();
    }

}
