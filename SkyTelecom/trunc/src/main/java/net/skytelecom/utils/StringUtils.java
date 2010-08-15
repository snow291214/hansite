package net.skytelecom.utils;

/**
 *
 * @author Khudyakov Alexey
 * ICQ: 164777039
 * Current date: 24.07.2010
 */
public class StringUtils {

    public static String arrayToString2(String[] a, String separator) {
        StringBuilder result = new StringBuilder();
        if (a.length > 0) {
            result.append(a[0]);
            for (int i = 1; i < a.length; i++) {
                result.append(separator);
                result.append(a[i]);
            }
        }
        return result.toString();
    }
}
