import java.sql.*;     // Use classes in java.sql package
import java.util.*;
import java.text.*;
 
public class JdbcInsertTest {    // Save as "JdbcUpdateTest.java"
   public static void Insert(String firstname, String lastname, String deptname, String birthdate, String gender, int salary, Connection conn)throws Exception {
      try{
         
         Statement stmt = conn.createStatement();
         
         // Setting the from_date to today for hire dates and from dates
         String from_date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
         
         // Find highest emp_no and add 1 to it
         String sqlSelect = "SELECT MAX(emp_no) FROM dept_emp";
         ResultSet max = stmt.executeQuery(sqlSelect);
         max.next();
         String str = max.getString("MAX(emp_no)");
         int emp_no = Integer.parseInt(str);
         emp_no+=1;
         
         // INSERT a record in employees table
         String sqlInsert = "insert into employees(emp_no,first_name, last_name, birth_date, gender, hire_date) values (" + "\'" + emp_no +"\', " + "\'" 
         + firstname + "\'" + ", " + "\'" + lastname + "\'" + ", " + "\'" 
         + birthdate + "\'" + ", " + "\'" + gender + "\', " + "\'" + from_date + "\'" + ")";
         System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
         stmt.executeUpdate(sqlInsert);
         
         // INSERT a record in the salary table
         String sqlInsert2 = "INSERT INTO salaries(emp_no, salary, from_date, to_date) VALUES (" + "\'" + emp_no
         + "\', " + "\'" + salary + "\', " + "\'" + from_date + "\', " + "\'9999-01-01\')";
         System.out.println("The SQL statement is: " + sqlInsert2 + "\n");  // Echo for debugging
         stmt.executeUpdate(sqlInsert2);
         
         // SELECT the department name of the correct department
         String sqlSelect2 = "SELECT dept_no FROM departments WHERE dept_name= " + "\'" + deptname + "\'";
         ResultSet dept_no = stmt.executeQuery(sqlSelect2);
         dept_no.next();
         String dept = dept_no.getString(1);
         
         // INSERT a record into the dept_emp table
         String sqlInsert3 = "INSERT INTO dept_emp(emp_no, dept_no, from_date, to_date) VALUES (" + "\'" + emp_no 
         + "\', " + "\'" + dept + "\', " + "\'" + from_date + "\', " + "\'" + "9999-01-01" + "\'" + ")";
         System.out.println("The SQL statement is: " + sqlInsert3 + "\n");  // Echo for debugging
         stmt.executeUpdate(sqlInsert3);
         
         //Print the final output message
         System.out.println("Employee " + firstname + " " + lastname + " added!");
         
       }catch (SQLException ex){
         ex.printStackTrace();
       }finally {
         try { conn.close(); } catch (Exception e) { /* ignored */ }//close the connection
      }
   }
}