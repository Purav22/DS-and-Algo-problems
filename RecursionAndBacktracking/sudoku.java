import java.util.ArrayList;
import java.util.HashSet;

public class sudoku {
    

    // *********************************LeetCode 37****************************
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
    int row[];
    int col[];
    int mat[][];

    public void toggleSudokuBit(int r, int c, int num) {
        int mask = (1 << num);
        row[r] ^= mask;
        col[c] ^= mask;
        mat[r / 3][c / 3] ^= mask;
    }
    public boolean sudokuSolver_Bit(char[][] board, ArrayList<Integer> loc, int idx) {
        if (idx == loc.size()) {
            return true;
        }

        int r = loc.get(idx) / board[0].length;
        int c = loc.get(idx) % board[0].length;

        for (int num = 1; num <= 9; num++) {
            int mask = (1 << num);
            if ((row[r] & mask) == 0 && (col[c] & mask) == 0 && (mat[r / 3][c / 3] & mask) == 0) {
                board[r][c] = (char) (num + '0');
                toggleSudokuBit(r, c, num);

                if (sudokuSolver_Bit(board, loc, idx + 1))
                    return true;

                board[r][c] = '.';
                toggleSudokuBit(r, c, num);
            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {
        ArrayList<Integer> loc = new ArrayList<>();

        row = new int[9];
        col = new int[9];
        mat = new int[3][3];

        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.')
                    loc.add(i * m + j);
                else
                    toggleSudokuBit(i, j, board[i][j] - '0');
            }
        }
        sudokuSolver_Bit(board, loc, 0);
    }

    //********************************LeetCode 36*************************
   public static  boolean isValidSudoku(char[][] board) {
        @SuppressWarnings("unchecked")
        HashSet<Integer>[] row = new HashSet[9];
        @SuppressWarnings("unchecked")
        HashSet<Integer>[] col = new HashSet[9];
        @SuppressWarnings("unchecked")
        HashSet<Integer>[][] mat = new HashSet[3][3];

        int n = board.length;
        int m = board[0].length;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] != '.') {
                    int num = board[r][c] - '0';
                    if (!row[r].contains(num) && !col[c].contains(num) && !mat[r / 3][c / 3].contains(num)) {
                        row[r].add(num);
                        col[c].add(num);
                        mat[r / 3][c / 3].add(num);
                    } else
                        return false;
                }
            }
        }
        return true;
    }
}