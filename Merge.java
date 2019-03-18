import java.util.*;

public class Merge{

  public static void mergesort(int[] data){
    mergesort(data,0,data.length);
  }

  public static void mergesort(int[] data, int lo, int hi){
    if(lo >= hi){
      return;
    }
    //middle index of data
    int mid = data.length / 2;
    //left side of data
    int[] left = Arrays.copyOfRange(data,0,mid);
    mergesort(left,0,left.length-1);
    //right side of data
    int[] right = Arrays.copyOfRange(data,mid,data.length);
    mergesort(right,0,right.length-1);
    int idx = 0;
    int rightIdx = 0;
    int leftIdx = 0;
    while (idx < data.length){
      if (leftIdx >= left.length) {
        data[idx] = right[rightIdx];
        rightIdx++;
        idx++;
      }
      else if (rightIdx >= right.length) {
        data[idx] = left[leftIdx];
        leftIdx++;
        idx++;
      }
      else if (left[leftIdx] <= right[rightIdx]) {
        data[idx] = left[leftIdx];
        leftIdx++;
        idx++;
      }
      else {
        data[idx] = right[rightIdx];
        rightIdx++;
        idx++;
      }
    }
  }
  public static void main(String[] args){
    int[] arr = new int[100000];
    int[] arr2 = new int[100000];
    Random rand = new Random();
    for (int i = 0; i < arr.length; i++){
      int r = rand.nextInt();
      arr[i] = r;
      arr2[i] = r;
    }
    mergesort(arr);
    Arrays.sort(arr2);
    System.out.println(Arrays.toString(arr).equals(Arrays.toString(arr2)));
  }
}
