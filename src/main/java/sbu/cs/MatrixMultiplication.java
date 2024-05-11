package sbu.cs;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplication {

    // You are allowed to change all code in the BlockMultiplier class
    public static class BlockMultiplier implements Runnable
    {
        List<List<Integer>> tempMatrixProduct;
        List<List<Integer>> matrix_A;
        List<List<Integer>> matrix_B;
        public BlockMultiplier(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B) { //constructor
            // TODO
            this.matrix_A = matrix_A;
            this.matrix_B = matrix_B;
        }
        @Override
        public void run() {
            /*
            TODO
                Perform the calculation and store the final values in tempMatrixProduct
            */
        }
        public List<List<Integer>> multiplyngMatrix(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B) {
            int rows1 = matrix_A.size();
            int cols1 = matrix_A.get(0).size();
            int cols2 = matrix_B.get(0).size();

            List<List<Integer>> result = new ArrayList<>();

            for (int i = 0; i < rows1; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j < cols2; j++) {
                    int sum = 0;
                    for (int k = 0; k < cols1; k++) {
                        sum += matrix_A.get(i).get(k) * matrix_B.get(k).get(j);
                    }
                    row.add(sum);
                }
                result.add(row);
            }
            return result;
        }
    }

    /*
    Matrix A is of the form p x q
    Matrix B is of the form q x r
    both p and r are even numbers
    */
    public static List<List<Integer>> ParallelizeMatMul(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B)
    {
        /*
        TODO
            Parallelize the matrix multiplication by dividing tasks between 4 threads.
            Each thread should calculate one block of the final matrix product. Each block should be a quarter of the final matrix.
            Combine the 4 resulting blocks to create the final matrix product and return it.
         */
        for (int i = 0 ; i < matrix_A.size()/2 ; i++) {
            for (int j = 0 ; j < matrix_B.size() ; j++) {

            }
        }
        BlockMultiplier blockMultiplier1 = new BlockMultiplier(matrix_A , matrix_B);
        Thread thread1 = new Thread(blockMultiplier1);
        BlockMultiplier blockMultiplier2 = new BlockMultiplier(matrix_A , matrix_B);
        Thread thread2 = new Thread(blockMultiplier1);
        BlockMultiplier blockMultiplier3 = new BlockMultiplier(matrix_A , matrix_B);
        Thread thread3 = new Thread(blockMultiplier1);
        BlockMultiplier blockMultiplier4 = new BlockMultiplier(matrix_A , matrix_B);
        Thread thread4 = new Thread(blockMultiplier1);
        return null;
    }

    public static void main(String[] args) {
        // Test your code here

    }
}
