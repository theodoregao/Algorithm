package problems.ch1.section3;

import java.io.File;

public class _43_ListingFiles {
    
    static void listingFiles(File path) {
        listingFiles(path, "");
    }
    
    static void listingFiles(File path, String prefix) {
        if (!path.isDirectory()) return;
        for (String fileName: path.list()) {
            System.out.println(prefix + fileName);
            File file = new File(path, fileName);
            if (file.isDirectory()) {
                listingFiles(file, prefix + "  ");
            }
        }
    }
    
    public static void main(String[] args) {
        File path = new File("src");
        listingFiles(path);
    }

}
