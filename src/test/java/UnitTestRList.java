/**
 * Created by LuMoR on 10.02.2017.
 */

import org.junit.*;

import static org.junit.Assert.assertEquals;


public class UnitTestRList {
    @org.junit.Test
    public void testAddIndexOf() throws Exception{
        RList<String> TRList = new RList<String>();

        TRList.add("1");
        TRList.add("2");
        int ii = TRList.indexOf("2");

        assertEquals(1, ii);

    }

    @org.junit.Test
    public void testEmpry() throws Exception{
        RList<String> TRList = new RList<String>();

        TRList.add("1");
        TRList.add("2");
        boolean ii = TRList.isEmpty();

        assertEquals(false, ii);

    }

    @org.junit.Test
    public void testRemove() throws Exception{
        RList<String> TRList = new RList<String>();

        TRList.add("1");
        TRList.add("2");
        String ii = TRList.remove(1);

        assertEquals("2", ii);

    }

}
