import java.sql.*;  
 
public class JdbcSelectTest {   
   public static void Select(String dept_name, Connection conn) {
      try{
          
         Statement stmt = conn.createStatement();
         
         //SELECT query
         String strSelect = "select employees.emp_no, first_name, last_name from employees natural join departments, dept_emp where departments.dept_no = dept_emp.dept_no and dept_emp.emp_no = employees.emp_no and departments.dept_name = " + "\"" + dept_name + "\"";
         System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
 
         ResultSet rset = stmt.executeQuery(strSelect);
 
         //This prints all the results of the select statement
         System.out.println("The records selected are:");
         int rowCount = 0;
         while(rset.next()) {   // Move the cursor to the next row, return false if no more row
            int emp_no = rset.getInt("emp_no");
            String firstname = rset.getString("first_name");
            String lastname = rset.getString("last_name");
            System.out.println( emp_no + "   " + firstname + "   " + lastname);
            ++rowCount;
         }
         System.out.println("Total number of records = " + rowCount);
      }catch (SQLException ex){
         ex.printStackTrace();
      }finally {
         try { conn.close(); } catch (Exception e) { /* ignored */ } //close the connection
      }
   }
}