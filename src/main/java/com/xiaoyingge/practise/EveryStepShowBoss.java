package com.xiaoyingge.practise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

/**
 * <p>输入两个数组，一个是代表ID，一个代表进货或者退货，还有一个K，返回最大的中奖人K</p>
 * <p>1.要输出每一步的TOP K</p>
 * <p>2.如果获奖区不满，直接进获奖区，如果获奖区满了，进等候区</p>
 * <p>3.用户发生购买事件，则商品+1，发生退货则-1，，如果为0还减，则忽略该事件</p>
 * <p>4.只要用户商品数>0，则一定存在于得奖区或者等候区，如果减为0，则从获奖区和等候区删除他</p>
 * <p>5.得奖区只要人不满，新来的用户直接进得奖区</p>
 * <p>6.如果数量不足以进入得奖区，则进入等候区</p>
 * <p>7.如果等候区的商品大于获奖区最少的人，就可以替换他</p>
 * <p>8.等候区的人进入得奖区，需要挑选数量最多且时间最早的</p>
 * <p>9.被踢出得奖区的人是数量最少且最早进来的人</p>
 * <p></p>
 * <p></p>
 * <p></p>
 * <p></p>
 * <p></p>
 *
 * @author XiaoYingGee
 * @date 2022/3/22 22:00
 */
public class EveryStepShowBoss {

    // 为了测试
    public static class Data {

        public int[] arr;
        public boolean[] op;

        public Data(int[] a, boolean[] o) {
            arr = a;
            op = o;
        }
    }

    // 为了测试
    public static Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new Data(arr, op);
    }

    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort(Comparator.comparingInt(a -> a));
            cur2.sort(Comparator.comparingInt(a -> a));
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 100;
        int maxK = 6;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Data testData = randomData(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = compare(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + " , " + op[j]);
                }
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

    @lombok.Data
    @AllArgsConstructor
    public static class Customer {

        private int id;
        private int count;
        private int time;

        @Override
        public String toString() {
            return id + "," + count + "," + time;
        }
    }

    private static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        Map<Integer, Customer> prizeArea = new HashMap<>();
        Map<Integer, Customer> waitArea = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buy = op[i];
            //如果是个卖出动作且是个新节点，则忽略
            if (!buy && !prizeArea.containsKey(id) && !waitArea.containsKey(id)) {
                result.add(new ArrayList<>(prizeArea.keySet()));
                continue;
            }

            Customer current = prizeArea.get(id);
            if (current == null) {
                current = waitArea.getOrDefault(id, new Customer(id, 0, 0));
            }
            int count = current.getCount();
            current.setCount(buy ? ++count : --count);
            //如果减到0，就删掉它
            if (current.count <= 0) {
                //如果是在获取区有空，且减到0，需要从候选区取最大的来补位
                if (prizeArea.containsKey(id) && waitArea.size() > 0) {
                    List<Customer> wait = waitArea.values().stream().sorted(new WaitComparator()).collect(Collectors.toList());
                    Customer customer = wait.get(0);
                    customer.setTime(i);
                    prizeArea.put(customer.getId(), customer);
                    waitArea.remove(customer.getId());
                }
                prizeArea.remove(id);
                waitArea.remove(id);
                result.add(new ArrayList<>(prizeArea.keySet()));
                continue;
            }
            //到这里说明没有变成0的可能了
            //出现了一个新点，被删空重新出现也算新点
            if (!prizeArea.containsKey(id) && !waitArea.containsKey(id)) {
                current.setTime(i);
                //如果获奖区没满，直接进获奖区
                if (prizeArea.size() < k) {
                    prizeArea.put(id, current);
                } else {
                    //先扔进等候区
                    waitArea.put(id, current);
                    //因为新节点的删除动作被过滤了，这里只考虑新增的情况,它又是个新节点，所以数量必然为1，
                    //而获奖区里的至少都会有1个，不可能PK的过，所以不必处理更新逻辑
                }
            } else if (prizeArea.containsKey(id)) {
                //如果是新增不用管，本来奖励区就是放大数的
                //如果是退货，需要找出最小的和等候区最大的比
                if (!buy && waitArea.size() > 0) {
                    checkExchange(prizeArea, waitArea, i);
                }
            } else if (waitArea.containsKey(id)) {
                if (buy && prizeArea.size() > 0) {
                    checkExchange(prizeArea, waitArea, i);
                }
            }
            result.add(new ArrayList<>(prizeArea.keySet()));
        }
        return result;
    }

    private static void checkExchange(Map<Integer, Customer> prizeArea,
            Map<Integer, Customer> waitArea, int time) {
        List<Customer> prize = prizeArea.values().stream().sorted(new PrizeComparator()).collect(Collectors.toList());
        List<Customer> wait = waitArea.values().stream().sorted(new WaitComparator()).collect(Collectors.toList());
        Customer prizeLower = prize.get(0);
        Customer waitUpper = wait.get(0);
        if (waitUpper.count > prizeLower.count) {
            prizeArea.remove(prizeLower.getId());
            waitArea.remove(waitUpper.getId());
            prizeLower.setTime(time);
            waitUpper.setTime(time);
            prizeArea.put(waitUpper.getId(), waitUpper);
            waitArea.put(prizeLower.getId(), prizeLower);
        }
    }

    private static class WaitComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            if (o1.count == o2.count) {
                return o1.time - o2.time;
            } else {
                return o2.count - o1.count;
            }
        }
    }

    private static class PrizeComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            if (o1.count == o2.count) {
                return o1.time - o2.time;
            } else {
                return o1.count - o2.count;
            }
        }
    }

    public static List<Integer> getCurAns(ArrayList<Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Customer c : daddy) {
            ans.add(c.id);
        }
        return ans;
    }

    private static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Customer> map = new HashMap<>();
        ArrayList<Customer> cands = new ArrayList<>();
        ArrayList<Customer> daddy = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            if (!buyOrRefund && !map.containsKey(id)) {
                ans.add(getCurAns(daddy));
                continue;
            }
            // 没有发生：用户购买数为0并且又退货了
            // 用户之前购买数是0，此时买货事件
            // 用户之前购买数>0， 此时买货
            // 用户之前购买数>0, 此时退货
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            // 买、卖
            Customer c = map.get(id);
            if (buyOrRefund) {
                c.count++;
            } else {
                c.count--;
            }
            if (c.count == 0) {
                map.remove(id);
            }
            // c
            // 下面做
            if (!cands.contains(c) && !daddy.contains(c)) {
                if (daddy.size() < k) {
                    c.time = i;
                    daddy.add(c);
                } else {
                    c.time = i;
                    cands.add(c);
                }
            }
            cleanZeroBuy(cands);
            cleanZeroBuy(daddy);
            cands.sort(new WaitComparator());
            daddy.sort(new PrizeComparator());
            move(cands, daddy, k, i);
            ans.add(getCurAns(daddy));
        }
        return ans;
    }

    public static void move(ArrayList<Customer> cands, ArrayList<Customer> daddy, int k, int time) {
        if (cands.isEmpty()) {
            return;
        }
        // 候选区不为空
        if (daddy.size() < k) {
            Customer c = cands.get(0);
            c.time = time;
            daddy.add(c);
            cands.remove(0);
        } else { // 等奖区满了，候选区有东西
            if (cands.get(0).count > daddy.get(0).count) {
                Customer oldDaddy = daddy.get(0);
                daddy.remove(0);
                Customer newDaddy = cands.get(0);
                cands.remove(0);
                newDaddy.time = time;
                oldDaddy.time = time;
                daddy.add(newDaddy);
                cands.add(oldDaddy);
            }
        }
    }

    public static void cleanZeroBuy(ArrayList<Customer> arr) {
        List<Customer> noZero = new ArrayList<Customer>();
        for (Customer c : arr) {
            if (c.count != 0) {
                noZero.add(c);
            }
        }
        arr.clear();
        for (Customer c : noZero) {
            arr.add(c);
        }
    }
    /**
     * 5 , false
     * 3 , false
     * 8 , false
     * 9 , false
     * 7 , false
     *
     * 3 , false
     * 7 , true
     * 2 , true
     * 4 , false
     * 9 , false
     *              7-1 2-1
     * 7 , true
     * 9 , false
     * 4 , true
     * 4 , false
     * 9 , true
     *             7-2 2-1 9-1
     * 0 , false
     * 1 , false
     * 7 , true
     * 8 , true
     * 5 , false
     *            7-3 2-1 9-1 8-1
     * 1 , true
     * 3 , false
     * 2 , true
     * 4 , false
     * 2 , true
     *           7-3 2-3 9-1 8-1 | 1-1
     * 7 , true
     * 8 , true
     * 3 , true
     * 1 , false
     * 7 , true
     *           7-5 2-3 9-1  8-2 | 3-1
     * 0 , false
     * 3 , false
     * 8 , true
     * 1 , true
     * 7 , false
     *             7-4 2-3 9-1 8-3 | 1-1
     * 9 , false
     * 4 , true
     * 3 , false
     * 5 , false
     * 4 , true
     *
     * 6 , false
     * 0 , true
     * 6 , false
     * 1 , true
     * 0 , true
     * 9 , false
     * 2 , true
     * 2 , false
     * 9 , false
     * 0 , false
     * 6 , false
     * 2 , false
     * 1 , false
     * 2 , false
     * 3 , false
     * 1 , true
     * 9 , false
     * 1 , true
     * 1 , false
     * 3 , false
     * 8 , true
     * 3 , true
     * 5 , false
     * 2 , true
     * 5 , false
     * 5 , true
     * 8 , true
     * 6 , true
     * 7 , true
     * 3 , false
     * 5 , false
     * 6 , true
     * 6 , false
     * 3 , true
     * 0 , false
     * 0 , true
     * 3 , true
     * 7 , false
     * 1 , false
     * 5 , true
     * 2 , false
     * 9 , false
     * 9 , true
     * 0 , false
     * 0 , false
     * 8 , true
     * 1 , true
     * 8 , true
     * 2 , true
     * 5 , true
     * 7 , true
     * 4 , false
     * 8 , false
     * 4 , false
     * 0 , false
     * 4 , true
     * 8 , false
     * 7 , false
     */
}
