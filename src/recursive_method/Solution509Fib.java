package recursive_method;

public class Solution509Fib {
    //快速幂算法
    private long quick_pow(int base, int power){
        long result = 1;
        while (power > 0){
            if ((power & 1) == 1){
                // 注意指数相加（二进制表示相加关系），幂相乘
                result *= base;
            }
            base *= base;
            power >>= 1;
        }
        return result;
    }

    //矩阵版
    private int[][] quick_pow(int[][] base, int power){
        //将结果初始化为单元矩阵
        int[][] result = {{1,0},{0,1}};
        while(power > 0 ){
            if ((power&1) == 1){
                result = multiply(result,base);
            }
            base = multiply(base,base);
            power >>= 1;
        }
        return result;
    }

    //矩阵乘法
    private int[][] multiply_universal(int[][] left, int[][] right){
        int rsize = left.length;
        int csize = right[0].length;
        int[][] result = new int[rsize][csize];
        for (int i = 0; i < rsize; i++) {
            for (int j = 0; j < left[0].length; j++) {
                for (int k = 0; k < right.length; k++) {
                    result[i][k] += left[i][j]*right[j][k];
                }
            }
        }
        return result;
    }

    //二阶矩阵相乘
    private int[][] multiply(int[][] left, int[][] right){
        int[][] result = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result[i][j] = left[i][0]*right[0][j] + left[i][1]*right[1][j];
            }
        }
        return result;
    }

    public int fib(int n){
        //特判
        if (n<2) return n;
        int[][] M = {{1,1},{1,0}};
        //注意前面的矩阵幂指数需要减一
        int[][] fnfnpre = quick_pow(M,n-1);
        // int[2][1] f1f0 = {{1},{0}}
        return fnfnpre[0][0];
    }
}
