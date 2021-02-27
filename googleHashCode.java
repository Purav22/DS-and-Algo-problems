import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class graph{
    int src;
    int dest;
    String name;
    int w;

    graph(int src, int dest, String name, int w){
        this.src = src;
        this.dest = dest;
        this.name = name;
        this.w = w;
    }
}

class intersaction{
    int src;
    int time;
    int parent;

    intersaction(int src, int time, int parent){
        this.src = src;
        this.time = time;
        this.parent= parent;
    }
}

class ans{
    int src;
    int parent;
    int time;

    ans(int src, int parent,int time){
        this.src = src;
        this.parent = parent;
        this.time += time;
    }


}
public class googleHashCode {

    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str[] = br.readLine().split(" ");
        int D = Integer.parseInt(str[0]);
        int I = Integer.parseInt(str[1]);
        int S = Integer.parseInt(str[2]);
        int V = Integer.parseInt(str[3]);
        int F = Integer.parseInt(str[4]);
        int arr[] = new int[I];
        int green[] = new int[I];
        int count = 0;
        ArrayList<graph> list = new ArrayList<>();
        ArrayList<intersaction> inter = new ArrayList<>();
        ArrayList<ans> ans = new ArrayList<>();

        for(int i = 0 ; i < S; i++){
            String s[] = br.readLine().split(" ");
            int src = Integer.parseInt(s[0]);
            int dest = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[3]);
            String name = s[2];
            graph myAns = new graph(src, dest, name, w);
            
            list.add(myAns);
        }
        int time = 0;
        for(int i = 0; i < V; i++){
            String s[] = br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);

            for(int j = 0; j < n; j++){
                String street = s[j + 1];
                int temp[] = giveDest(street, list);
                if(j == 0){
                    inter.add(new intersaction(temp[1], time, temp[0]));
                    ans.add(new ans(temp[1], temp[0], time));
                    green[temp[1]] += 2;
                }else{
                    
                    time += giveTime(street, list);
                    inter.add(new intersaction(temp[1], time, temp[0]));
                    ans.add(new ans(temp[1], temp[0], time));
                    green[temp[1]] += 1;
                }
                arr[temp[1]]++;
                if(arr[temp[1]] == 1) count++;
                time++;
            }
            time = 0;
            
        }


        
        // System.out.println(count);
        // for(int i = 0; i < ans.size(); i++){
        //     ans rmv = ans.get(i);
        //     System.out.println(rmv.src);
        //     System.out.println(arr[rmv.src]);
        //     String a = getName(rmv.src, rmv.parent, list);
        //     System.out.println(a + " " + green[rmv.src]);
        // }



        
    }

    public static String getName(int src, int dest, ArrayList<graph> list){
        for(graph g : list){
            if(g.src == src && g.dest == dest){
                return g.name;
            }
        }
        return null;
    }

    public static int giveTime(String street, ArrayList<graph> list) {
        for(graph g : list){
            if(g.name.equals(street)){
                return g.w;
            }
        }
        return 0;
    }

    public static int[] giveDest(String street, ArrayList<graph> list) {
        for(graph g : list){
            if(g.name.equals(street)){
                return new int[]{g.src,g.dest};
            }
        }
        return new int[2];
    }
}
