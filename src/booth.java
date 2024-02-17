

import java.util.Scanner;

public class booth {
    public static Scanner s = new Scanner(System.in);
    public int booth(int n1b,int n2b,int b) {
        int[] num1 = decimaltobinary(n1b,b);
        int[] negnum1= decimaltobinary((-(n1b)),b);
        int[] num12= decimaltobinary((n1b*2),b);
        int[] negnum12= decimaltobinary((-(n1b*2)),b);
        int[] num2 = decimaltobinary(n2b,b); 
        int[] n1 = new int[(2*b)+2];
        int[] negn1 = new int[(2*b)+2];
        int[] n12 = new int[(2*b)+3]; 
        int[] negn12 = new int[(2*b)+3]; 
        int[] n2 = new int[(2*b)+2]; 
        for (int i = 0; i < b; i++)
        {
            n1[i+1] = num1[i];
            negn1[i+1] = negnum1[i];
            n12 [i+1] = num12 [i];
            negn12 [i+1] = negnum12 [i];
            n2[i + (b+1)] = num2[i];
            if(num1[0]==1) n1[0]=1;
            
        }
        chap(n1,"(Num1)A",b);
        chap(n2,"(Num2)B",b);
        System.out.println();
        
        for (int i=0;i<b-2;i++){
            if ((n2[(2*b)+1]==0 && n2[(2*b)]==0 && n2[(2*b)-1]==0)||(n2[(2*b)+1]==1 && n2[(2*b)]==1 && n2[(2*b)-1]==1));
               //nop
            else if ((n2[(2*b)+1]==0 && n2[(2*b)]==1 && n2[(2*b)-1]==0) || (n2[(2*b)+1]==1 && n2[(2*b)]==0 && n2[(2*b)-1]==0)){
               //add a
                add(n2,n1,b);
                chap(n2,"(ADD)+A",b);
            }
            else if (n2[(2*b)+1]==1 && n2[(2*b)]==1 && n2[(2*b)-1]==0){
               //add 2a   
                add(n2,n12,b);
                chap(n2,"(ADD)2A",b);
            }
            else if (n2[(2*b)+1]==0 && n2[(2*b)]==0 && n2[(2*b)-1]==1){
               //add -2a
                add(n2,negn12,b);
                chap(n2,"(ADD)-2A",b);
            }
            else if ((n2[(2*b)+1]==0 && n2[(2*b)]==1 && n2[(2*b)-1]==1)|| (n2[(2*b)+1]==1 && n2[(2*b)]==0 && n2[(2*b)-1]==1)){
               //add -a
                add(n2,negn1,b);
                chap(n2,"(ADD)-A",b);
            }
            
            Rshift(n2,b);
            chap(n2,"(ASHR)B",b);
        }
    return binarytodecimal(n2,b);      
}
    
    public int[] decimaltobinary(int ndb,int b)
    {
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
    public int binarytodecimal(int[] bintodec,int b)
    {
        int dec = 0;
        int t = 1;
        for (int i = 2*b ; i >= 0; i--, t *= 2){
            dec += (bintodec[i] * t);
        }
        return dec;        
    }
    
    
    public void Rshift(int[] R,int b)
    {     
        if(R[0]==0){
        for (int i = ((2*b)+1); i >= 2; i--)
            R[i] = R[i - 2]; 
            R[0] = 0;
            R[1] = 0;
    }
        else if(R[0]==1){
        for (int i = ((2*b)+1); i >= 2; i--)
            R[i] = R[i - 2]; 
            R[0] = 1;
            R[1] = 1;
    } 
    }
    
    public void add(int[] n1, int[] n2,int b)
    {
        int c = 0;
        for (int i = (2*b); i >= 0; i--)
        {
            int res = n1[i] + n2[i] + c;
            n1[i] = res % 2;
            c = res / 2;
        }        
    }
    public void chap(int[] n, String ch,int b)
    { 
        System.out.print("\n"+ ch +"  : ");
        for (int i = 0; i < n.length; i++)
        {
            if (i == 1||i == b+1||i == (2*b)+1)
                System.out.print(" ");
            
            System.out.print(n[i]);
        } 
    }
}
