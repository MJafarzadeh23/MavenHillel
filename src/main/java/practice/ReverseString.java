package practice;

public class ReverseString {

    public String reverseString(String str) {
        char[] strToArray = str.toCharArray();

        for (int left = 0, right = strToArray.length - 1; left < right; left++, right--) {
            char loopArray = strToArray[left];
            strToArray[left]  = strToArray[right];
            strToArray[right] = loopArray;
        }
        String reverseStr = String. valueOf(strToArray);
        // reverse array output
        /*for (int i = 0; i < strToArray.length; i++) {
            System.out.print(strToArray[i] + " ");
        }*/
        return reverseStr;
    }
}
