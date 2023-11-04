import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SharedScanner {
    private static Scanner scanner;

    private SharedScanner() {
    }

    public static Scanner getInstance() {
        if (scanner == null) throw new NullPointerException();
        return scanner;
    }

    public static void setStreamInstance(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        scanner = new Scanner(fileInputStream);
    }

    public static void setInteractiveInstance() {
        scanner = new Scanner(System.in);
    }
}
