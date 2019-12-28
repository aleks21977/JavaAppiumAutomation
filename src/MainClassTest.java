import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test
    public void testGetLocalNumber()
    {
        int a = this.getLocalNumber();
        //System.out.println(this.getLocalNumber());

        if (a != 14)
        {
            Assert.fail("Number != 14");
        } else {
            System.out.println("Number == 14");
        }

    }
}
