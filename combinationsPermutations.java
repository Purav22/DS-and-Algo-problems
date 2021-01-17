

public class combinationsPermutations {
    public static void main(String[] args) {
        //System.out.println(nQueen01(new boolean[4][4], 0, 4, ""));
        int n = 9;
        System.out.println((-939 * n) % 26);
        
    }
    public static int permutationCoinsInfiniteCoins(int arr[], int tar, String ans) {
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int num : arr){
            if(tar - num >= 0)
            count += permutationCoinsInfiniteCoins(arr, tar - num, ans + num);
        }

        return count;
    }

    public static int combinationCoinsInfiniteCoins(int arr[], int idx, int tar, String ans){
        if(tar== 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < arr.length; i++){
            if(tar - arr[i] >= 0)
                count += combinationCoinsInfiniteCoins(arr, i, tar - arr[i], ans + arr[i]);
        }
        return count;
    }

    public static int combinationCoins(int arr[], int idx, int tar, String ans){
        if(tar== 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = idx; i < arr.length; i++){
            if(tar - arr[i] >= 0)
                count += combinationCoins(arr, i + 1, tar - arr[i], ans + arr[i]);
        }
        return count;
    }

    public static int permutationsCoins(int arr[], int tar, String ans){
        if(tar== 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < arr.length; i++){
            int ele = arr[i];
            if(tar - ele >= 0 && arr[i] > 0){
                arr[i] = -1;
                count += permutationsCoins(arr, tar - ele, ans + arr[i]);
            }
            arr[i] = ele;
        }
        return count;

    } 

    public static int combinationCoinsSeq(int arr[], int idx, int tar, String ans){
        if(idx == arr.length || tar == 0){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar - arr[idx] >= 0){
            count += combinationCoinsSeq(arr, idx + 1, tar - arr[idx], ans + arr[idx]);
        }
        count += combinationCoinsSeq(arr, idx + 1, tar, ans);


        return count;
    }

    public static int combinationInfiniteSubSeq(int arr[], int idx, int tar, String ans){
        if(idx == arr.length || tar == 0){
            if(tar == 0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar - arr[idx] >= 0){
            count += combinationCoinsSeq(arr, idx, tar - arr[idx], ans + arr[idx]);
        }
        count += combinationCoinsSeq(arr, idx + 1, tar, ans);


        return count;
    }

    public static int permutationInfiniteSubSeq(int arr[], int idx, int tar, String str){
        if(idx == arr.length || tar == 0){
            if(tar == 0){
                System.out.println(str);
                return 1;
            }
            return 0;
        }


        int count = 0;
        
            
            if(tar - arr[idx] >= 0 ){
               
                count += permutationInfiniteSubSeq(arr, 0,  tar - arr[idx], str + arr[idx]);

            }
            count += permutationInfiniteSubSeq(arr, idx + 1, tar, str);
            
    
        return count;
    }

    public static int permutationSubSeq(int arr[], int idx, int tar, String str){
        if(tar == 0 || idx == arr.length){
            if(tar == 0){
                System.out.println(str);
                return 1;
            }
            return 0;
        }


        int count = 0;
            int num = arr[idx];
            if(tar - arr[idx] >= 0 && arr[idx] > 0){
                arr[idx] = -1;
                count += permutationSubSeq(arr, 0, tar - num, str + num);
                arr[idx] = num;
            }
            
            count += permutationSubSeq(arr, idx + 1, tar, str);
            
        
        return count;
    }

    public static int queenInRoomCombination(int arr, int idx, int tar, String ans, int count){
        if(count == tar){
            System.out.println(ans);
            return 1;
        }
        
        int counted = 0;
        for(int i = idx; i < arr; i++){
            counted += queenInRoomCombination(arr, i + 1, tar, ans + "b" + i + "q" + count + " " , count + 1);
        }
        return counted;
    }

    public static int queenInRoomPermutation(int arr[], int tar, String ans, int count, boolean visited[]){
        if(count == tar){
            System.out.println(ans);
            return 1;
        }
        
        int counted = 0;
        for(int i = 0; i < arr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                counted += queenInRoomPermutation(arr, tar,  ans + "b" + i + "q" + count + " " , count + 1, visited);
                visited[i] = false;
            }
        }
        return counted;
    }








   
}