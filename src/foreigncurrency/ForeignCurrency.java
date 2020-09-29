
package foreigncurrency;

import java.text.NumberFormat;
import java.util.Scanner;

/**
 *
 * @author jacob hinkebein
 */
public class ForeignCurrency {

    static Scanner sc = new Scanner(System.in);
    static double rEUR, rGBP, rJPY, rCAD, rRUB;
    public static void main(String[] args) {
        System.out.println("Welcome to the currency calculator!");
        
        getRates();
        doValuation();
        
        System.out.println("Thanks for using the currency calculator!");
    }
    public static void getRates(){
        System.out.println("Please enter the currency rate Per US $ of the following currencies:");
        rEUR = getOneRate("EUR: ");
        rGBP = getOneRate("GBP: ");
        rJPY = getOneRate("JPY: ");
        rCAD = getOneRate("CAD: ");
        rRUB = getOneRate("RUB: ");
    }
    public static double getOneRate(String currency) {
        double r = 0;
        do {
            try {
                System.out.print(currency);
                r = sc.nextDouble();
                if (r <= 0) {
                    System.out.println("Illegal value: negatives not allowed.");
                }
            } catch (Exception e) {
                System.out.println("Error: non-negative real numbers only.");
                sc.nextLine();
                r = -1;
            }
        } while (r < 0);
        return r;
    }
    
    public static void doValuation() {
        int choice, quantity;
        double cVal, totalCVal = 0;
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        
        int[] units = new int[5];
        for (int i = 0; i < 5; i++) {
            units[i] = 0;
        }
        double[] ctot = {0, 0, 0, 0, 0};
        
        choice = getChoice();
        
        while (choice != 0 ) {
            cVal = 0;
            quantity = 0;
            switch (choice) {
                case 1: 
                    quantity = getQty("Euros");
                    cVal = quantity * rEUR;
                    System.out.println(quantity + " Euros has a value of: " + curr.format(cVal));
                    units[0] += quantity;
                    ctot[0] += cVal;
                    break;
                case 2:
                    quantity = getQty("Pounds Sterling");
                    cVal = quantity * rGBP;
                    System.out.println(quantity + " Pounds Sterling has a value of: " + curr.format(cVal)); 
                    break;
                case 3:
                    quantity = getQty("Yen");
                    cVal = quantity * rJPY;
                    System.out.println(quantity + " Yen has a value of: " + curr.format(cVal));
                    break;
                case 4:
                    quantity = getQty("Canadian Dollars");
                    cVal = quantity * rCAD;
                    System.out.println(quantity + " Canadian Dollars has a value of: " + curr.format(cVal));
                    break;
                case 5:
                    quantity = getQty("Russian Rubles");
                    cVal = quantity * rRUB;
                    System.out.println(quantity + " Russian Rubles has a value of: " + curr.format(cVal));
                    break;
                case 9:
                    getRates(); 
                    break;
                default:
                    System.out.println("Error: choice not recognized.");
                    break;
            }
            if (choice != 9) {
            units[choice-1] += quantity;
            ctot[choice-1] += cVal;
            }
            totalCVal += cVal;
            choice = getChoice();
        }
        String[] cnm = {"EUR", "GBP", "JPY", "CAD", "RUB"};
        for (int i = 0; i < 5; i++) {
            System.out.println(cnm[i] +": " + units[i] + " units costing: " + curr.format(ctot[i]));
        }
        System.out.println("Thus the total value of the proposed currency purchases was: " + curr.format(totalCVal));
    }

    public static int getChoice() {
        int c = 0;
        do {
            try {
                System.out.print("Select currency for valuation (1=EUR, 2=GBP, 3=JPY, 4=CAD, 5=RUB, 9=New Rates, 0=Quit): ");
                c = sc.nextInt();
                if (c < 0 || (c > 5 && c != 9)) {
                    System.out.println("Choice is out of bounds: 0-5 or 9 only.");
                }
            } catch (Exception e) {
                System.out.println("Illegal input: not an integer.");
                sc.nextLine();
                c = -1;
            }
        } while (c < 0 || (c > 5 && c != 9));
        return c;
    }
    public static int getQty(String ccode) {
        int q;
        
        do {
            try {
                System.out.print("How many " + ccode + " are you buying? ");
                q = sc.nextInt();
                if (q < 0) {
                    System.out.println("Choice is out of bounds: non-negative integers only.");
                }
            } catch (Exception e) {
                System.out.println("Illegal input: not an integer.");
                sc.nextLine();
                q = -1;
            }
        } while (q < 0);
        return q;
    }
}
