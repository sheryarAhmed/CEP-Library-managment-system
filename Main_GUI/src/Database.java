import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Database {
    private Connection con = null;
    private String url = "jdbc:sqlite:db/";
    private String tableName = "";
    


    public Database(String filename, String tableName){
        this.tableName = tableName;
        createDatabase(filename);
    }
    
    private void createDatabase(String fileName){
        url = url+fileName;
        try {
            con = DriverManager.getConnection(url);

        }catch(Exception e){
           System.out.println("There is a problem during creation of Database");
           System.out.println(e.getMessage());
        } 
    }

    public void createTable(String[] columnNames, String[] columnTypes){
        if (con == null)
            System.out.println("Please Create Database First");
        if (columnNames.length == columnTypes.length){

            String createT = "CREATE TABLE " + tableName + " (";
        for(int i=0; i<columnNames.length; i++){
            createT += columnNames[i] + " " + columnTypes[i] + ",";
        }
        createT = createT.substring(0, createT.length()-1); // Remove the last comma
        createT += ");";
        
        try {
            Statement stmt = con.createStatement();
            stmt.execute(createT);
        } catch(Exception e){
            System.out.println("Table already exists. Please delete the db");
            System.out.println(e.getMessage());
        } 

        } else 
            System.out.println("Make sure the length of columnsName and columnsTypes must equal");

           
    }
    
    
    public String toString(){
        String text = "";
        String select = "SELECT * FROM " + tableName;
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(select);
            while (rs.next()) {
                for(int i=1; i<=rs.getMetaData().getColumnCount(); i++){
                    text += (rs.getMetaData().getColumnName(i) + ":" + rs.getString(i) + "\t");
                }
                text = text+"\n";
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return text;
        }
        return text.substring(0,text.length()-1);
    }

    public void insertData(String[] values){
        String check = values[0];
        if (dataExist(check)){
            String insert = "INSERT INTO " + tableName + " VALUES(";
            for(int i=0; i<values.length; i++){
                insert += "?,";
            }
            insert = insert.substring(0, insert.length()-1); // Remove the last comma
            insert += ")";
            
            try {
                PreparedStatement psst = con.prepareStatement(insert);
                for(int i=0; i<values.length; i++){
                    psst.setString(i+1, values[i]);
                }
                psst.executeUpdate();
            } catch (SQLException e){
                System.out.println(e.getLocalizedMessage());
            }

         }else
            System.out.println("Already have");

        
    }

    public void deleteTable(String tableName) {
        if (con == null) {
            System.out.println("Please Create Database First");
            return;
        }
        try {
            String deleteT = "DROP TABLE " + tableName;
            PreparedStatement stmt = con.prepareStatement(deleteT);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(tableName + " table has been deleted.");
    }
    
    public boolean removeColumn(String query) {
        Boolean result = false;
        try {
            DatabaseMetaData metaData = con.getMetaData();
            ResultSet columnResultSet = metaData.getColumns(null, null, tableName, null);
            
            String text = "DELETE FROM " + tableName + " WHERE "+columnResultSet.getString("COLUMN_NAME")+" = '" + query+"';";            
            PreparedStatement pst = con.prepareStatement(text);
            pst.execute();
            result = true;

            
            
        } catch (Exception e) {
            System.out.printf("Error in code:\n%s", e.getLocalizedMessage());
            return result;
        }
        return result;
    }
    

    public String searchTable(String query){
        String returnString = " ";
        try{
            DatabaseMetaData mBaseData = con.getMetaData();
            ResultSet rs = mBaseData.getColumns(null, null, tableName, null);
            String colNames = "";
            while(rs.next()){
                colNames += " "+rs.getString("COLUMN_NAME");
            }
            String text = "SELECT * FROM "+tableName+" WHERE ";
            Scanner colExtract = new Scanner(colNames);
            int size = 0;
            while(colExtract.hasNext()){
                text += colExtract.next()+ " LIKE '%"+query+"%' OR "; 
                size++;
            }
            colExtract.close();
            text = text.substring(0, text.length()-3)+";";

            PreparedStatement pst = con.prepareStatement(text);
            ResultSet sr = pst.executeQuery();
            Scanner printColName = new Scanner(colNames);
            while(sr.next()){
                for(int i = 1;i<=size;i++)
                    returnString += String.format("%-10s ",sr.getString(i));
                returnString +="\n";
            }
            printColName.close();

        }catch (Exception e){
            
            System.out.println(e.getLocalizedMessage());
            return returnString;
            
        }
        return returnString.substring(0, returnString.length()-1);

    }

    public void updateData(String [] query){
        String check = query[0];
        if (!dataExist(check)){
            removeColumn(check);
            insertData(query);
        }else
            System.out.println("insert data before update");
    }

    private boolean dataExist(String query){
        Boolean check = false;
        String text = "SELECT * FROM "+tableName+ " WHERE ";
        try{
            DatabaseMetaData metaData = con.getMetaData();
            ResultSet rs= metaData.getColumns(null, null, tableName, null);
            text += rs.getString("COLUMN_NAME")+"='"+query+"';";
            PreparedStatement pst = con.prepareStatement(text);
            ResultSet ch =  pst.executeQuery();
            if(!ch.next())
                check = true;
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
            return check;
        }
        return check;
    }
    private String convertHash(String password){
        String code = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte [] bt = md.digest();
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bt.length; i++) {
                sb.append(Integer.toString((bt[i] & 0xff) + 0x100, 16).substring(1));
            }

            code = sb.toString();

        }catch(Exception e){
            System.out.println(e.getLocalizedMessage());
            return code;
        }
        return code;
    }

    public String getHash(String password){
        return convertHash(password);
    }
}
