import java.sql.*; 
public class Example11_3 {
   public static void main(String args[]) {
      Connection con;
      Statement sql; 
      ResultSet rs;
      con = GetDBConnection.connectDB("教学库","root","123");
      if(con == null ) return;
      String c1=" ssex='女' and sage>22";//条件1
      String c2=" sname Like '__%'";  //条件2    
      String sqlStr =
      "select * from student where "+c1+" and "+c2+" order by sno";
      try { 
          sql=con.createStatement();
          rs = sql.executeQuery(sqlStr);
          while(rs.next()) { 
              String sno=rs.getString(1);
              String sname=rs.getString(2);
              String ssex=rs.getString(3);
              int sage=rs.getInt(4);
              String sdept=rs.getString(5);
              System.out.printf("%s\t",sno);
              System.out.printf("%s\t",sname);
              System.out.printf("%s\t",ssex); 
              System.out.printf("%d\t",sage);
              System.out.printf("%s\n",sdept);
          }
          con.close();
      }
      catch(SQLException e) { 
         System.out.println(e);
      }
  }
}
