import java.sql.*; 
public class Example11_5 {
   public static void main(String args[]) {
      Connection con;
      PreparedStatement preSql;  //预处理语句对象preSql
      ResultSet rs;
      con = GetDBConnection.connectDB("教学库","root","123");
      if(con == null ) return;
      String sqlStr ="insert into student values(?,?,?,?,?)";
      try { 
          preSql = con.prepareStatement(sqlStr);//得到预处理语句对象preSql
          preSql.setString(1,"A002");       //设置第1个?代表的值
          preSql.setString(2,"刘伟1");       //设置第2个?代表的值
          preSql.setString(3,"男"); //设置第3个?代表的值
          preSql.setFloat(4,20);        //设置第4个?代表的值   
          preSql.setString(5,"通信系");        //设置第5个?代表的值
          int ok = preSql.executeUpdate();
          sqlStr="select * from student where sname like ? ";
          preSql = con.prepareStatement(sqlStr);//得到预处理语句对象preSql
          preSql.setString(1,"刘%");       //设置第1个?代表的值
          rs = preSql.executeQuery();
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
