import java.sql.*;
public class DemoJdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException{

        /*
            import the package - imported sql.* from java
            load and register - driver Class.forName("org.postgresql.Driver") // optional step
            create connection - con object// connecting db to application
            create a statement - statement st
            execute statement - rs.next() check
            process the results - conn.close();
            close
         */

        String url ="jdbc:postgresql://localhost:5433/Demo";
        // demo is the db name and we gotta mention which network the db is available port number and ip address
        // 5342 and 3306 default for postgres and mysql
        String userName="postgres"; // can be configured
        String password="0626";

        /*
            first query
        String query = "select sname from student where sid=1";

            second query
        String query = "select * from student";
             third query
        String query ="insert into student values(5,87,'studentFive')";

              fourth query
         String query ="update student set marks=98 where sid=5";

              fifth query
          String query ="delete from student where sid=5";
              sixth query
          String query ="insert into student values(" + sid + " , " + marks + " , '" + sname + "' )";
         */


        // lets say the data is from UI
        int sid =7;
        String sname ="studentSix";
        int marks=88;

        String query ="insert into student values(?,?,?)";
        Class.forName("org.postgresql.Driver"); // throws exception

        Connection con = DriverManager.getConnection(url, userName, password);  // Driveranager utility class has methid getConnection
        // Connection is an interface // throws SQLexception
        // mySQL postgresql are network dbs have port nums etc
        // create a reference object for Statement
        //Statement is an interface


        // Statement st = con.createStatement();

        // to exceute store procedure use callable statement

        // lets use Prepared Statement

        PreparedStatement st = con.prepareStatement(query);
        // takes the query parameter here unlike Statement interface
        // gives pre compiled query can be used for caching
        st.setInt(1, sid);
        st.setInt(2, marks);
        st.setString(3,sname);

        // we got the statement
        // ResultSet rs = st.executeQuery(query); // use executeQuery to only fetch the data
        // ResultSet is an interface
        // did we get any response from db
        //use next() method it gives boolean
        // if there is a next row i.e. there is data it will give boolean true or false

        // Boolean status = st.execute(query); // use execute for insert etc CRUD: CUD // execute returns boolean if the query was successfully exceuted
        Boolean status = st.execute(); // mentioned the query in te st object creation
        System.out.println(status);
        // returns false: only returns true if returns a result set object but execute is returning count.
        // for select query returns result set object
        //ResultSet rs pointer is before the first record. So use rs.next()
        // rs.next();

        // use while(rs.next) to loops through all rows to get the records

//         for fetching
        System.out.println("Connected to PostgreSQL database");
        // System.out.println(rs.next()); // got true


        // String name = rs.getString("sname"); // using sname to get the columns name to get the name of user
        //or
        // rs.getArray(1)''
        // System.out.println(name);
        con.close(); //closing the connection
    }
}
