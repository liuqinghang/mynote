import java.sql.*; 
public class Example11_1 {
   public static void main(String args[]) {
      Connection con=null;
      Statement sql; 
      ResultSet rs;
      try{  Class.forName("com.mysql.jdbc.Driver"); //加载JDBC_MySQL驱动
      }
      catch(Exception e){}
      String uri = "jdbc:mysql://localhost:3306/教学库?useSSL=true";
      String user ="root";
      String password ="123";
      try{  
         con = DriverManager.getConnection(uri,user,password); //连接代码
      }
      catch(SQLException e){ }
      try { 
          sql=con.createStatement();
          rs=sql.executeQuery("SELECT * FROM student"); //查询student表
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
