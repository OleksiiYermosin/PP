package lab1.task2;

import lab1.common.OptimizedCounter;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import static lab1.common.Reader.getFiles;

public class Main {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        File[] array = getFiles(args[0]);
        Map<String, Integer> fileWordCounters = Arrays.stream(array)
                .map(OptimizedCounter::countWords)
                .reduce(OptimizedCounter::uniteMaps)
                .orElseThrow(RuntimeException::new);
        System.out.println(fileWordCounters);
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - time) + " ms");
    }

}
