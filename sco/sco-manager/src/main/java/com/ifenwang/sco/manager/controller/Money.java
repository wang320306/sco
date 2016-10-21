package com.ifenwang.sco.manager.controller;

import java.math.BigDecimal;

public class Money
{
    public static void main(String[] args)
    {
        BigDecimal money = new BigDecimal(450000);// 总金额
        BigDecimal month = new BigDecimal(360);// 总月数
        BigDecimal preMoney = money.divide(month);// 每月本金
        
        BigDecimal yearInterestRate = new BigDecimal(0.049);// 年利率
        BigDecimal monthInterestRate = yearInterestRate.multiply(new BigDecimal(0.85)).divide(new BigDecimal(12));// 月利率
        
        
        
        System.out.println("总金额：" + money.doubleValue());
        System.out.println("每月本金：" + preMoney.doubleValue());
        System.out.println("年利率：" + yearInterestRate.doubleValue());
        System.out.println("月利率：" + monthInterestRate.doubleValue());
        
        BigDecimal ealseMoney = null; // 已还本金
        BigDecimal preInterestMoney = null; // 当月利息
        System.out.println("期数\t总金额\t本金\t利息");
        for (int i = 0; i < 360; i++)
        {
            ealseMoney = new BigDecimal(i).multiply(preMoney);
            preInterestMoney = (money.subtract(ealseMoney)).multiply(monthInterestRate);
            System.out.println(
                    (i+1) + "\t" 
                    + (preInterestMoney.add(preMoney)).setScale(2, BigDecimal.ROUND_UP).doubleValue() + "\t" 
                    + preMoney.doubleValue() + "\t" 
                    + preInterestMoney.setScale(2, BigDecimal.ROUND_UP).doubleValue());
        }
        
        
        
        
        /*double money = 450000.00d;//总本金
        int month = 360;// 总月数
        double preMoney = money / month;//平均每月本金
        System.out.println("每月本金                ：" + preMoney);

        double yearInterestRate = 0.049;//年利率
        System.out.println("年利率                     ：" + (yearInterestRate * 0.85));
        double monthInterestRate = (yearInterestRate * 0.85 ) / 12;//月利率
        System.out.println("月利率                     ：" + monthInterestRate);
        System.out.println();
        
        System.out.println("期数\t总金额\t\t本金\t利息");
        
        double ealseMoney = 0d;
        double preInterestMoney = 0d;
        for (int i = 0; i < month; i++)
        {
            ealseMoney = i * preMoney;//已还本金
            
            preInterestMoney = (money - ealseMoney) * monthInterestRate;// 每月利息
            
            System.out.println(
            (i+1) + "\t" 
            + String.format("%.2f", (preMoney + preInterestMoney)) + "\t\t" 
            + String.format("%.2f", preMoney) + "\t" 
            + String.format("%.2f", preInterestMoney));
        }*/

    }

    /**
     * 计算两个数相乘
     * @param a 参数
     * @param b 参数
     * @return int类型
     */
    public static int multiply(String a, String b)
    {
        BigDecimal bd1 = new BigDecimal(a);
        BigDecimal bd2 = new BigDecimal(b);
        return bd1.multiply(bd2).intValue();
    }

    /**
     * 计算两个数相乘
     * @param a 参数
     * @param b 参数
     * @return float类型
     */
    public static float multiplyFloat(String a, String b)
    {
        BigDecimal bd1 = new BigDecimal(a);
        BigDecimal bd2 = new BigDecimal(b);
        return bd1.multiply(bd2).floatValue();
    }

    /**
     * 计算两个数相加
     * @param a 参数
     * @param b 参数
     * @return double类型数
     */
    public static double addDouble(String a, String b)
    {
        BigDecimal bd1 = new BigDecimal(a);
        BigDecimal bd2 = new BigDecimal(b);
        return bd1.add(bd2).doubleValue();
    }

    /**
     * 计算两个数相减
     * @param a 参数
     * @param b 参数
     * @return double类型数
     */
    public static double subtractDouble(String a, String b)
    {
        BigDecimal bd1 = new BigDecimal(a);
        BigDecimal bd2 = new BigDecimal(b);
        return bd1.subtract(bd2).doubleValue();
    }

}
