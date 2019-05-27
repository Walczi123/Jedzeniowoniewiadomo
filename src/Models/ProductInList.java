package Models;

public class ProductInList {
    Product product;
    Float amount;
    Unit unit;
    //constructor
    public ProductInList(){}
    public ProductInList( ProductInList p){
        this.product=p.product;
        this.amount=p.amount;
        this.unit=p.unit;
    }
    //setters and getters
    //product
    public void setProduct(Product p){this.product=p;}
    public Product getProduct(){return this.product;}
    //amount
    public void setAmount(Float a){this.amount=a;}
    public  Float getAmount(){return this.amount;}
    //unit
    public void setUnit(Unit u){this.unit=u;}
    public Unit getUnit(){return this.unit;}
    //print
    public void print(){
        System.out.println(this.product.getName()+" "+this.amount+" "+this.unit.getName());
    }
}
