package Models;

public class Product {

    private String name;
    private boolean isDeleted;

    //getters and setters
    //name
    public void setName(String s){this.name=s;}
    public String getName(){return this.name;}
    //deleted
    public void setDeleted(boolean s){this.isDeleted=s;}
    public boolean getDeleted(){return this.isDeleted;}

    //constructors
    public Product(){}
    public Product(String n){
        this.name=n;
        this.isDeleted=false;
    }

    //other metods
    public void Delete() {
        this.isDeleted = true;
    }
}
