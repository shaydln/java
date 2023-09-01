package expression;

public class Clear implements binaryAndTripleInterface {
    binaryAndTripleInterface left, right;
    public Clear(binaryAndTripleInterface left, binaryAndTripleInterface right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toMiniString() {
        return null;
    }


    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) & ~(1 << right.evaluate(x, y, z));
    }
    public String toString() {
        return "(" + left.toString() + " clear " + right.toString() + ")";
    }

}
//        boolean belowZero = false;
//        while (b < 0) {
//            b += 32;
//        }
//        while (b > 32) {
//            b -= 32;
//        }
//        int[] binA = new int[32];
//        int pos = 31;
//        int res = 0;
//
//        if (a < 0) {
//            binA[0] = 1;
//            a = Math.abs(Integer.MIN_VALUE - a);
//            res -= Integer.MIN_VALUE;
//            belowZero = true;
//        }
//
//
//        while (a > 0) {
//            binA[pos] = a % 2;
//            pos--;
//            a /= 2;
//        }
//        while (pos >= 0) {
//            binA[pos] = 0;
//            pos--;
//        }
//
//        if (belowZero && binA[0] == 1) {
//            res += Math.abs(Integer.MIN_VALUE);
//            binA[0] = 1;
//        }
//
//        //31 30 29 .... 3 2 1 0
//        //0  1  2 ...  29 30 31
//        //1             -2**31+1
//        //011111111
//
//
//        // 111111111110
//
////        if (binA[31 - b]  == 1) {
////            res += Math.abs(Integer.MIN_VALUE);
////        }
//        binA[31 - b] = 0;
//
//        for (int i = 31; i >= 1; i--) {
//            res += binA[i] * Math.pow(2, 31-i);
//        }
//
//
//        if (!belowZero) {
//            res -= binA[0] * Math.pow(2, 31);
//        }
//
//        return res;

