package lab1.task1;

import static lab1.common.Counter.countWords;
import static lab1.common.Reader.getFiles;

public class Main {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        System.out.println(countWords(getFiles(args[0])));
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - time) + " ms");
    }

}
