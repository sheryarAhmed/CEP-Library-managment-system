//import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect{

    public static void main(String[] args) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:sqlite:db/table.db");
            System.out.println("Your database has been opened");
            try {
                deleteTable(con);
            }
            catch(Exception e){
                System.out.println("did not delete the table");
            }
            createTable(con);
            System.out.println();
            System.out.println("Inserting Data");
            insertData(con, "Java for OOP", "Unkown", 10);
            insertData(con, "Web Dev in Python", "Unkown", 23);
            insertData(con, "Electronic Devices Circuit", "Unkown", 13);
            insertData(con, "Managment Sciences", "Unkown", 12);
            System.out.println();
            System.out.println("Displaying the books");
            displayDatabase(con,"Books");

    } catch(SQLException e){
            e.printStackTrace();
            System.out.println(e.getClass().getName()+":"+e.getMessage());

    } 
    finally{
        if (con != null){
            try{
                con.close();
            } catch(SQLException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    }
    public static void  deleteTable(Connection con) throws SQLException{
        String deleteT = "DROP TABLE Books";
        PreparedStatement stmt = con.prepareStatement(deleteT);
        stmt.execute();
        
    }
    public static void createTable(Connection con)throws SQLException{
        String createT = ""+
                        "CREATE TABLE Books"+
                        "("+
                        "bookname varchar(255), "+
                        "author varchar(255), "+
                        "isn integer"+
                        ");"+
                        "";
        PreparedStatement stmt = con.prepareStatement(createT);
        stmt.execute();

    }
    public static void insertData(Connection con, String bookName,String author,int isn) throws SQLException{
        String inset = "INSERT INTO Books(bookname,author,isn) VALUES(?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(inset);
        pstmt.setString(1, bookName);
        pstmt.setString(2, author);
        pstmt.setInt(3, isn);
        pstmt.executeUpdate();

    }
    public static void displayDatabase(Connection con, String table) throws SQLException {
        String select = "SELECT * FROM " + table;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(select);
    
        while (rs.next()) {
            System.out.print("Books\t" + rs.getString("bookname") + ",\t");
            System.out.print("Author\t" + rs.getString("author") + ",\t");
            System.out.print("ISN\t" + rs.getString("isn") + ",\n");
        }
    }

    public static void searchBooks(Connection con, String searchQuery) throws SQLException {
        String select = "SELECT * FROM Books WHERE bookname LIKE ? OR author LIKE ? OR isn LIKE ?";
        PreparedStatement pstmt = con.prepareStatement(select);
        pstmt.setString(1, "%" + searchQuery + "%");
        pstmt.setString(2, "%" + searchQuery + "%");
        pstmt.setString(3, "%" + searchQuery + "%");
        ResultSet rs = pstmt.executeQuery();
    
        while (rs.next()) {
            System.out.print("Books\t" + rs.getString("bookname") + ",\t");
            System.out.print("Author\t" + rs.getString("author") + ",\t");
            System.out.print("ISN\t" + rs.getString("isn") + ",\n");
        }
    }
    
    
    
}