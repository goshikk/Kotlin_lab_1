class Sudoku(private val board: Array<IntArray>) {
    fun solve(): Boolean {
        for (row in 0..8) {
            for (col in 0..8) {
                if (board[row][col] == 0) {
                    for (number in 1..9) {
                        if (isValid(board, row, col, number)) {
                            board[row][col] = number
                            if (solve()) {
                                return true
                            } else {
                                board[row][col] = 0
                            }
                        }
                    }
                    return false
                }
            }
        }
        return true
    }

    private fun isValid(board: Array<IntArray>, row: Int, col: Int, number: Int): Boolean {
        for (i in 0..8) {
            if (board[row][i] == number) {
                return false
            }
            if (board[i][col] == number) {
                return false
            }
        }
        val sqrt = 3
        val boxRowStart = row - row % sqrt
        val boxColStart = col - col % sqrt
        for (i in boxRowStart until boxRowStart + sqrt) {
            for (j in boxColStart until boxColStart + sqrt) {
                if (board[i][j] == number) {
                    return false
                }
            }
        }
        return true
    }

    fun print() {
        for (i in 0..8) {
            for (j in 0..8) {
                print("${board[i][j]} ")
            }
            println()
        }
    }
}

fun main() {
    val board = arrayOf(
        intArrayOf(5, 3, 0, 0, 7, 0, 0, 0, 0),
        intArrayOf(6, 0, 0, 1, 9, 5, 0, 0, 0),
        intArrayOf(0, 9, 8, 0, 0, 0, 0, 6, 0),
        intArrayOf(8, 0, 0, 0, 6, 0, 0, 0, 3),
        intArrayOf(4, 0, 0, 8, 0, 3, 0, 0, 1),
        intArrayOf(7, 0, 0, 0, 2, 0, 0, 0, 6),
        intArrayOf(0, 6, 0, 0, 0, 0, 2, 8, 0),
        intArrayOf(0, 0, 0, 4, 1, 9, 0, 0, 5),
        intArrayOf(0, 0, 0, 0, 8, 0, 0, 7, 9)
    )
    val sudoku = Sudoku(board)
    if (sudoku.solve()) {
        sudoku.print()
    } else {
        println("Нет решения")
    }
}
