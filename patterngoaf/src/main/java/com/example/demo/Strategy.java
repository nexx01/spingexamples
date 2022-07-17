import java.util.Arrays;

class Scratch {
    public static void main(String[] args) {
        SortContext sortContext = new SortContext(new BubbleSort());
        int[] ints = {5, 4, 3, 2, 2, 1, 7};

        if(ints.length<100){
            sortContext.setWorker(new BubbleSort());
        } else {
            sortContext.setWorker(new MergeSort());
        }

        System.out.println(Arrays.toString(sortContext.sort(ints)));
    }
}

class SortContext{

    private Worker worker;

    public SortContext(Worker worker) {
        this.worker = worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    int[] sort(int[] ar){
        return worker.sort(ar);
    }

}

interface Worker{
    int[] sort(int[] ar);
}

class BubbleSort implements Worker{

    @Override
    public int[] sort(int[] ar) {
        boolean sorted = false;

        while (!sorted){
            sorted = true;
            for (int i = 0; i < ar.length-1; i++) {
                if(ar[i]>ar[i+1]){
                    int temp = ar[i];
                    ar[i] = ar[i + 1];
                    ar[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        return ar;
    }
}

class MergeSort implements Worker{

    @Override
    public int[] sort(int[] ar) {
        mergeSort(ar,ar.length);
        return ar;
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }
    public static void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}