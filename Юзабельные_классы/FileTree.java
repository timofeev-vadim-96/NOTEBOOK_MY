
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileTree {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(".");
        drawFileTree(path, "", true); //флаг true для печати в текущей директории
    }

    static void drawFileTree(Path path, String indent, boolean isLast) throws IOException {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└");
            indent += "   ";
        } else {
            System.out.print("├");
            indent += "│  ";
        }
        System.out.println(path.getFileName());

        if (Files.isDirectory(path)) {
            try (Stream<Path> dirFiles = Files.list(path)) {
                Path[] files = dirFiles.toArray(Path[]::new);
                if (files.length == 0) return;
                int subDirTotal = 0;
                int subFile = 0;
                for (Path file : files) {
                    if (Files.isDirectory(file)) subDirTotal++;
                    else subFile++;
                }

                int currentSubDir = 0;
                int currentSubFile = 0;
                for (Path file : files) {
                    if (Files.isDirectory(file)) {
                        drawFileTree(file, indent, subDirTotal == ++currentSubDir);
                    } else drawFileTree(file, indent, subFile == ++currentSubFile);
                }
            }
        }
    }
}
