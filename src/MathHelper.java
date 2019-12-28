public class MathHelper
{
    final public int simple_int = 7;
    final public static int static_int = 7;

    public void changesSimpleInt()
    {
        static_int = 8;
    }

    public static void changesSimpleIntByStaticFunction()
    {
        this.simple_int = 8;
    }

    public int calc (int a, int b, char action)
    {
        if (action == '+') {
            return this.plus(a, b);
        } else if (action == '-') {
            return this.minus(a, b);
        } else if (action == '*') {
            return this.multiply(a, b);
        } else if (action == '/') {
            return this.devide(a, b);
        } else {
            return this.typeErrorAndReturnDefaultValue("Wrong action: " + action);
        }
    }

    private int typeErrorAndReturnDefaultValue (String error_massage) {
        System.out.println(error_massage);
        return 0;
    }

    private int plus(int a, int b)
    {
        return a + b;
    }

    private int minus(int a, int b)
    {
        return a - b;
    }

    private int multiply(int a, int b)
    {
        return a * b;
    }

    private int devide(int number, int divider)
    {
        if (divider == 0) {
            return this.typeErrorAndReturnDefaultValue("Cannot divide by zero");
        } else {
            return number / divider;
        }

    }


}
