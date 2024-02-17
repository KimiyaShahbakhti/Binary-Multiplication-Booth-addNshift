

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Memari {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(new File("in.txt"));
        ArrayList<Integer> list = new ArrayList<>();
        while (s.hasNextInt()) {
            list.add(s.nextInt());
        }
        System.out.println(list);

        // Booth or AddNShift
        int m = list.get(0);
        // b bit (e.g 4 bit)
        int b = list.get(1);
        //signed or unsigned
        int sign = list.get(2);
        int n1 = list.get(3);
        int n2 = list.get(4);

        try {
            PrintStream outputfile = System.out;
            PrintStream p = new PrintStream("out.txt");
            System.setOut(p);
            if (m == 1) {
                outputfile.println("Booth multiplication");
                booth booth = new booth();
                int result2 = booth.booth(n1, n2, b);
                System.out.println("\n\nResult : " + n1 + " * " + n2 + " = " + result2);
                outputfile.println(result2);
            } else if (m == 0) {
                addNshift addnshift = new addNshift();
                if (sign == 1) {
                    outputfile.println("Signed add & shift multiplication");
                    int result1 = addnshift.addnshiftS(n1, n2, b);
                    outputfile.println(result1);
                    System.out.println("\n\nResult : " + n1 + " * " + n2 + " = " + result1);
                } else if (sign == 0) {
                    outputfile.println("Unsigned add & shift multiplication");
                    int result1 = addnshift.addnshiftUS(n1, n2, b);
                    System.out.println("\n\nResult : " + n1 + " * " + n2 + " = " + result1);
                    outputfile.println(result1);
                }
            }
            System.setOut(outputfile);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
