package Models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Price {

 //specifying format of presenting date
 SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    private double value;
    private int productId;
    private String date;

    //getters and setters
    //value
    public void setValue(float s){this.value = s;}
    public double getValue(){return this.value;}
    //productId
    public void setProductId(int s){this.productId = s;}
    public int getProductId(){return this.productId;}
    //date
    public void setDateString(String s) {this.date = s;}
    public void setDateNow() {this.date = DATE_FORMAT.format(new Date());}
    public String getDate(){return this.date;}

    //constructors
    public Price(){}
    public Price(double v, int p){
        this.value = v;
        this.productId = p;
        this.date= DATE_FORMAT.format(new Date());
    }
    public Price(double v, int p, String d){
        this.value = v;
        this.productId = p;
        this.date= d;
    }
}
