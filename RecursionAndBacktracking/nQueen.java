import java.util.ArrayList;

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
   

}
