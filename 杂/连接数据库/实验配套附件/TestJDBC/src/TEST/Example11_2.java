import java.sql.*; 
public class Example11_2 {
   public static void main(String args[]) {
      Connection con;
      Statement sql; 
      ResultSet rs;
      con = GetDBConnection.connectDB("教学库","root","123");
      if(con == null ) return;
      try { 
          sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_READ_ONLY);
          rs = sql.executeQuery("SELECT * FROM student ");
          rs.last();
          int max = rs.getRow();
          System.out.println("表共有"+max+"条记录,随机抽取2条记录：");
          int [] a =GetRandomNumber.getRandomNumber(max,2);//得到1-max之间2个不同随机数
          for(int i:a){
             rs.absolute(i);//游标移动到第i行
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
