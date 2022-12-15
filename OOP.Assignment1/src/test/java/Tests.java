import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Member;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }
    @Test
    public void testappend(){
        GroupAdmin a1 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        ConcreteMember abraham = new ConcreteMember();
        a1.register(abraham);
        a1.register(yuval);
        a1.append("hello");
        assertEquals("hello",yuval.usb.toString(),abraham.usb.toString());
    }
    @Test
    public void testinsert(){
        GroupAdmin a1 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        ConcreteMember abraham = new ConcreteMember();
        a1.register(abraham);
        a1.register(yuval);
        a1.insert(0,"testinsert");
        a1.insert(4,"test");
        assertEquals("testtestinsert",yuval.usb.toString(),abraham.usb.toString());
    }
    @Test
    public void testdelete(){
        GroupAdmin a1 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        ConcreteMember abraham = new ConcreteMember();
        a1.register(abraham);
        a1.register(yuval);
        a1.append("testdelete");
        a1.delete(4,10);
        assertEquals("test",yuval.usb.toString(),abraham.usb.toString());
    }
    @Test
    public void testundo(){
        GroupAdmin a1 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        ConcreteMember abraham = new ConcreteMember();
        a1.register(abraham);
        a1.register(yuval);
        a1.append("test");
        a1.append("undo");
        a1.undo();
        assertEquals("test",yuval.usb.toString(),abraham.usb.toString());
        a1.undo();
        assertEquals("",yuval.usb.toString(),abraham.usb.toString());
    }
    @Test
    public void testcopy(){
        GroupAdmin a1 = new GroupAdmin();
        ConcreteMember yuval = new ConcreteMember();
        ConcreteMember abraham = new ConcreteMember();
        a1.register(abraham);
        a1.register(yuval);
        a1.append("test");
        a1.append("undo");
        a1.unregister(yuval);
        a1.append("test");
        ConcreteMember yarin = new ConcreteMember();
        a1.register(yarin);
        assertEquals("testundo",yuval.usb.toString());
        assertEquals("testundotest",abraham.usb.toString());
        assertEquals(null,yarin.usb);
    }
    @Test
    public void testupdate()
    {
        GroupAdmin a1 = new GroupAdmin();
        a1.append("hello");
        a1.append("world");
        ConcreteMember yarin = new ConcreteMember();
        assertEquals(null,yarin.usb);
        a1.register(yarin);
        a1.append("test");
        assertEquals("helloworldtest",yarin.usb.toString());
    }
}