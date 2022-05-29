package lab1.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OptimizedCounter {

    public static Map<String, Integer> countWords(File file) {
        Map<String, Integer> resultMap = new HashMap<>();
        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()){
                for (String word : scanner.nextLine().split(" ")) {
                    resultMap.put(word, resultMap.getOrDefault(word, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    public static Map<String, Integer> uniteMaps(Map<String, Integer> firstMap, Map<String, Integer> secondMap){
        Map<String, Integer> mergedMap = new HashMap<>(secondMap);
        firstMap.forEach((k, v) -> mergedMap.merge(k, v, Integer::sum));
        return mergedMap;
    }

}
