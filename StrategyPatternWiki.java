// https://en.wikipedia.org/wiki/Strategy_pattern

import java.util.ArrayList;
import java.util.List;

public class StrategyPatternWiki {
	
    public static void main(final String[] arguments) {
        // Prepare strategies
        //Stratgy Setting
        BillingStrategy normalStrategy    = new NormalStrategy();
        BillingStrategy happyHourStrategy = new HappyHourStrategy();


        Customer firstCustomer = new Customer(normalStrategy);

        // Normal billing 1달러 짜리 한개
        firstCustomer.add(1.0, 1);

        // Start Happy Hour
        firstCustomer.setStrategy(happyHourStrategy);
        firstCustomer.add(1.0, 2);

        // New Customer
        Customer secondCustomer = new Customer(happyHourStrategy);
        secondCustomer.add(0.8, 1);
        // The Customer pays
        firstCustomer.printBill();

        // End Happy Hour
        secondCustomer.setStrategy(normalStrategy);
        secondCustomer.add(1.3, 2);
        secondCustomer.add(2.5, 1);
        secondCustomer.printBill();
    }
}

class Customer {
    private List<Double> drinks;
    private BillingStrategy strategy;

    //생성자의 파라미터가 들어오면 각 customer에 대해 strategy를 세팅하고 drinks라는 리스틀 만들어준다
    //생성자가 항목에 strategy필드로 넣고 어떤 strategy를 넣을건지 적용을한다.
    public Customer(final BillingStrategy strategy) {
        this.drinks = new ArrayList<Double>();
        this.strategy = strategy;
    }

    public void add(final double price, final int quantity) {
        //public void add(final double price, final int quantity) 의 add와 밑의 add는 전혀 다르다
        //밑에꺼는 drinks리스트에 add를 하는 메소드
        drinks.add(strategy.getActPrice(price*quantity));
    }

    // Payment of bill
    public void printBill() {
        double sum = 0;
        for (Double i : drinks) {
            sum += i;
        }
        System.out.println("Total due: " + sum);
        drinks.clear();
    }

    // Set Strategy
    //다이나믹하게 바뀐다
    public void setStrategy(final BillingStrategy strategy) {
        this.strategy = strategy;
    }
}

interface BillingStrategy {
    double getActPrice(final double rawPrice);
}

// Normal billing strategy (unchanged price)
class NormalStrategy implements BillingStrategy {
    @Override
    public double getActPrice(final double rawPrice) {
        return rawPrice;
    }
}

// Strategy for Happy hour (50% discount)
class HappyHourStrategy implements BillingStrategy {
    @Override
    public double getActPrice(final double rawPrice) {
        return rawPrice*0.5;
    }
}
