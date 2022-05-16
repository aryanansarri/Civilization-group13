import org.junit.Assert;
import org.junit.Test;

public class ExampleToWriteTest {
//     can define some attribute

//      ** please read notes **
//      #1 test function should be Public!
//      #2 for get coverage test right click to green tick in left and
//      bottom and then run <nameClass> with Coverage!
//      don't forget our project should have at least 70% coverage!

    @Test
    public void testFunction() {
        System.out.println("I am a test!");
        Assert.assertEquals(1, 1);
        //and we can use other function of assert class
    }
}
