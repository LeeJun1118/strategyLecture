// https://en.wikipedia.org/wiki/Strategy_pattern

import java.util.ArrayList;
import java.util.List;

public class StrategyPatternWiki2 {

    public static void main(final String[] arguments) {
        // Prepare strategies
        BillingStrategy normalStrategy    = new NormalStrategy();
        BillingStrategy happyHourStrategy = new HappyHourStrategy();
		BillingStrategy memberStrategy   = new MemberStrategy();

        Customer firstCustomer = new Customer(normalStrategy);

        // Normal billing
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
	
		// member customer
		Customer thirdCustomer = new Customer(memberStrategy);
		thirdCustomer.add(1.0, 1);
		thirdCustomer.add(2.0, 1);
		thirdCustomer.printBill();
    }
}

// Strategy for Member Customer (30% discount)
class MemberStrategy implements BillingStrategy {
    @Override
    public double getActPrice(final double rawPrice) {
		return rawPrice*0.7;
    }
}
