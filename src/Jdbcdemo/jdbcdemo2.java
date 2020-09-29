package Jdbcdemo;

import java.sql.SQLException;
import java.util.Scanner;

public class jdbcdemo2 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username= sc.nextLine();
        System.out.println("请输入密码：");
        String password= sc.nextLine();
        try {
            boolean flag= new jdbcdemo1().login(username,password);
            if(flag){
                //登录成功
                System.out.println("登录成功！");
            }else{
                System.out.println("用户名或密码错误！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
