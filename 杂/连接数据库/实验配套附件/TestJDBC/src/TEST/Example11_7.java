import java.sql.*; 
public class Example11_7{
    public static void main(String args[]){
       Connection con = null;
       Statement sql;
       ResultSet rs; 
       String sqlStr;
       con = GetDBConnection.connectDB("教学库","root","123");
       if(con == null ) return;
       try{ int n = 2;
            con.setAutoCommit(false);       //关闭自动提交模式
            sql = con.createStatement();
            sqlStr = "select sname,sage from student where sno='A001'";
            rs = sql.executeQuery(sqlStr);
            rs.next();
            int h1 = rs.getInt(2);
            System.out.println("事务之前"+rs.getString(1)+"年龄:"+h1);
            sqlStr = "select sname,sage from student where sno='A002'"; 
            rs = sql.executeQuery(sqlStr);
            rs.next();
            int h2 = rs.getInt(2);
            System.out.println("事务之前"+rs.getString(1)+"年龄:"+h2);  
            h1 = h1-n;
            h2 = h2+n;
            sqlStr = "update student set sage ="+h1+" where sno='A001'";
            sql.executeUpdate(sqlStr);
            sqlStr = "update student set sage ="+h2+" where sno='A002'";
            sql.executeUpdate(sqlStr);
            con.commit(); //开始事务处理,如果发生异常直接执行catch块
            con.setAutoCommit(true); //恢复自动提交模式
            String s = "select sname,sage from student"+
                      " where sno='A001' or sno='A002'";
            rs = 
            sql.executeQuery(s);
            while(rs.next()){
               System.out.println("事务后"+rs.getString(1)+
                                  "年龄:"+rs.getFloat(2));  
            }
            con.close();
         }
         catch(SQLException e){
            try{ con.rollback();          //撤销事务所做的操作
            }
            catch(SQLException exp){}
         }
    }
}
