package Models;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    private String description;
    private List<ProductInList> productsList =  new ArrayList<ProductInList>();
    private int  userId;
    private boolean isDeleted;
    //getters and setters
    //name
    public void setName(String s){this.name=s;}
    public String getName(){return this.name;}
    //description
    public void setDescription(String s){this.description=s;}
    public String getDescription(){return this.description;}
    //productList
    public void setProductsList(List<ProductInList> l){this.productsList=l;}
    public List<ProductInList> getProductsList(){return this.productsList;}
    public void addProduct(ProductInList elem){this.productsList.add(elem);}
    //userId
    public void setUserId(int u){this.userId=u;}
    public int getUserId(){return this.userId;}
    //isDeleted
    public void setDeleted(boolean u){this.isDeleted=u;}
    public boolean getDeleted(){return this.isDeleted;}
    //constructors
    public Recipe(){}
    public Recipe(String n,String desc,List<ProductInList> list,int id){
        this.name=n;
        this.description=desc;
        this.productsList=list;
        this.userId=id;
        this.isDeleted=false;
    }
}
