package lab1.common;

import java.io.File;

public class Reader {

    public static File[] getFiles(String path) {
        return (new File(path)).listFiles();
    }

}
