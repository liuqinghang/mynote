import java.sql.*; 
public class Example11_4 {
   public static void main(String args[]) {
      Connection con;
      Statement sql; 
      ResultSet rs;
      con = GetDBConnection.connectDB("教学库","root","123");
      if(con == null ) return;
      String jiLu="('201800101','王三','男',19, '软件工程系'),"+
                  "('201800102','王珊','女',20, '计算机科学系')";    //2条记录
      String sqlStr ="insert into student values "+jiLu;
      try { 
          sql=con.createStatement(); 
          int ok = sql.executeUpdate(sqlStr);
          rs = sql.executeQuery("select * from student");
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
         System.out.println("记录中sno值不能重复"+e);
      }
  }
}
