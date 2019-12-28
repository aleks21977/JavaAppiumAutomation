import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{

    @Test
    public void testGetClassNumber()
    {
        int a = this.getClassNumber();
        if (a > 45)
        {
            System.out.println("Number == " + a);

        } else {
            Assert.fail("Number <= 45");
        }
    }

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
