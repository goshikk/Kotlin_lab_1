import kotlin.random.Random

class Sudoqu {

    fun main() {
        val board = generateSudoku()
        printBoard(board)

        while (!isSudokuSolved(board)) {
            println("\nВведите строку, столбец и число (через пробел), чтобы поставить число в ячейку:")
            val input = readLine()
            val values = input?.split(" ")?.map { it.toInt() }

            if (values != null && values.size == 3) {
                val row = values[0]
                val col = values[1]
                val num = values[2]

                if (isValidMove(board, row, col, num)) {
                    board[row - 1][col - 1] = num
                    printBoard(board)
                } else {
                    println("Некорректный ход! Попробуйте еще раз.")
                }
            } else {
                println("Некорректный ввод! Попробуйте еще раз.")
            }
        }

        println("\nПоздравляем! Вы решили головоломку Судоку.")
    }

    fun generateSudoku(): Array<Array<Int>> {
        val sudoku = Array(9) { Array(9) { 0 } }

        repeat(20) {
            val row = Random.nextInt(9)
            val col = Random.nextInt(9)
            val num = Random.nextInt(1, 10)

            if (isValidMove(sudoku, row, col, num)) {
                sudoku[row][col] = num
            }
        }

        return sudoku
    }

    fun isValidMove(board: Array<Array<Int>>, row: Int, col: Int, num: Int): Boolean {
        // Проверка, что число не встречается в строке и столбце
        for (i in 0 until 9) {
            if (board[row - 1][i] == num || board[i][col - 1] == num) {
                return false
            }
        }

        // Проверка, что число не встречается в квадрате 3x3
        val startRow = (row - 1) / 3 * 3
        val startCol = (col - 1) / 3 * 3

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (board[startRow + i][startCol + j] == num) {
                    return false
                }
            }
        }

        return true
    }

    fun isSudokuSolved(board: Array<Array<Int>>): Boolean {
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (board[i][j] == 0) {
                    return false
                }
            }
        }
        return true
    }

    fun printBoard(board: Array<Array<Int>>) {
        println("┌───┬───┬───┐")
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (j % 3 == 0) print("│")
                print(" ${if (board[i][j] == 0) " " else board[i][j]} ")
            }
            println("│")
            if ((i + 1) % 3 == 0 && i < 8) println("├───┼───┼───┤")
        }
        println("└───┴───┴───┘")
    }

}