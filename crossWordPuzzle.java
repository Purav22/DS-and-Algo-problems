
public class crossWordPuzzle {
    
    public static boolean isPossibleToPlaceH(char[][] arr, String word, int r, int c) {
        for(int i = 0; i < word.length(); i++){
            if(i + c == arr[0].length){
                return false;
            }
            if(arr[r][c + i] == '-' || arr[r][c + i] == word.charAt(i)){
                continue;
            }else{
                return false;
            }

        }
        return true;
    }
    public static boolean[] placeH(char[][] arr, String word, int r, int c) {
        boolean[] location = new boolean[word.length()];
        for(int i = 0; i < word.length(); i++){
            if(arr[r][c + i] == '-'){
                location[i] = true;
                arr[r][c + i] = word.charAt(i);
            }
        }
        return location;
    }
    public static void UnPlaceH(char[][] arr, String word, int r, int c, boolean[] location) {

        for(int i = 0; i < word.length(); i++){
            if(location[i] == true){
                arr[r][c + i] = '-';
            }
        }
        
    }
    public static boolean isPossibleToPlaceV(char[][] arr, String word, int r, int c) {
        for(int i = 0; i < word.length(); i++){
            if(i + r == arr.length){
                return false;
            }
            if(arr[r + i][c] == '-' || arr[r + i][c] == word.charAt(i)){
                continue;
            }else{
                return false;
            }

        }
        return true;
        
    }
    public static boolean[] placeV(char[][] arr, String word, int r, int c) {
        boolean[] location = new boolean[word.length()];
        for(int i = 0; i < word.length(); i++){
            if(arr[r + i][c] == '-'){
                location[i] = true;
                arr[r + i][c] = word.charAt(i);
            }
        }
        return location;
    }
    public static void UnPlaceV(char[][] arr, String word, int r, int c, boolean[] location) {
        for(int i = 0; i < word.length(); i++){
            if(location[i] == true){
                arr[r + i][c] = '-';
            }
        }
    }

    public static int crosswordPuzzle(char[][] arr, String[] words, int vidx) {
        if(vidx == words.length){
            displayArray(arr);
            return 1;
        }
        String word = words[vidx];
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                if(arr[i][j] == '-' || arr[i][j] == word.charAt(0)){
                    if(isPossibleToPlaceH(arr, word, i, j)){
                        boolean[] location = placeH(arr, word, i, j);
                        count+= crosswordPuzzle(arr, words, vidx + 1);
                        UnPlaceH(arr, word, i, j, location);
                    }

                    if(isPossibleToPlaceV(arr, word, i, j)){
                        boolean[] location = placeV(arr, word, i, j);
                        count+= crosswordPuzzle(arr, words, vidx + 1);
                        UnPlaceV(arr, word, i, j, location);
                    }
                }
            }
        }
        return count;
    }

    private static void displayArray(char[][] arr) {
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
