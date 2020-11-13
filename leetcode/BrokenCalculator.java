package leetcode;

public class BrokenCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(BrokenCalculator.brokenCalc(5, 9));
	}
	
	public static int brokenCalc(int X, int Y) {
        if (X > Y) {
            return X - Y;
        }
        int[] res = Y % 2 == 0 ? new int[Y + 1] : new int[Y + 2];
        for (int i = 0; i <= X; i++) {
            res[i] = X - i;
        }
        for (int i = X + 1; i <res.length; i++) {
            if (i % 2 == 0) {
                res[i] = res[i / 2] + 1;
                if (i - 1 != X) {
                    res[i - 1] = res[i] + 1;
                }
            } 
        }
        return res[Y];
    }
	
	public static int brokenCalc2(int X, int Y) {
		if (X >= Y) {
			return X - Y;
		}
		
		if (Y % 2 == 0) {
			return brokenCalc2(X, Y / 2) + 1;
		} else {
			return brokenCalc2(X, (Y + 1) / 2) + 2;
		}
		
	}

}
