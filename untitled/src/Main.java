import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Integer.valueOf;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
      var x =  arrayList.stream().map(s-> s+1).toList();


    }
}