import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class leetCode60 {
    public  void main(String[] args) {
        Scanner scan  = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        System.out.println(getPermutation(n, k));
        
    }
    public String getPermutation(int n, int k) {
        if(n<=1)
        return n+"";
        List<Integer>list=new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            list.add(i+1);
        }
        return helper(n,k,list,"");
        
    }
    public String helper (int n,int k,List<Integer>list,String s)
    {
        if(list.size()==1)
        {
            s=s+list.get(0);
            return s;
        }
        int blocksize=fact(n-1);
        int index=k/blocksize;
        if(k%blocksize==0) //eg: if k=12,n=4 the first digit should be 2 and not 3
        {
            index--;
        }
        s=s+list.get(index);
        list.remove(index);
        k=k-(blocksize*index);
        return helper(n-1,k,list,s);
    }
    public int fact(int n)
    {
        int fac=1;
        for(int i=1;i<=n;i++)
        {
            fac*=i;
        }
        return fac;
    }
    
}