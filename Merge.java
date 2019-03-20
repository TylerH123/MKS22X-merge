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
  public static void mergesortO(int[] data, int[] temp, int lo, int hi){
    if(lo >= hi){
      return;
    }
    if ((hi-lo) < 10){
      insertionSort(data,lo,hi);
    }
    else{
      int mid = (hi + lo) / 2;
      //left half
      mergesortO(temp, data, lo, mid);
      //right half
      mergesortO(temp, data, mid + 1, hi);
      int idx = lo; // index being replaced in data
      int leftIdx = lo; // 0
      int rightIdx = mid+1; // 1
      while (idx <= hi) {
        if (rightIdx > hi) {
          data[idx] = temp[leftIdx];
          leftIdx++;
        } else if (leftIdx > mid) {
          data[idx] = temp[rightIdx];
          rightIdx++;
        } else if (temp[leftIdx] <= temp[rightIdx]) {
          data[idx] = temp[leftIdx];
          leftIdx++;
        } else {
          data[idx] = temp[rightIdx];
          rightIdx++;
        }
        idx++;
      }
    }
  }
  private static void insertionSort(int[] data, int lo, int hi) {
    for (int i = lo+1; i <= hi; i++) {
      int current = data[i];
      int j = i-1;
      while (j >= lo && data[j] > current) {
        data[j+1] = data[j];
        j--;
      }
      data[j+1] = current;
    }
  }
  /**public static void main(String[] args){
    int[] arr = new int[100000];
    int[] arr2 = new int[100000];
    Random rand = new Random();
    for (int i = 0; i < arr.length; i++){
      int r = rand.nextInt();
      arr[i] = r;
      arr2[i] = r;
    }
    mergesort2(arr);
    Arrays.sort(arr2);
    System.out.println(Arrays.toString(arr).equals(Arrays.toString(arr2)));
  }**/
  //Sort testing code
  private static final int INCREASE = 0;
  private static final int DECREASE = 1;
  private static final int STANDARD = 2;
  private static final int SMALL_RANGE = 3;

  private static String name(int i){
    if(i==INCREASE)return "Increassing";
    if(i==DECREASE)return "Decreassing";
    if(i==STANDARD)return "Normal Random";
    if(i==SMALL_RANGE)return "Random with Few Values";
    return "Error categorizing array";

  }

  private static int create(int min, int max){
    return min + (int)(Math.random()*(max-min));
  }

  private static int[]makeArray(int size,int type){
    int[]ans =new int[size];
    if(type == STANDARD){
      for(int i = 0; i < size; i++){
        ans[i]= create(-1000000,1000000);
      }
    }
    else if(type == INCREASE){
      int current = -5 * size;
      for(int i = 0; i < size; i++){
        ans[i]= create(current,current + 10);
        current += 10;
      }
    }
    else if(type == DECREASE){
      int current = 5 * size;
      for(int i = 0; i < size; i++){
        ans[i]= create(current,current + 10);
        current -= 10;
      }
    }
    else if(type == SMALL_RANGE){
      for(int i = 0; i < size; i++){
        ans[i]= create(-5,5);
      }
    }
    else{
      ans = new int[0];//empty is default
    }
    return ans;
  }

  public static void main(String[]args){
    if(args.length < 2)return;

    int size =  Integer.parseInt(args[0]);
    int type =   Integer.parseInt(args[1]);

    int [] start = makeArray(size,type);
    int [] result = Arrays.copyOf(start,start.length);
    Arrays.sort(result);

    long startTime = System.currentTimeMillis();
    /*
     * Test your sort here //yoursort(start);
     * Add code to switch which sort is tested by changing one of the args!
     */
    mergesortO(start,start,0,start.length-1);
    long elapsedTime = System.currentTimeMillis() - startTime;
    if(Arrays.equals(start,result)){
      System.out.println("PASS Case "+name(type)+"\t array, size:"+start.length+"\t"+elapsedTime/1000.0+"sec ");
    }else{
      System.out.println("FAIL ! ERROR ! "+name(type)+" array, size:"+size+"  ERROR!");
    }
  }
}
