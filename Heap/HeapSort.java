import java.util.ArrayList;

public class HeapSort {

    public static int compareTo(ArrayList<Integer> arr, boolean isMax, int a, int b) {
        if (isMax)
            return arr.get(a) - arr.get(b);
        else
            return arr.get(b) - arr.get(a);
    }

    public static void downHeapify(ArrayList<Integer> arr, int pi, boolean isMax, int n) {
        int maxidx = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;

        if (lci <= n && compareTo(arr, isMax, lci, maxidx) > 0)
            maxidx = lci;
        if (rci <= n && compareTo(arr, isMax, rci, maxidx) > 0)
            maxidx = rci;

        if (maxidx != pi) {
            swap(arr, pi, maxidx);
            downHeapify(arr, maxidx, isMax, n);
        }
    }

    public static void swap(ArrayList<Integer> arr, int a, int b) { // O(1)
        int val1 = arr.get(a);
        int val2 = arr.get(b);

        arr.set(a, val2);
        arr.set(b, val1);
    }

    public static void solve() {
        int[] data = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        int n = data.length;
        boolean isMax = true;

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(data[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            downHeapify(arr, i, isMax, n - 1);
        }

        int i = n - 1;
        while (i >= 0) {
            swap(arr, 0, i);
            i--;
            downHeapify(arr, 0, isMax, i);
        }

        for (int ele : arr)
            System.out.print(ele + " ");
        ;
        System.out.println();
    }

    public static void main(String[] args) {
        solve();
    }

}