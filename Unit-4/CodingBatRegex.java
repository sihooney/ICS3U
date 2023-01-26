import java.util.regex.*;

public class CodingBatRegex {
    public static void main(String[] args) {

    }

    // https://codingbat.com/prob/p123614
    public static int countCode(String str) {
        Pattern p = Pattern.compile("co[a-zA-Z]e");
        Matcher m = p.matcher(str);
        int count = 0;
        while (m.find()) {
            ++count;
        }
        return count;
    }

    // https://codingbat.com/prob/p136594
    public static boolean xyzThere(String str) {
        return Pattern.compile("[^.]xyz").matcher(str).find();
    }

    // https://codingbat.com/prob/p175762
    public static boolean bobThere(String str) {
        return Pattern.compile("b.b").matcher(str).find();
    }

    // https://codingbat.com/prob/p136417
    public static boolean prefixAgain(String str, int n) {
        return Pattern.compile(str.substring(0, n)).matcher(str.substring(n)).find();
    }

    // https://codingbat.com/prob/p159772
    public static boolean xyzMiddle(String str) {
        Pattern p = Pattern.compile("xyz");
        Matcher m = p.matcher(str);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            if (Math.abs(start - (str.length() - end)) <= 1) {
                return true;
            }
        }
        return false;
    }

    // https://codingbat.com/prob/p129952
    public static String getSandwich(String str) {
        Pattern p = Pattern.compile(".*bread.*bread.*");
        Matcher m = p.matcher(str);
        if (m.matches()) {
            int start = str.indexOf("bread") + 5;
            int end = str.lastIndexOf("bread");
            return str.substring(start, end);
        }
        return "";
    }

    // https://codingbat.com/prob/p194491
    public static boolean sameStarChar(String str) {
        Pattern p = Pattern.compile(".\\*.");
        Matcher m = p.matcher(str);
        while (m.find()) {
            if (str.charAt(m.start()) != str.charAt(m.end() - 1)) {
                return false;
            }
        }
        return true;
    }

    // https://codingbat.com/prob/p180759
    public static String zipZap(String str) {
        return str.replaceAll("z.p", "zp");
    }

    // https://codingbat.com/prob/p139564
    public static String starOut(String str) {
        String pass1 = str.replaceAll("[^*]\\*", "*");
        String pass2 = pass1.replaceAll("\\*[^*]", "*");
        return pass2.replaceAll("\\*", "");
    }

    // https://codingbat.com/prob/p147538
    public static String wordEnds(String str, String word) {
        Pattern p = Pattern.compile(word);
        Matcher m = p.matcher(str);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            if (m.start() > 0) sb.append(str.charAt(m.start() - 1));
            if (m.end() < str.length()) sb.append(str.charAt(m.end()));
        }
        return sb.toString();
    }
}
