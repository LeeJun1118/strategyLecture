package printStrategy;


class A implements I {
    public void  ma(){
        System.out.println("printStrategy.A");
    }
}
class B implements I {
    public void  ma(){
        System.out.println("printStrategy.B");
    }
}
class C implements I {
    public void  ma(){
        System.out.println("printStrategy.C");
    }
}
class D implements I {
    public void  ma(){
        System.out.println("printStrategy.D");
    }
}
class Print{
    public I strategy;

    public Print(I strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(I strategy){
        this.strategy = strategy;
    }
    public void applyPrint(){
        strategy.ma();
    }

}

public class PrintStrategy {
    public static void main(String[]args){
        I a = new A();
        I b = new B();
        I c = new C();
        I d = new D();

        Print aObj = new Print(a);
        Print bObj = new Print(b);
        Print cObj = new Print(c);
        Print dObj = new Print(d);

        aObj.applyPrint();
        bObj.applyPrint();
        cObj.applyPrint();
        dObj.applyPrint();

        //strategy
        aObj.setStrategy(c);
        dObj.setStrategy(b);

        System.out.println();

        aObj.applyPrint();
        bObj.applyPrint();
        cObj.applyPrint();
        dObj.applyPrint();
    }
}
