import SQL.PostgresDatabaseProvider;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            PostgresDatabaseProvider databaseLoader = new PostgresDatabaseProvider("localhost:5432/Jedzenioweniewiadomo", "user", "password");
            databaseLoader.open();
            //testujta dowoli
            databaseLoader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}