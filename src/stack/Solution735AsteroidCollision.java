package stack;

import java.util.*;

public class Solution735AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int asteroid:asteroids){
            if (asteroid>0){
                stack.push(asteroid);
            }else{
                if (stack.isEmpty()){
                    stack.push(asteroid);
                }else{
                    int lastAsteroid = stack.pop();
                    if (lastAsteroid<0){
                        stack.push(lastAsteroid);
                        stack.push(asteroid);
                    }else {
                        while(true){
                            if (lastAsteroid<0) break;
                            if (lastAsteroid>-asteroid){
                                stack.push(lastAsteroid);
                                break;
                            }else if (lastAsteroid<-asteroid){
                                if (stack.isEmpty()){
                                    break;
                                }else{
                                    lastAsteroid = stack.pop();
                                }
                            }else{
                                break;
                            }
                        }
                    }
                }
            }
        }
        int[] result = new int[stack.size()];
        Integer[] integerList = new Integer[stack.size()];
        stack.copyInto(integerList);
        for (int i = 0; i < stack.size(); i++) {
            result[i] = integerList[i];
        }
        return result;
    }
    // exp. [10,5,-5]
    // 较优解法
    public int[] asteroidCollision2(int[] asteroids){
        boolean alive;
        Deque<Integer> stk = new ArrayDeque<>(asteroids.length);
        for (int aster:asteroids){
            alive = true;
            // 1.aster必须存活，循环才有意义
            // 2.aster必须为负，才有必要循环
            // 3.栈必须非空，才可以循环
            // 4.栈顶值必须为正，才可能发生碰撞
            while (alive && aster<0 && !stk.isEmpty() && stk.peek()>0){
                // 若两者相等，则两个操作都执行
                // 如果栈顶元素的绝对值大于当前aster的绝对值，则aster炸毁
                // 如果栈顶元素的绝对值小于当前aster的绝对值，则栈顶元素炸毁
                if (stk.peek()>=-aster){
                    alive = false;
                }
                if (stk.peek()<=-aster){
                    stk.removeFirst();
                }
            }
            // 循环结束（或者未进入循环），如果aster仍存活，则将aster压入栈中
            if (alive) stk.addFirst(aster);
        }
        // 将栈中元素依次弹出，按照数组尾到头的顺序存入
        int size = stk.size();
        int[] aliveAster = new int[size];
        for (int i = 0; i < size; i++) {
            aliveAster[size-1-i] = stk.removeFirst();
        }
        return aliveAster;
    }
}
