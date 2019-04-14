package Models;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    private String description;
    private List<Integer> productsList=new ArrayList<>();
    private int  userId;
    //getters and setters
    //name
    public void setName(String s){this.name=s;}
    public String getName(){return this.name;}
    //description
    public void setDescription(String s){this.description=s;}
    public String getDescription(){return this.description;}
    //productList
    public void setProductsList(List<Integer> l){this.productsList=l;}
    public List<Integer> getProductsList(){return this.productsList;}
    public void addProduct(Integer elem){this.productsList.add(elem);}
    //userId
    public void setUserId(int u){this.userId=u;}
    public int getUserId(){return this.userId;}
    //constructors
    public Recipe(){}
    public Recipe(String n,String desc,List<Integer> list,int id){
        this.name=n;
        this.description=desc;
        this.productsList=list;
        this.userId=id;
    }
}
