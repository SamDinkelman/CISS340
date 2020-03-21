import java.sql.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;

// To run please use java -cp .:/path/to/mysqlconnector/jar/files main <enter commands here>

public class main {
   public static void main(String[] args)throws Exception{  
      
      // Reads in the credentials, just the username and password, from a file
      // Change path to add your credentials
      // Please store username on the first line and password on the second
     
      BufferedReader in = new BufferedReader(new FileReader("/home/workstation/Desktop/assignment3/credentials.txt"));
      String str;
      List<String> list = new ArrayList<String>();
      while((str = in.readLine()) != null){
          list.add(str);
      }  
      String[] stringArr = list.toArray(new String[0]);
      
      // Starts the connection and later hands it off to each function 
      try(
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC" ,stringArr[0],stringArr[1]);
         
      ){
         // Shows all the employees in the department
         if (args[0].equals("show") && args[1].equals("employees") && args[2].equals("department")){
            String deptname = args[3];
            JdbcSelectTest select = new JdbcSelectTest();
            select.Select(deptname, conn);
         
         // Adds an employee to the database  
         }else if(args[0].equals("add") && args[1].equals("employee")){
            String firstname = args[2];
            String lastname = args[3];
            String deptname = args[4];
            String birthdate = args[5];
            String gender = args[6];
            int salary = Integer.parseInt(args[7]);
            JdbcInsertTest add = new JdbcInsertTest();
            add.Insert(firstname, lastname, deptname, birthdate, gender, salary, conn);
         
         // Deletes an employee from the database
         }else if(args[0].equals("delete") && args[1].equals("employee")){ 
            int empid = Integer.parseInt(args[2]);
            JdbcDeleteTest del = new JdbcDeleteTest();
            del.delete(empid, conn);
         
         // Displays the sum of all current salaries
         }else if(args[0].equals("show") && args[1].equals("salaries") && args[2].equals("sum")){
            JdbcSalaryTest sal = new JdbcSalaryTest();
            sal.Salary(conn);
         
         // Prints a usage statement if any other input is entered
         }else{
               System.out.println("Please enter one of the following options:");
               System.out.println("    java main show employees department <department name>");
               System.out.println("    java main add employee <first_name> <last_name> <dept_name> <birthdate> <gender> <salary>");
               System.out.println("    java main delete employee <employee_ID>");
               System.out.println("    java main show salaries sum");
            }
      }catch (SQLException ex){
         System.out.println("Please enter one of the following options:");
         System.out.println("    java main show employees department <department name>");
         System.out.println("    java main add employee <first_name> <last_name> <dept_name> <birthdate> <gender> <salary>");
         System.out.println("    java main delete employee <employee_ID>");
         System.out.println("    java main show salaries sum");
         ex.printStackTrace();
      }
   }
}