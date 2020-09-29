package Jdbcdemo;

import java.sql.*;

public class jdbcdemo1 {
    static Connection con=null;
    static PreparedStatement pst=null;
    static ResultSet rs=null;
    public boolean login(String username,String password) throws SQLException {
        if (username==null || password==null) {
            return false;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con= DriverManager.getConnection("jdbc:mysql:///stu","root","root");
            String sql="select * from user where username=? and password=?";
             pst=con.prepareStatement(sql);
             pst.setString(1,username);
             pst.setString(2,password);
              rs= pst.executeQuery();
             if(rs.next()){
                 return true;
             }else return false;



        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (con!=null)
                con.close();
            if (pst!=null)
                pst.close();
            if (rs!=null)
                rs.close();

        }


        return false;

    }

}
