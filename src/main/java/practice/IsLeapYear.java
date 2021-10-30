package practice;

public class IsLeapYear {

    public boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            System.out.println("Year " + year + " is not a leap year");
            return false;
        } else {

            // if a year can be divided for 100 and 400 without reminder, so it's a leap year
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    System.out.println("Year " + year + " is a leap year");
                    return true;
                } else {
                    System.out.println("Year " + year + " is not a leap year");
                    return false;
                }
                // if a year can not be devided for 100 without reminder, so it's a leap year
            } else {
                System.out.println("Year " + year + " is a leap year");
                return true;
            }
        }
    }
}
