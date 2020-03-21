import java.sql.*;  
 
public class JdbcSalaryTest {   
   public static void Salary(Connection conn){
      try{
         
         // Create a connection
         Statement stmt = conn.createStatement();
         
         // Selects the Sum of the most recent salaries
         String strSelect = "SELECT SUM(salary) FROM salaries WHERE to_date = \'9999-01-01\'";
         System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
         
        //Print the results of the Query
         ResultSet num = stmt.executeQuery(strSelect);
         num.next();
         String sum = num.getString(1);
         System.out.println("$" + sum);
         
       }catch (SQLException ex){
         ex.printStackTrace();
       }finally {
         try { conn.close(); } catch (Exception e) { /* ignored */ } //close the connection
      }
   }
}