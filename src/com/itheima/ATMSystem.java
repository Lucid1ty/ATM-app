package com.itheima;

import java.util.ArrayList;
import java.util.Random;
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
                    login(accounts,sc);
                    break;
                case 2:
                    //用户账户开户
                    register(accounts, sc);
                    break;
                default:
                    System.out.println("您输入的命令操作不存在");
            }
        }


    }

    /**
     * 登录功能
     * @param accounts 全部账户对象的集合
     * @param sc 扫描器
     */
    private static void login(ArrayList<Account> accounts, Scanner sc) {
        System.out.println("=======系统登录操作=======");
        //1.判断账户集合中是否存在账户,如果不存在,登录功能不能进行。
        if(accounts.size() == 0){
            System.out.println("当前系统中无任何账户,请先开户,再来登录~~~");
            return; //结束方法执行
        }

        //2.进入登录操作
        while (true) {
            System.out.println("请您输入登录卡号：");
            String cardID = sc.next();
            //3.判断卡号是否存在：根据卡号去账户集合中查询账户对象。
            Account acc = getAccountByCarID(cardID, accounts);
            if (acc != null){
                while (true) {
                    //卡号存在!
                    //4.让用户输入密码,并且判断密码是否正确
                    System.out.println("请您输入登录密码：");
                    String passWord = sc.next();
                    //判断密码是否正确
                    if(acc.getPassWord().equals(passWord)){
                        //密码正确
                        System.out.println("登录成功！" + acc.getUserName() + "先生/女士,您的卡号是：" + acc.getCardId());
                        //查询 转账 存款 取款
                    }else{
                        //密码错误
                        System.out.println("密码错误！");
                    }
                }
            }else{
                // 卡号不存在！
                System.out.println("对不起,您输入的卡号不存在！");
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
        account.setUserName(userName);  //设置用户名
        //下面开始设置用户密码
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
        //为账户设置额度
        System.out.println("请您输入当次限额：");
        double quotaMoney = sc.nextDouble();
        account.setQuotaMoney(quotaMoney);

        // 为账户随机一个8位且与其他账户不同的卡号！！！(独立功能,独立成方法)
        String cardID = getRandomCardID(accounts);
        account.setCardId(cardID);
        //3.把账户对象添加到账户集合中去
        accounts.add(account);
        System.out.println("恭喜您：" + userName + "先生," + "您开户成功，您的卡号是：" + cardID + ",请妥善保管！");
    }

    /**
     * 为账户生成8位与其他卡号不同的数字
     * @return  cardID
     */
    private static String getRandomCardID(ArrayList<Account> accounts) {
        Random r = new Random();
        while (true) {
            //1.先生成8位数字
            String cardID = "";
            for (int i = 0; i < 8; i++) {
                cardID += r.nextInt(10);
            }
            //2.判断这个8位的卡号是否与其他用户重复了
            //根据这个卡号去查询账户的对象
            Account acc = getAccountByCarID(cardID, accounts);
            if (acc == null){
                //说明此时cardID没有重复,可以使用这个新卡号了
                return cardID;
            }
        }
    }


    /**
     * 根据卡号查询出一个账户信息出来
     * @param cardID    卡号
     * @param accounts  全部账户的信息
     * @return  账户对象 | null
     */
    private static Account getAccountByCarID(String cardID, ArrayList<Account> accounts){
        //循环遍历所有用户
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);  //当前账户
            //如果当前的账户的卡号和我要找的卡号相同
            if (acc.getCardId().equals(cardID)){
                return acc;
            }
        }
        return null;//没有找到这个账号
    }


}
