package sort;

import java.util.Random;

public class SortUtil {
    
    public static int[] randomInts(int size) {
        int[] items = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) items[i] = random.nextInt();
        return items;
    }
    
    /**
     * @param length if length less than 0, the length will be random value between (0, -length).
     */
    public static String[] randomStrings(int size, int length) {
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) strings[i] = randomString(length);
        return strings;
    }
    
    private static String randomString(int length) {
        Random random = new Random();
        length = length > 0 ? length : (1 + random.nextInt(-length));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) sb.append((char)('a' + random.nextInt(26)));
        return sb.toString();
    }
    
    public static void swap(int[] items, int i, int j) {
        int temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
    
    public static void swap(String[] strings, int i, int j) {
        String temp = strings[i];
        strings[i] = strings[j];
        strings[j] = temp;
    }
    
    public static boolean isSorted(int[] items) {
        for (int i = 1; i < items.length; i++)
            if (items[i - 1] > items[i]) return false;
        return true;
    }
    
    public static boolean isSorted(String[] strings) {
        for (int i = 1; i < strings.length; i++)
            if (strings[i - 1].compareTo(strings[i]) > 0) {
                System.out.println(strings[i - 1] + " : " + strings[i]);
                return false;
            }
        return true;
    }

}
