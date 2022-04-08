package com.xiaoyingge.algorithm.part21;

/**
 * @author XiaoYingGee
 * @date 2022/3/18 21:42
 */
public class Question2043 {

    static class Bank {

        private long[] balances;

        public Bank(long[] balance) {
            balances = new long[balance.length];
            for (int i = 0; i < balance.length; i++) {
                balances[i] = balance[i];
            }
        }

        public boolean transfer(int account1, int account2, long money) {
            if (account1 > balances.length || balances[account1 - 1] < money || account2 > balances.length) {
                return false;
            }
            balances[account1 - 1] = balances[account1 - 1] - money;
            balances[account2 - 1] = balances[account2 - 1] + money;
            return true;
        }

        public boolean deposit(int account, long money) {
            if (account > balances.length) {
                return false;
            }
            balances[account - 1] = balances[account - 1] + money;
            return true;
        }

        public boolean withdraw(int account, long money) {
            if (account > balances.length || balances[account - 1] < money) {
                return false;
            }
            balances[account - 1] = balances[account - 1] - money;
            return true;

        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank(new long[]{10L, 100L, 20L, 50L, 30L});
        System.out.println(bank.withdraw(3, 10));
        System.out.println(bank.transfer(5, 1, 20));
        System.out.println(bank.deposit(5, 20));
        System.out.println(bank.transfer(3, 4, 15));
        System.out.println(bank.withdraw(10, 50));
    }

}
