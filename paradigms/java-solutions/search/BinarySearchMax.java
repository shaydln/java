package search;

public class BinarySearchMax {
    // P3: args.length > 0 && for all (i < j) except k and t: args[i] < args[j] && left <= right && exists k > t: args[k] > args[t]
    // Q3: result = min(j): args[j] <= x && 1 <= j <= n - 1
    public static int maxBinarySearch(final int[] args, int left, int right) {
        // I: right' - left' is decreasing and left' <= middle' <= right'
        while (left + 1 < right) {
            // P3 && right - left > 1 && I
            int middle;
            // P3 && right' - left' > 1 && middle ' = middle && I
            middle = right - left;
            // P3 && right' - left' > 1 && middle' = right' - left' && I
            if (middle % 2 == 1) {
                // P3 && right' - left' > 1 && middle' % 2 == 1 && I
                middle -= 1;
                // P3 && right' - left' > 1 && middle' % 2 == 0 && I
            } else {
                // P3 && right' - left' > 1 && middle' % 2 == 0 && I
            }
            // P3 && right' - left' > 1 && middle' % 2 == 0 && I
            middle /= 2;
            // P3 && right' - left' > 1 && middle'' = middle' / 2 && I
            middle += left;
            // P3 && right' - left' > 1 && middle'' = middle'' + left && I
            if (args[middle] > args[args.length - 1]) {
                // P3 && right' - left' > 1 && args[middle''] > args[args.length - 1] && I
                left = middle;
                // P3 && right' - left' > 1 && args[middle''] > args[args.length - 1] && left' == middle'' && I
            } else {
                // P3 && right' - left' > 1 && args[middle''] <= args[args.length - 1] && I
                right = middle;
                // P3 && right' - left' > 1 && args[middle''] <= args[args.length - 1] && right' == middle'' && I
            }
        }
        // P3 && left' + 1 >= right' && I
        if (left < 0) {
            // P3 && left' + 1 >= right' && left' < 0 && I
            return  args[args.length-1];
        } else {
            // P3 && left' + 1 >= right' && left' > 0 && I
            return  args[left];
        }
    }

    // P: args.length > 0
    // Q: result = min(j): args[j] <= x && 1 <= j <= n - 1
    public static void main(String[] args) {
        // P
        int[] arr = new int[args.length];
        // P && arr' = arr
        int i = 0;
        // P && sum == 0 && i == 1 && i' = i
        while (i < args.length) {
            // P && i < args.length-1
            arr[i] = Integer.parseInt(args[i]);
            // P && i < args.length-1 && arr'[i] = Integer.parseInt(args[i])
            i++;
            // P && i < args.length-1 && i' = i' + 1
        }
        System.out.println(maxBinarySearch(arr, -1, args.length));
    }


















}
