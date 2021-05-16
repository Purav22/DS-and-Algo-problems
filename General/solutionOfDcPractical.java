import java.util.*;

public class solutionOfDcPractical {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        dcPract1(str);
    }

public static void dcPract1(String str){

    HashMap<Character, Integer> map = new HashMap<>();
    String s = "";
    for(int i = 0; i < str.length(); i++){
        char ch = str.charAt(i);
        if(map.containsKey(str.charAt(i))){
            map.put(ch, map.get(ch) + 1);

        }else{
            s = s + ch;
            map.put(ch, 1);
        }
    }

    double prob[] = new double[map.size()];
    double selfInfo[] = new double[map.size()];
    double arr[] = new double[map.size()];

    int n = str.length();
    double entropy = 0;
    for(int i = 0; i < s.length(); i++){
        char ch = s.charAt(i);
        //System.out.println((double)map.get(ch) / n);
        prob[i] = (double)map.get(ch) / n;
        //System.out.println(prob[i]);
        selfInfo[i] = -(Math.log(prob[i]) / Math.log(2));
        arr[i] = prob[i] * selfInfo[i];
        entropy += arr[i];
    }

    System.out.println("|   text    |   Freqency    |   Probability    |    Self Information    |   P*S    |");
    System.out.println("_____________________________________________________________________________________");
    for(int i = 0; i < s.length(); i++){
        char ch = s.charAt(i);
        prob[i] = (Math.round(prob[i] * 100))/100.0; //
        selfInfo[i] = (Math.round(selfInfo[i] * 100))/100.0;
        arr[i] = (Math.round(arr[i] * 100))/100.0;
        
        System.out.println("      " + ch +"             " + map.get(ch) + "              " + prob[i] + "                  " + selfInfo[i] + "               " + arr[i]);
    }
    System.out.println("_____________________________________________________________________________________");
    entropy = (Math.round(entropy * 100))/100.0;
    System.out.println("Entropy is-------->" + entropy);

}
}