package lab1.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Counter {

    public static Map<String, Integer> countWords(File[] files) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (File file : files){
            try(Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    for (String word : scanner.nextLine().split(" ")) {
                        resultMap.put(word, resultMap.getOrDefault(word, 0) + 1);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }

}
