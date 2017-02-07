/**
 * Created by LuMoR on 07.02.2017.
 */


public class Test {
    public static void main(String[] args){
        RList<String> TRList = new RList<String>();

        TRList.add("First");
        TRList.add("Second");
        int ii = TRList.indexOf("Second");
        TRList.add(TRList.size(), "3333");
        //System.out.println(ii);
        //System.out.println(TRList.contains("First"));
        System.out.println(TRList.isEmpty());
        for (int i=0;i<TRList.size();i++){
        System.out.println(" - "+i+" . "+TRList.get(i));}
        TRList.remove("3333");
        System.out.println(TRList.isEmpty());
        for (int i=0;i<TRList.size();i++){
            System.out.println(" - "+i+" . "+TRList.get(i));}
        System.out.println("OK");
    }
}
