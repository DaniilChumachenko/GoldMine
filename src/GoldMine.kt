package com.security_space.presentation.aboutus

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

fun main() {
    val rows = 4
    val columns = 4
    //We specify the dimension of the array, since we get it from a file
    val fileName = "src\\goldMine.txt"
    goldMine(fileToArray(fileName, rows, columns), columns, rows)
}

fun goldMine(gold: Array<IntArray>, M: Int, N: Int): Int {
    //If we pass a zero number of columns or rows to the method
    if (M == 0 || N == 0) return 0
    //creating a buffer array in which we will add the maximum amount of gold that a miner can collect
    val buffer = Array(M) { IntArray(N) }
    //We test the passages in which the tent moves straight up or down
    for (column in N - 1 downTo 0) {
        for (row in 0 until M) {
            val x = if (row == 0 || column == N - 1) 0
                else buffer[row - 1][column + 1]
            val y = if (column == N - 1) 0
                else buffer[row][column + 1]
            val z = if (row == M - 1 || column == N - 1) 0
                else buffer[row + 1][column + 1]
            //Writing the maximum value collected by one of the ways
            buffer[row][column] = (gold[row][column] + y.coerceAtLeast(x.coerceAtLeast(z)))
        }
    }
    //Output the result of the maximum gold that will be stored in the first column
    var result = buffer[0][0]
    for (i in 1 until M) result = result.coerceAtLeast(buffer[i][0])
    //Write the result to a text file according to the condition
    File("results.txt").writeText(result.toString())
    return result
}

//The method we use to convert numbers from a file to an array
fun fileToArray(fileName: String, rows: Int, columns: Int): Array<IntArray> {
    //We also check that the columns and rows are not zeros
    if (rows == 0 || columns == 0) return Array(0){ IntArray(0) }
    val sc = Scanner(BufferedReader(FileReader(fileName)))
    val myArray = Array(rows) { IntArray(columns) }
    //I wanted to optimize the method's running time somehow, so
    // I put a new thread where writing to the array occurs
    Thread(Runnable {
        while (sc.hasNextLine()) {
            for (i in myArray.indices) {
                //Numbers are separated by spaces, so each new number starts after a space
                val line: List<String> = sc.nextLine().trim().split(" ")
                for (j in line.indices) {
                    myArray[i][j] = line[j].toInt()
                }
            }
        }
    }).run()
    return myArray
}