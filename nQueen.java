import javax.xml.validation.Validator;

public class nQueen {
    static boolean[] rowA;
    static boolean[] colA;
    static boolean[] diagA;
    static boolean[] aDiagA;

    public static void toggleNQueen(int r, int c, int n) {
        rowA[r] = !rowA[r];
        colA[c] = !colA[c];
        diagA[r - c + n - 1] = !diagA[r - c + n - 1];
        aDiagA[r + c] = !aDiagA[r + c];
    }
    public static int nQueen01(boolean[][] box, int idx, int tnq, String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int n = box.length;
        int m = box[0].length;

        for(int i = idx; i < n * m; i++){
            int r = i / m;
            int c = i % m;
            if(isSafeToPlaceQueen(box, r, c)){
                box[r][c] = true;
                count += nQueen01(box, i + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }
        }
        return count;
    }

    public static boolean isSafeToPlaceQueen(boolean[][] boxes, int r, int c) {
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        for (int d = 0; d < dir.length; d++) {
            for (int rad = 1; rad < boxes.length; rad++) {
                int x = r + rad * dir[d][0];
                int y = c + rad * dir[d][1];

                if (x >= 0 && y >= 0 && x < boxes.length && y < boxes[0].length) {
                    if (boxes[x][y])
                        return false;

                } else
                    break;
            }
        }

        return true;
    }
   

    public static int nQueen02(int n, int idx, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < n * n; i++) {
            int r = i / n;
            int c = i % n;
            if (!rowA[r] && !colA[c] && !diagA[r - c + n - 1] && !aDiagA[r + c]) {
                toggleNQueen(r, c, n);
                count += nQueen02(n, i + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                toggleNQueen(r, c, n);
            }
        }
        return count;
    }

    public static void subseq(String str, int idx, String ans) {
        if (idx == str.length()) {
            return;
        }

        for (int i = idx; i < str.length(); i++) {
            char ch = str.charAt(i);
            System.out.println(ans + ch);
            subseq(str, i + 1, ans + ch);
        }
    }


    public static int nQueen04(int n, int r, int tnq, String ans){
        if(tnq == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;

        for(int c = 0; c < n; c++){
            if (!rowA[r] && !colA[c] && !diagA[r - c + n - 1] && !aDiagA[r + c]) {
                toggleNQueen(r, c, n);
                count += nQueen04(n, r + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                toggleNQueen(r, c, n);
            }
        }
        return count;
    }
    static int colB = 0;
    static int diagB = 0;
    static int aDiagB = 0;

    public static void toggleNQueenBit(int r, int c, int n) {
        colB ^= (1 << c);
        diagB ^= (1 << (r - c + n - 1));
        aDiagB ^= (1 << (r + c));
    }

    public static int nQueen04BitMask(int n, int r, int tnq, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int c = 0; c < n; c++) {
            if ((colB & (1 << c)) == 0 && (diagB & (1 << (r - c + n - 1))) == 0 && (aDiagB & (1 << (r + c))) == 0) {
                toggleNQueenBit(r, c, n);
                count += nQueen04BitMask(n, r + 1, tnq - 1, ans + "(" + r + "," + c + ") ");
                toggleNQueenBit(r, c, n);
            }
        }

        return count;
    }

    // ***************************sudoku***************************************
    char[][] board;
    public boolean isValidToPlaceNumber(int r, int c, int num){
        for(int i = 0; i < board.length; i++){
            if(board[r][i] - '0' == num){
                return false;
            }
        }
        for(int i = 0; i < board.length; i++){
            if(board[i][c] - '0' ==num){
                return false;
            }   
        }
        r = (r / 3) * 3;
        c = (c / 3) * 3;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[r + i][c + j] -'0' ==num)
                    return false;
            }
        }
        return true;

    }

    public int sudokuSolver(int idx){
        if(idx == board.length * board[0].length){
            return 1;
        }

        int r = idx / board[0].length;
        int c = idx % board[0].length;
        int count = 0;
        if(board[r][c] != '.'){
            return sudokuSolver(idx + 1);
        }

        for(int num = 1; num <= 9; num++){
            if(isValidToPlaceNumber(r, c, num)){

                board[r][c] = (char)('0' + num);
                count += sudokuSolver(idx + 1);
                board[r][c] = '.';
            }
        }
        return count;
    }
    public boolean sudokuSolver_01(int loc[], int idx)
    {
        if (idx == loc.length)
        {
            
            return true;
        }

        int r = loc[idx] / board[0].length;
        int c = loc[idx] % board[0].length;
        boolean res = false;

        for (int num = 1; num <= 9; num++)
        {
            if (isValidToPlaceNumber(r, c, num))
            {
                board[r][c] = (char)('0' + num);
                res = res || sudokuSolver_01(loc, idx + 1);
                board[r][c] = '.';
            }
        }

        return res;
    }

}
