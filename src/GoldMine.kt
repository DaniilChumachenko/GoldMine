package com.security_space.presentation.aboutus

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

fun main() {
    val rows = 4
    val columns = 4
    goldMine(fileToMassive(rows, columns), columns, rows)
}

fun goldMine(gold: Array<IntArray>?, M: Int, N: Int) {
    val buffer = Array(M) { IntArray(N) }
    for (column in N - 1 downTo 0) {
        for (row in 0 until M) {
            val x = if (row == 0 || column == N - 1) 0
                else buffer[row - 1][column + 1]
            val y = if (column == N - 1) 0
                else buffer[row][column + 1]
            val z = if (row == M - 1 || column == N - 1) 0
                else buffer[row + 1][column + 1]

            buffer[row][column] = ((gold?.get(row)?.get(column) ?: 0) + y.coerceAtLeast(x.coerceAtLeast(z)))
        }
    }
    var result = buffer[0][0]
    for (i in 1 until M) result = result.coerceAtLeast(buffer[i][0])
    return File("results.txt").writeText(result.toString())
}


fun fileToMassive(rows: Int, columns: Int): Array<IntArray> {
    val sc = Scanner(BufferedReader(FileReader("src\\goldMine.txt")))
    val myArray = Array(rows) { IntArray(columns) }
    Thread(Runnable {
        while (sc.hasNextLine()) {
            for (i in myArray.indices) {
                val line: List<String> = sc.nextLine().trim().split(" ")
                for (j in line.indices) {
                    myArray[i][j] = line[j].toInt()
                }
            }
        }
    }).run()
    return myArray
}

