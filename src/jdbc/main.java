package jdbc;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String username;
        String password;
        String sex;
        System.out.print("?> 请输入要注册的用户名：");
        username = sc.nextLine();
        System.out.print("?> 请输入密码：");
        password = sc.nextLine();
        System.out.print("?> 请输入性别(男:M, 女:W)：");
        sex = sc.nextLine();
        DBServer.signUp(username, password, sex);
        System.out.print("?> 请输入要登录的用户名：");
        username = sc.nextLine();
        System.out.print("?> 请输入密码：");
        password = sc.nextLine();
        DBServer.signIn(username, password);
        DBServer.showAllInfoWhereSex("M");
        sc.close();
    }
}
