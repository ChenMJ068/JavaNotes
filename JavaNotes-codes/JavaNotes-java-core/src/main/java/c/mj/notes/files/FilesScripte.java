package c.mj.notes.files;

import java.io.File;
import java.io.IOException;

/**
 * @author ChenMJ
 * @version FilesScripte.class, v 0.1 2020/11/12 10:21  Exp$
 */
public class FilesScripte {
    public static void main(String[] args) throws IOException {
        createNewFile("tEcdCdinf");
        String tmp = "Inse)";
    }

    private static void createNewFile(String name) throws IOException {
        File file = new File("d:" + File.separator + name + ".sql");
        file.createNewFile();
    }
}
