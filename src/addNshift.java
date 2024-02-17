

import java.util.Scanner;

public class addNshift {

    public static Scanner s = new Scanner(System.in);
    // SIGNED -----------------------------------------------
    public int addnshiftS(int n1a, int n2a, int b) {
        int[] num1 = decimaltobinary(n1a, b);
        int[] negnum1 = decimaltobinary(-n1a, b);
        int[] num2 = decimaltobinary(n2a, b);
        int[] n1 = new int[(2 * b) + 1];
        int[] negn1 = new int[(2 * b) + 1];
        int[] n2 = new int[(2 * b) + 1];
        int[] Copyn2 = new int[(2 * b) + 1];
        for (int i = 0; i < b; i++) {
            n1[i + 1] = num1[i];
            negn1[i + 1] = negnum1[i];
            n2[i + b + 1] = num2[i];
            Copyn2[i + b + 1] = num2[i];
        }

        chap(n1, "(Num1)A", b);
        chap(n2, "(Num2)B", b);
        System.out.println();

        for (int i = 0; i < (b - 1); i++) {
            if (Copyn2[(2 * b) - i] == 1) {
                add(n2, n1, b);
                chap(n2, "(ADD)+A", b);
            }
            if (Copyn2[(2 * b) - i] == 0);

            RshiftS(n2, b);
            chap(n2, "(ASHR)B", b);
        }
        if (Copyn2[b + 1] == 1) {
            add(n2, negn1, b);
            chap(n2, "(ADD)-A", b);
            RshiftS(n2, b);
            chap(n2, "(ASHR)B", b);
        } else if (Copyn2[b + 1] == 0) {
            add(n2, n1, b);
            chap(n2, "(ADD)+A", b);
            RshiftS(n2, b);
            chap(n2, "(ASHR)B", b);
        }
        return binarytodecimal(n2, b);
    }
    // UNSIGNED ---------------------------------------------
    public int addnshiftUS(int n1a, int n2a, int b) {
        int[] num1 = decimaltobinary(n1a, b);
        int[] negnum1 = decimaltobinary(-n1a, b);
        int[] num2 = decimaltobinary(n2a, b);
        int[] n1 = new int[(2 * b) + 1];
        int[] negn1 = new int[(2 * b) + 1];
        int[] n2 = new int[(2 * b) + 1];
        int[] Copyn2 = new int[(2 * b) + 1];
        int[] zero = new int[(2 * b) + 1];
        for (int i = 0; i < b; i++) {
            n1[i + 1] = num1[i];
            negn1[i + 1] = negnum1[i];
            n2[i + b + 1] = num2[i];
            Copyn2[i + b + 1] = num2[i];
        }
        chap(n1, "(Num1)A", b);
        chap(n2, "(Num2)B", b);
        System.out.println();

        for (int i = 0; i < b; i++) {
            if (Copyn2[(2 * b) - i] == 1) {
                add(n2, n1, b);
                chap(n2, "(ADD)+A", b);
                RshiftUS(n2, b);
                chap(n2, "(ASHR)B", b);
            }
            if (Copyn2[(2 * b) - i] == 0) {
                n2[0] = 0;
                RshiftUS(n2, b);
                chap(n2, "(ASHR)B", b);
            }
        }
        return binarytodecimal(n2, b);
    }
    // Decimal to Binary------------------------------------------------------   
    public int[] decimaltobinary(int ndb, int b) {
        int[] bin = new int[b];
        int ctr = b-1;
        int num = ndb;
        if (ndb < 0) {
            for (int i = 0; i < b; i++) {
                bin[i] = 1;
            }
            while (num < 0) num = 16+ num;
            while (ctr != 0) {
                bin[ctr--] = num % 2;
                num /= 2;
            }
        }
        else{
        while (num != 0) {
            bin[ctr--] = num % 2;
            num /= 2;
        }
        }
        return bin;
    }
    // Binary to Decimal---------------------------------------------------------    
    public int binarytodecimal(int[] bintodec, int b) {
        int dec = 0;
        int t = 1;
        for (int i = (2 * b); i >= 0; i--, t *= 2) {
            dec += (bintodec[i] * t);
        }
        return dec;
    }

    // Right Shift---------------------------------------------------------    
    public void RshiftS(int[] R, int b) {
        if (R[1] == 1) {
            for (int i = 2 * b; i > 1; i--) {
                R[i] = R[i - 1];
            }
                R[1] = 1;
                R[0] = 0;
        } else if (R[1] == 0) {
            for (int i = 2 * b; i > 1; i--) {
                R[i] = R[i - 1];
            }
                R[1] = 0;
                R[0] = 0;   
        }
    }
    // Right Shift Unsigned---------------------------------------------------------    
    public void RshiftUS(int[] R, int b) {
        if (R[0] == 0) {
            for (int i = 2 * b; i >= 1; i--) {
                R[i] = R[i - 1];
                R[0] = 0;
            }
        } else if (R[0] == 1) {
            for (int i = 2 * b; i >= 1; i--) {
                R[i] = R[i - 1];
                R[0] = 1;
            }
        }
    }
    // Add--------------------------------------------------------    
    public int add(int[] n1, int[] n2, int b) {
        int c = 0, carry = 0;
        for (int i = (2 * b); i >= 0; i--) {
            int res = n1[i] + n2[i] + c;
            n1[i] = res % 2;
            c = res / 2;
            carry = n1[0];
        }
        return carry;
    }
    // Print--------------------------------------------------------    
    public void chap(int[] n, String ch, int b) {
        System.out.print("\n" + ch + " : ");
        for (int i = 0; i < n.length; i++) {
            if (i == b + 1 || i == 1) {
                System.out.print(" ");
            }

            System.out.print(n[i]);
        }
    } 
}
