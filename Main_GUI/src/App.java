
public class App {
    public static void main(String[] args) throws Exception {
        
        Database db = new Database("books.db","books");
       //Database dbUser = new Database("user.db", "UserInfo");
        // String [] columnName = {"id","Name","LastName","Passade","DOB"};
        // String [] columnTypes = {"integer","varchar(255)","varchar(255)","varchar(255)","varchar(255)"};
        // String [][] data = {{"22","Java-Basic","Unknown","223"},
        //                     {"26","SQL-JavaEdc-Compute","Unknown","233"},
        //                     {"29","ava-non","Unknown","23"}};

        //String [] data = {"24","Java for beginners","Unknown","223344"};
        //db.deleteTable("books");
        //db.createTable(columnName, columnTypes);
        // for(String [] i:data)
        //String [] i = {"42","Usman","Khalid","223344","12/02/2001"};
        //dbUser.insertData(i); 
        //db.insertData(data);
        //db.updateData(data);
        //db.displayDatabase();
        //db.removeColumn("23");
        //System.out.println("\n-------------------------");
        //dbUser.createTable(columnName, columnTypes);
        //dbUser.insertData(data);  
       // dbUser.displayDatabase();     
        //db.bookSearch("2");
        //db.searchTable("edc");
        //System.out.println(dbUser.searchTable("sheryar"));
        //dbUser.displayDatabase();
        System.out.println();
        System.out.println(db);
        System.out.print(db.getHash("sheryar123")+"\n");
        System.out.print(db.getHash("sheryar123"));

        
    }
}
