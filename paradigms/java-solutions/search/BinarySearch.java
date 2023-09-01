package search;

public class BinarySearch {
    // P: args.length > 0 && for all j (2 <= j <= args.length - 1) && args[j-1] >= args[j]
    // Q: result = min(j): args[j] <= x && 0 <= j <= n - 1
    public static void main(String[] args) {
        // P
        int[] arr = new int[args.length];
        // P && arr' = arr
        int sum = 0;
        // P && sum == 0 && sum' = sum
        int i = 1;
        // P && sum == 0 && i == 1 && i' = i
        while (i < args.length) {
            // P && i < args.length-1
            arr[i] = Integer.parseInt(args[i]);
            // P && i < args.length-1 && arr'[i] = Integer.parseInt(args[i])
            sum += Integer.parseInt(args[i]);
            // P && i < args.length-1 && sum' = sum' + Integer.parseInt(args[i])
            i++;
            // P && i < args.length-1 && i' = i' + 1
        }
        // P && i >= args.length-1
        if (sum % 2 == 0) {
            // P && sum' % 2 == 0
            System.out.println(recursiveBinarySearch(arr, -1, args.length-1, Integer.parseInt(args[0])));
            // P && result = min(j): args[j] <= x && 0 <= j <= n - 1
        } else {
            // P && sum' % 2 == 1
            System.out.println(iterativeBinarySearch(arr, -1, args.length-1, Integer.parseInt(args[0])));
            // P && result = min(j): args[j] <= x && 0 <= j <= n - 1
        }
        // result = min(j): args[j] <= x && 0 <= j <= n - 1
    }
    // P2: args.length() > 0 && for all j (2 <= j <= args.length - 1) && args[j-1] >= args[j] && x >= min(args) && x <= max(args)
    // Q2: result = min(j): args[j] <= x && 1 <= j <= n - 1
    public static int iterativeBinarySearch(final int[] args, int left, int right, int x) {
        // P2 && left' = left && right' = right
        int middle;
        // I: right' - left' is decreasing and left' <= middle' <= right'
        // P2 && middle' = middle && I
        while (left + 1 < right) {
            // P2 && left' + 1 < right && I
            middle = right - left;
            // P2 && left' + 1 < right && middle' = right' - left' && I
            if (middle % 2 == 1) {
                // P2 && left' + 1 < right && middle' % 2 == 1 && I
                middle -= 1;
                // P2 && left' + 1 < right && middle' % 2 == 0 && I
            } else {
                // P2 && left' + 1 < right && middle' % 2 == 0 && I
            }
            // P2 && left' + 1 < right && middle' % 2 == 0 && I
            middle /= 2;
            // P2 && left' + 1 < right && middle'' = middle' / 2 && I
            middle += left;
            // P2 && left' + 1 < right && middle'' = middle'' + left && I
            if (args[middle+1] <= x) {
                // P2 && left' + 1 < right && args[middle''+1] <= x && I
                right = middle;
                // P2 && left' + 1 < right && right'' = middle'' && I
            } else {
                // P2 && left' + 1 < right && args[middle''+1] > x && I
                left = middle;
                // P2 && left' + 1 < right && left' = middle'' && I
            }
            // P2 && left' + 1 < right && I
        }
        // P2 && left' + 1 >= right' && I
        // P2 && right = min(j): args[j] <= x && 0 <= j <= n - 1 && I
        return right;
    }


    // P2: args.length() > 0 && for all j (2 <= j <= args.length - 1) && args[j-1] >= args[j] && x >= min(args) && x <= max(args) && left' < right'
    // Q2: result = min(j): args[j] <= x && 1 <= j <= n - 1
    public static int recursiveBinarySearch(int[] args, int left, int right, int x) {
        // I: right' - left' is decreasing and left' <= middle' <= right'
        // P2 && right' = right && left' = left && I
        if (right - left <= 1) {
            // P2 && right' - left' <= 1 && I
            return right;
        }
        // P2 && right - left > 1 && I
        int middle;
        // P2 && right' - left' > 1 && middle ' = middle && I
        middle = right - left;
        // P2 && right' - left' > 1 && middle' = right' - left' && I
        if (middle % 2 == 1) {
            // P2 && right' - left' > 1 && middle' % 2 == 1 && I
            middle -= 1;
            // P2 && right' - left' > 1 && middle' % 2 == 0 && I
        } else {
            // P2 && right' - left' > 1 && middle' % 2 == 0 && I
        }
        // P2 && right' - left' > 1 && middle' % 2 == 0 && I
        middle /= 2;
        // P2 && right' - left' > 1 && middle'' = middle' / 2 && I
        middle += left;
        // P2 && right' - left' > 1 && middle'' = middle'' + left && I
        if (args[middle+1] <= x) {
            // P2 && right' - left' > 1 && args[middle+1] <= x && I
            return recursiveBinarySearch(args, left, middle, x);
            // Q2: result = min(j): args[j] <= x && 0 <= j <= n - 1 && I && left = middle
        } else {
            // P2 && right' - left' > 1 && args[middle+1] > x && I
            return recursiveBinarySearch(args, middle, right, x);
            // Q2: result = min(j): args[j] <= x && 0 <= j <= n - 1 && I && right = midle
        }
    }
}
