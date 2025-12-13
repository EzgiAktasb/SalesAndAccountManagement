package model;

import Interface.Calculable;

public class Product implements Calculable{
	private String productID;
	private String name;
	private double price;
	private int quantity;



	//constructor
	 public Product(String productID, String name, double price, int quantity) {
	        this.productID = productID;
	        this.name = name;
	        this.price = price;
	        this.quantity = quantity;
	    }


	 
	 //getters
	 
	  public String getProductID() {
	       return productID;
	  }

	  public String getName() {
	       return name;
	  }

	  public double getPrice() {
	      return price; 
	  }

	  
	  public int getQuantity() {
	      return quantity;
	  }


	  //setters 
	  
	   public void setQuantity(int q) {
	       this.quantity = q;
	   }

	   
	   
	   //calculating utility method overrides from interface Calculable
	   
	    
	    public double calculateTotal() {
	        return price * quantity;
	    }

	    


}
