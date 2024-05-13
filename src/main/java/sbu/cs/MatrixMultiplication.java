package sbu.cs;

import javax.swing.plaf.TableHeaderUI;
import java.beans.PropertyEditorSupport;
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
        public void run() { // the only task of threads is to multiplying those matrices
            /*
            TODO
                Perform the calculation and store the final values in tempMatrixProduct
            */
            multiplyingMatrix(matrix_A , matrix_B);
        }
        public List<List<Integer>> multiplyingMatrix(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B) { //this method will multiply the matrices
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
            tempMatrixProduct = result;
            return result;
        }
    }

    // ---------------------------------- combining the four matrices(blocks) together ------------------------------------------
    public List<List<Integer>> khodeMatrix (List<List<Integer>> block1 , List<List<Integer>> block2 , List<List<Integer>> block3 , List<List<Integer>> block4) {
        // block 1 : up & left
        // block 2 : up & right
        // block 3 : down & left
        // block 4 : down & right
        // first we combine upper blocks then lower block with each other, then combine them to a final matrix
        List<List<Integer>> finalMatrix = new ArrayList<>();
        // combining block 1 & block 2 (upper blocks)
        for (int i = 0 ; i < block1.size() ; i++) {
            List<Integer> row = new ArrayList<>(block1.get(i)); // copying the row of the block1
            row.addAll(block2.get(i));
            finalMatrix.add(row);
        }
        // combining block 3 & block 4 (lower blocks)
        for (int i = 0 ; i < block3.size() ; i++) {
            List<Integer> row = new ArrayList<>(block3.get(i)); // copying the row of the block1
            row.addAll(block4.get(i));
            finalMatrix.add(row);
        }
        return finalMatrix;
    }
    // ---------------------------------- combining the four matrices(blocks) together ------------------------------------------
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
        List<List<Integer>> temp1 = new ArrayList<>();
        List<List<Integer>> temp2 = new ArrayList<>();
        List<List<Integer>> temp3 = new ArrayList<>();
        List<List<Integer>> temp4 = new ArrayList<>();

        //----------------------------------------------for separating the matrix---------------------------------------------------

        for (int i = 0; i < matrix_A.size()/2; i++) { // the upper part of the matrix ---> thread 1
            temp1.add(matrix_A.get(i));
        }

        for (int i = 0; i < matrix_B.size(); i++) { // the left part of the matrix ---> thread 2
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < matrix_B.get(0).size()/2 ; j++) {
                row.add(matrix_B.get(i).get(j));
            }
            temp2.add(row);
        }

        for (int i = matrix_A.size()/2 ; i < matrix_A.size(); i++) { // the down part of the matrix --->thread 3
            temp3.add(matrix_A.get(i));
        }


        for (int i = 0; i < matrix_B.size(); i++) { // the right part of the matrix ---> thread 4
            List<Integer> row = new ArrayList<>();
            for (int j = matrix_B.get(0).size()/2 ; j < matrix_B.get(0).size() ; j++) {
                row.add(matrix_B.get(i).get(j));
            }
            temp4.add(row);
        }
        // ----------------------------------------------for separating the matrix---------------------------------------------------

        List<BlockMultiplier> listOfBlocks = new ArrayList<>();
        List<Thread> listOfThreads = new ArrayList<>();


        BlockMultiplier blockMultiplier1 = new BlockMultiplier(temp1 , temp2); //up & left
        listOfBlocks.add(blockMultiplier1);

        BlockMultiplier blockMultiplier2 = new BlockMultiplier(temp1 , temp4); //up & right
        listOfBlocks.add(blockMultiplier2);

        BlockMultiplier blockMultiplier3 = new BlockMultiplier(temp3 , temp2); // down & left
        listOfBlocks.add(blockMultiplier3);

        BlockMultiplier blockMultiplier4 = new BlockMultiplier(temp3 , temp4); //down & right
        listOfBlocks.add(blockMultiplier4);
        // -------------------------------------------------------------------------------------------------------------------------
        // ----------------------------------------------for starting the threads---------------------------------------------------
        for (int i = 0 ; i < listOfBlocks.size() ; i++) {
            Thread thread = new Thread(listOfBlocks.get(i));
            listOfThreads.add(thread);
        }

        for (int i = 0 ; i < listOfThreads.size() ; i++) {
            listOfThreads.get(i).start();

        }
        // ----------------------------------------------for starting the threads---------------------------------------------------
        // -------------------------------------------------------------------------------------------------------------------------
        // ---------------------------------- combining the four matrices(blocks) together ------------------------------------------
        MatrixMultiplication matrixMultiplication = new MatrixMultiplication();
        // ---------------------------------- combining the four matrices(blocks) together ------------------------------------------
        // -------------------------------------------------------------------------------------------------------------------------
        return matrixMultiplication.khodeMatrix(blockMultiplier1.tempMatrixProduct , blockMultiplier2.tempMatrixProduct , blockMultiplier3.tempMatrixProduct , blockMultiplier4.tempMatrixProduct);
    }

    public static void main(String[] args) {
        // Test your code here
        List<List<Integer>> matrix_A = new ArrayList<>();
        List<List<Integer>> matrix_B = new ArrayList<>();
        List<Integer> row1A = new ArrayList<>();
        row1A.add(1);
        row1A.add(2);
        row1A.add(3);
        List<Integer> row2A = new ArrayList<>();
        row1A.add(4);
        row1A.add(5);
        row1A.add(6);
        List<Integer> row3A = new ArrayList<>();
        row1A.add(7);
        row1A.add(8);
        row1A.add(9);
        List<Integer> row4A = new ArrayList<>();
        row1A.add(10);
        row1A.add(11);
        row1A.add(12);
        List<Integer> row1B = new ArrayList<>();
        row1A.add(13);
        row1A.add(14);
        List<Integer> row2B = new ArrayList<>();
        row1A.add(15);
        row1A.add(16);
        List<Integer> row3B = new ArrayList<>();
        row1A.add(17);
        row1A.add(18);
        BlockMultiplier blockMultiplier = new BlockMultiplier(matrix_A , matrix_B);
        blockMultiplier.multiplyingMatrix(matrix_A , matrix_B);
    }
}
