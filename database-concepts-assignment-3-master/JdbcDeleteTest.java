import java.sql.*;     // Use classes in java.sql package
 
public class JdbcDeleteTest {
   public static void delete(int empid, Connection conn) {
      try{
         Statement stmt = conn.createStatement();
         
         // First we find the employee with the employee id then delete them
         String sqlSelect = "SELECT first_name, last_name FROM employees WHERE emp_no=" + empid;
         String sqlDelete = "DELETE FROM employees WHERE emp_no=" + empid;
         System.out.println("The SQL statement is: " + sqlDelete + "\n");  // Echo for debugging
         
         // Get results of select Query
         ResultSet rset = stmt.executeQuery(sqlSelect);
        
         // Print employee name and last name and then delete them
         try{
            if(sqlSelect != null){
               rset.next();
               String firstname = rset.getString("first_name");
               String lastname = rset.getString("last_name");
               System.out.println("Employee " + firstname + " " + lastname + " deleted!");
               int countDeleted = stmt.executeUpdate(sqlDelete);
            }
         }catch(SQLException ex) {
               System.out.println("Employee with id " + empid + " does not exist");
         }
      }catch(SQLException e){
         e.printStackTrace();
      }finally {
         try { conn.close(); } catch (Exception e) { /* ignored */ }//close the connection
      }
   }
}