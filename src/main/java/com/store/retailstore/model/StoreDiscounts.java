package com.store.retailstore.model;

public class StoreDiscounts implements Discounts {
    @Override
    public DiscountType getDiscountType() {
        return DiscountType.PERCENTAGE_BASED;
    }

    /**For every $100 on the bill, 
     * there would be a $5 discount	
     * (e.g. for $990,	you	get	$45 as a discount).
     ***/
    @Override
    public double applyDiscount(double amount) {

        if(amount < 100) 
        	return amount;
        else {
            return amount - (((int)(amount / 100)) * 5);
        }
    }
}
