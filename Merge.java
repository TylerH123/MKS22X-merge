import java.util.*;

public class Merge{

  public static void mergesort(int[] data){
    mergesort(data,0,data.length-1);
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
    
  }
}
