package com.itheima;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ATM系统的入口类
 */
public class ATMSystem {
    public static void main(String[] args) {
        //1.定义账户类
        //2.定义一个集合容器，负责以后存储全部的账户对象，进行相关的业务操作
        ArrayList<Account> accounts = new ArrayList<>();
        //3.展示系统的首页
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===========黑马ATM系统===========");
            System.out.println("1.账户登录");
            System.out.println("2.账户开户");
            System.out.println("请选择操作：");
            int command = sc.nextInt();
            switch (command){
                case 1:
                    //用户登录
                    break;
                case 2:
                    //用户注册
                    register(accounts, sc);
                    break;
                default:
                    System.out.println("您输入的命令操作不存在");
            }
        }


    }

    /**
     * 用户开户功能的实现
     * @param accounts  接受账户的集合
     */
    private static void register(ArrayList<Account> accounts, Scanner sc) {
        System.out.println("=======系统开户操作=======");
        //1.创建一个账户对象，用于后期封装账户信息
        Account account = new Account();
        //2.录入当前这个账户的信息，注入到账户对象中去
        System.out.println("请您输入账户用户名：");
        String userName = sc.next();
        account.setUserName(userName);

        while (true) {
            System.out.println("请您输入账户密码：");
            String passWord = sc.next();
            System.out.println("请您再次输入账户密码：");
            String okPassWord = sc.next();
            if (okPassWord.equals(passWord)){
                System.out.println("密码设置成功！");
                account.setPassWord(okPassWord);
                break;  //密码录入成功，结束死循环
            }else {
                System.out.println("两次密码不一致，请重新输入：");
            }
        }
        System.out.println("请您输入当次限额：");
        double quotaMoney = sc.nextDouble();
        account.setQuotaMoney(quotaMoney);

        // 为账户随机一个8位且与其他账户不同的卡号！！！




        //3.把账户对象添加到账户集合中去




























    }
}
