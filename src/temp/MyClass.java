package temp;

class MyClass
{
    int x;
    String p;
    char z;

    MyClass (int x,String p,char z) {
        this.x = x;
        this.p = p;
        this.z = z;
    }

}


class ConsDemo
{
    public static void main(String args[]) {
        MyClass t1 = new MyClass(10, "проба p", 'H');
        MyClass t2 = new MyClass(20, "проба f", 'D');

        System.out.println(t1.x + " " + t2.x);
        System.out.println(t1.p + " " + t2.p);
        System.out.println(t1.z + " " + t2.z);
        System.out.println(t1 + " " + t2);
    }
}