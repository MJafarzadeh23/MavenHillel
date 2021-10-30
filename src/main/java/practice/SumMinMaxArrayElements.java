package practice;

public class SumMinMaxArrayElements {

    // Method which returns the sum of max and min in the given array
    public int sumMinMaxElements(int[] array) {
        int max = array[0];
        int min = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int sumArray = min + max;
        return sumArray;
    }
}
