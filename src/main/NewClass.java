/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Sandeep K
 */
public class NewClass {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        modifyArray(numbers.clone());

        // Print the original array
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }

    public static void modifyArray(int[] array) {
        // Modify the array
        for (int i = 0; i < array.length; i++) {
            array[i] *= 2; // Double each element
        }
    }

}
