class sendMoreMoney {
    static String s1 = "send";
    static String s2 = "more";
    static String s3 = "money";
    static int[] mapping = new int[26];
    static boolean[] isNumberUsed = new boolean[10];

    public static int stringTonumber(String str){
        int res = 0;
        for(int i = 0; i < str.length(); i++){
            res = res * 10 + mapping[str.charAt(i) -'a'];
        }
        return res;
    }
    public static int solveSendMoreMoney(){
        
        String str = s1 + s2 + s3;
        int freq = 0;
        for(int i = 0; i < str.length(); i++){
            freq |= (1 << (str.charAt(i) - 'a')); 
        }

        str = "";
        for(int i = 0; i < 26; i++){
            int mask = (1 << i);
            if((freq & mask) != 0)
                str +=
                 (char)(i + 'a');
        }
        return giveMapping(str, 0);
    }

    public static int giveMapping(String str, int idx){
        if(idx == str.length()){
            int num1 = stringTonumber(s1);
            int num2 = stringTonumber(s2);
            int num3 = stringTonumber(s3);

            if(num1 + num2 == num3){
                System.out.println(num1 + "\n" + "+" + num2 + "\n" + num3 + "\n");
                return 1;
            }
            return 0;
        }
        char ch = str.charAt(idx);

        int count = 0;
        for(int num = 0; num <= 9; num++){
            if(!isNumberUsed[num]){
                isNumberUsed[num] = true;
                mapping[ch - 'a'] = num;
                count += giveMapping(str, idx + 1);
                mapping[ch - 'a'] = 0;
                isNumberUsed[num] = false;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        solveSendMoreMoney();
    }
    
}
