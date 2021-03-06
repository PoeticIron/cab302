package asgn2Pizzas;

import java.time.LocalTime;

import asgn2Exceptions.PizzaException;


/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant. 
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza and MeatLoversPizza. 
 * Each of these subclasses have a different set of toppings. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment Specification. 
 * 
 * @author Person A
 *
 */
public abstract class Pizza  {
	
	private int quantity;
	private LocalTime orderTime;
	private LocalTime deliveryTime;
	private String pizzaType;
	private double price;
	private double totalPrice;
	private double costPerPizza;
	private double meatLoversCost = 12.00, vegetarianCost = 10.00, margheritaCost = 8.00;
	
	/**
	 *  This class represents a pizza produced at the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.1. 
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification
	 *  are violated. 
     *
     *  PRE: TRUE
	 *  POST: All field values except cost per pizza are set
	 * 
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @param type -  A human understandable description of this Pizza type
	 * @param price - The price that the pizza is sold to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 * 
	 */
	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price) throws PizzaException{
		//TODO
		if(quantity > 10 || quantity < 1){
			throw new PizzaException("Quantity amount invalid.");
		}
		if(orderTime.getHour() > 23 || orderTime.getHour() < 19){
			throw new PizzaException("Order cannot be made as the kitchen is closed.");
		}
		//is super long cause not sure about how type input is done. will fix later.
		if(type != "margherita" || type != "Margherita" || type != "vegetarian" 
				|| type != "Vegetarian" || type != "Meat Lovers" || type != "meat lovers"){
			throw new PizzaException("We do not serve that type of pizza.");
		}
		
		this.quantity = quantity;
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.pizzaType = type;
		this.price = price;
		this.totalPrice = this.price * this.quantity;
	}

	/**
	 * Calculates how much a pizza would could to make calculated from its toppings.
	 *  
     * <P> PRE: TRUE
	 * <P> POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza(){
		// TO DO
		if(pizzaType == "Margherita"){
			costPerPizza = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost();
		}
		else if(pizzaType == "Vegetarian"){
			costPerPizza = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost()
						+ PizzaTopping.EGGPLANT.getCost() + PizzaTopping.MUSHROOM.getCost()
						+ PizzaTopping.CAPSICUM.getCost();
		}
		else if(pizzaType == "Meat Lovers"){
			costPerPizza = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost()
						+ PizzaTopping.BACON.getCost() + PizzaTopping.PEPPERONI.getCost()
						+ PizzaTopping.SALAMI.getCost();
		}
	}
	
	/**
	 * Returns the amount that an individual pizza costs to make.
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza(){
		// TO DO
		return costPerPizza;
	}

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza(){
		// TO DO
		if(pizzaType == "Margherita"){
			return margheritaCost;
		}
		else if(pizzaType == "Vegetarian"){
			return vegetarianCost;
		}
		else{
			return meatLoversCost;
		}
	}

	/**
	 * Returns the amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderCost(){
		// TO DO
		this.calculateCostPerPizza();
		return this.costPerPizza * this.quantity;
	}
	
	/**
	 * Returns the amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderPrice(){
		// TO DO
		return this.totalPrice;
	}
	
	
	/**
	 * Returns the profit made by the restaurant on the order which is the order price minus the order cost. 
	 * @return  Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 */
	public final double getOrderProfit(){
		// TO DO
		return this.totalPrice - this.getOrderCost();
	}
	

	/**
	 * Indicates if the pizza contains the specified pizza topping or not. 
	 * @param topping -  A topping as specified in the enumeration PizzaTopping
	 * @return Returns  true if the instance of Pizza contains the specified topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping){
		// TO DO
		if(pizzaType == "Margherita"){
			if(PizzaTopping.TOMATO.equals(topping) || PizzaTopping.CHEESE.equals(topping)){
				return true;
			}
			else{
				return false;
			}
		}
		else if(pizzaType == "Vegetarian"){
			if(PizzaTopping.TOMATO.equals(topping) || PizzaTopping.CHEESE.equals(topping)
					|| PizzaTopping.EGGPLANT.equals(topping) || PizzaTopping.MUSHROOM.equals(topping) 
					|| PizzaTopping.CAPSICUM.equals(topping)){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(PizzaTopping.TOMATO.equals(topping) || PizzaTopping.CHEESE.equals(topping)
					|| PizzaTopping.BACON.equals(topping) || PizzaTopping.PEPPERONI.equals(topping) 
					|| PizzaTopping.SALAMI.equals(topping)){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	/**
	 * Returns the quantity of pizzas ordered. 
	 * @return the quantity of pizzas ordered. 
	 */
	public final int getQuantity(){
		// TO DO
		return this.quantity;
	}

	/**
	 * Returns a human understandable description of the Pizza's type. 
	 * The valid alternatives are listed in Section 5.1 of the Assignment Specification. 
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType(){
		// TO DO
		return this.pizzaType;
	}


	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 * You do not need to test this method.
	 *  
	 * @return true if *this* Pizza object and the *other* Pizza object have the same values returned for 	
	 * getCostPerPizza(), getOrderCost(), getOrderPrice(), getOrderProfit(), getPizzaType(), getPricePerPizza() 
	 * and getQuantity().
	 *   
	 */
	@Override
	public boolean equals(Object other){
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza()) &&
			(this.getOrderCost()) == (otherPizza.getOrderCost())) &&				
			(this.getOrderPrice()) == (otherPizza.getOrderPrice()) &&
			(this.getOrderProfit()) == (otherPizza.getOrderProfit()) &&
			(this.getPizzaType() == (otherPizza.getPizzaType()) &&
			(this.getPricePerPizza()) == (otherPizza.getPricePerPizza()) &&
			(this.getQuantity()) == (otherPizza.getQuantity()));
	}

	
}
