package lab1.task3;

import lab1.common.OptimizedCounter;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static lab1.common.Reader.getFiles;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long time = System.currentTimeMillis();
        File[] array = getFiles(args[0]);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Map<String, Integer> resultMap = executor.
                submit(() -> Arrays.stream(array).parallel().map(OptimizedCounter::countWords)).get()
                .reduce(OptimizedCounter::uniteMaps)
                .orElseThrow(RuntimeException::new);
        executor.shutdown();
        System.out.println(resultMap);
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - time) + " ms");
    }

}
