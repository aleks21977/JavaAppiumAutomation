import org.junit.Test;

public class MainTest extends CoreTestCase
{
    public void typeStartMessage()
    {
        super.typeStartMessage();
        System.out.println("Curreent class is MainTest");
    }

    @Test
    public void myFirstTest() {
        this.typeStartMessage();
    }

    @Test
    public void mySecondTest() {
        this.typeStartMessage();
    }
}
