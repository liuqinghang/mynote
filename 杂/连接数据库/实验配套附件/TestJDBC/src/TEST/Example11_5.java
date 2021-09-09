import java.sql.*; 
public class Example11_5 {
   public static void main(String args[]) {
      Connection con;
      PreparedStatement preSql;  //Ԥ����������preSql
      ResultSet rs;
      con = GetDBConnection.connectDB("��ѧ��","root","123");
      if(con == null ) return;
      String sqlStr ="insert into student values(?,?,?,?,?)";
      try { 
          preSql = con.prepareStatement(sqlStr);//�õ�Ԥ����������preSql
          preSql.setString(1,"A002");       //���õ�1��?�����ֵ
          preSql.setString(2,"��ΰ1");       //���õ�2��?�����ֵ
          preSql.setString(3,"��"); //���õ�3��?�����ֵ
          preSql.setFloat(4,20);        //���õ�4��?�����ֵ   
          preSql.setString(5,"ͨ��ϵ");        //���õ�5��?�����ֵ
          int ok = preSql.executeUpdate();
          sqlStr="select * from student where sname like ? ";
          preSql = con.prepareStatement(sqlStr);//�õ�Ԥ����������preSql
          preSql.setString(1,"��%");       //���õ�1��?�����ֵ
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
         System.out.println("��¼��snoֵ�����ظ�"+e);
      }
  }
}
