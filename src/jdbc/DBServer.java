package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBServer {
    private static String dbUrl = "jdbc:mysql://localhost:3306/stu?useSSL=false";
    private static String dbUser = "root";
    private static String dbPassword = "root";

    public static void setConnectionInfo(String url, String user, String password) {
        dbUrl = url;
        dbUser = user;
        dbPassword = password;
    }

    public static void signUp(String username, String password, String sex) {
        if (!(sex.equals("M") || sex.equals("W"))) {
            System.out.println(">> 性别输入格式错误！");
            return;
        }

        getSignUp(username, password, sex);
    }

    public static void signIn(String username, String password) {
        if (username.length() == 0 || password.length() == 0) {
            System.out.println(">> 用户名或密码为空！");
            return;
        }

        getSignIn(username, password);
    }

    private static void getSignUp(String username, String password, String sex) {
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            if (con == null) {
                System.err.println("--- MySQL connection failed! ---");
                return;
            }

            String ssql = "SELECT username FROM users WHERE username=?";
            Object[] sobjects = { username };
            try (PreparedStatement sps = con.prepareStatement(ssql)) {
                for (int i = 0; i < sobjects.length; i++) {
                    sps.setObject(i + 1, sobjects[i]);
                }

                try (ResultSet srs = sps.executeQuery()) {
                    if (srs.next()) {
                        System.out.println(">> 用户已存在！注册失败！");
                        return;
                    } else {
                        String isql = "INSERT INTO users VALUES (?,?,?)";
                        Object[] iobjects = { username, password, sex };
                        int count = 0;
                        try (PreparedStatement ips = con.prepareStatement(isql)) {
                            con.setAutoCommit(false);
                            for (int i = 0; i < iobjects.length; i++) {
                                ips.setObject(i + 1, iobjects[i]);
                            }
                            count = ips.executeUpdate();
                            con.commit();
                        } catch (SQLException e) {
                            con.rollback();
                        }

                        if (count > 0) {
                            System.out.println(">> 注册成功！");
                        } else {
                            System.out.println(">> 注册失败！");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getSignIn(String username, String password) {
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            if (con == null) {
                System.err.println("--- MySQL connection failed! ---");
                return;
            }

            String ssql = "SELECT * FROM users WHERE username=? AND password=?";
            Object[] sobjects = { username, password };
            try (PreparedStatement sps = con.prepareStatement(ssql)) {
                for (int i = 0; i < sobjects.length; i++) {
                    sps.setObject(i + 1, sobjects[i]);
                }

                try (ResultSet srs = sps.executeQuery()) {
                    if (srs.next()) {
                        System.out.println(">> 登陆成功！");
                    } else {
                        System.out.println(">> 登录失败！请检查用户名或密码！");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showAllInfoWhereSex(String sex) {
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            if (con == null) {
                System.err.println("--- MySQL connection failed! ---");
                return;
            }

            String ssql = "SELECT * FROM users WHERE sex=?";
            Object[] sobjects = { sex };
            try (PreparedStatement sps = con.prepareStatement(ssql)) {
                for (int i = 0; i < sobjects.length; i++) {
                    sps.setObject(i + 1, sobjects[i]);
                }

                try (ResultSet srs = sps.executeQuery()) {
                    String choiceSex = sex.equals("M") ? "男" : "女";
                    System.out.println(">> 输出所有" + choiceSex + "生信息：");
                    while (srs.next()) {
                        String userRes = srs.getString("username");
                        String passwordRes = srs.getString("password");
                        System.out.println(">> 用户名:" + userRes + ",密码:" + passwordRes);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
