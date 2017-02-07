import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by LuMoR on 07.02.2017.
 */


public class Test {
    public static void main(String[] args){
        RList<String> TRList = new RList<String>();
        ArrayList<String> tT = new ArrayList<String>();
        tT.add("t1");
        tT.add("t2");
        tT.add("t3");

        TRList.add("1");
        TRList.add("2");
        int ii = TRList.indexOf("2");
        TRList.add(TRList.size(), "3");
        //System.out.println(ii);
        //System.out.println(TRList.contains("First"));
        System.out.println(TRList.isEmpty());
        for (int i=0;i<TRList.size();i++){
        System.out.println(" - "+i+" . "+TRList.get(i));}
        TRList.remove("3");
        System.out.println(TRList.isEmpty());
        for (int i=0;i<TRList.size();i++){
            System.out.println(" - "+i+" . "+TRList.get(i));}
        System.out.println("OK");
        TRList.addAll(2,tT);
        System.out.println(TRList.isEmpty());
        for (int i=0;i<TRList.size();i++){
            System.out.println(" - "+i+" . "+TRList.get(i));}
        System.out.println("OK");
        TRList.removeAll(tT);
        System.out.println(TRList.isEmpty());
        for (int i=0;i<TRList.size();i++){
            System.out.println(" - "+i+" . "+TRList.get(i));}
        System.out.println("OK");
        TRList.addAll(tT);
        System.out.println(TRList.isEmpty());
        for (int i=0;i<TRList.size();i++){
            System.out.println(" - "+i+" . "+TRList.get(i));}
        System.out.println("OK");

    }
}
