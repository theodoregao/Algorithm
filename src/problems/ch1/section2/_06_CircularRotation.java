package problems.ch1.section2;

/**
 * 1.2.6 A string s is a circular rotation of a string t if it matches when the characters
are circularly shifted by any number of positions; e.g., ACTGACG is a circular shift of
TGACGAC, and vice versa. Detecting this condition is important in the study of genomic
sequences. Write a program that checks whether two given strings s and t are circular
shifts of one another. Hint : The solution is a one-liner with indexOf(), length(), and
string concatenation.
 * @author Theodore
 *
 */
public class _06_CircularRotation {

    private static boolean isCircularRotation(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        for (int i = 0; i < str1.length(); i++) {
            if (str2.indexOf(str1.substring(i)) == 0
                    && str1.indexOf(str2.substring(str1.length() - i)) == 0)
                return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        
        String str1 = "szyyrhfowi;lkfjwaiutrfguiwefiskudfjie";
        String str2 = "lkfjwaiutrfguiwefiskudfjieszyyrhfowi;";
        String str3 = "fowi;lkfjwaiutrfguiwefiskudfjieszyyrh";
        String str4 = "lkfjwaiutrfguiwefiskudfjieszyyrhfowi;l";

        System.out.println(isCircularRotation(str1, str2));
        System.out.println(isCircularRotation(str1, str3));
        System.out.println(isCircularRotation(str1, str4));
        System.out.println(isCircularRotation(str2, str3));
        System.out.println(isCircularRotation(str2, str4));
        System.out.println(isCircularRotation(str3, str4));
        
    }

}
