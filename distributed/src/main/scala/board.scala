package multitier

import Array._

class Player (var w: Int) {
    val width: Int = w
    val rand = new scala.util.Random

    def getMove() : Int = {
        return (rand.nextDouble() * w).toInt
    }
}

class Board (var w: Int, var h: Int) {
    val width: Int = w
    val height: Int = h
    val board = ofDim[Int](height, width)
    var winner: Byte = 0
    var count: Int = 0
    reset()

    override def toString(): String = {
        val sb = new StringBuilder((width + 2) * (height + 2), "+-")
        for (x <- 0 until width) {
                sb ++= "--"
            }
        sb ++= "+\n"
        for (y <- 0 until height) {
            sb ++= "| "
            for (x <- 0 until width) {
                if (board(y)(x) == 0) {
                    sb ++= "  "
                } else if (board(y)(x) == 1) {
                    sb ++= "O "
                } else {
                    sb ++= "X "
                }
            }
            sb ++= "|\n"
        }
        sb ++= "+-"
        for (x <- 0 until width) {
                sb ++= "--"
            }
        sb ++= "+"
        return sb.result()
    }

    def drop(player: Int, col: Int) : Boolean = {
        if (winner != 0) {
            return false
        }
        if (player != 1 && player != 2) {
            return false
        }
        if (col >= width) {
            return false
        }
        var y = height - 1
        while (y >= 0) { // had to add braces to compile on scala 2
            if (board(y)(col) == 0) {
                board(y)(col) = player
                count += 1
                setWinner(y, col)
                return true
            }
            y -= 1
        }
        return false
    }

    def getWinner() : Int = {
        return winner
    }

    def setWinner(i: Int, j: Int) : Unit = {
        // check for 4 in a row here
        val color = board(i)(j)
        var conn = 0
        var i2 = 0
        var j2 = 0

        // vertical 4
        if (i <= 2) {
            if (board(i+1)(j) == color && board(i+2)(j) == color && board(i+3)(j) == color) {
                winner = color.toByte
                return
            }
        }
        
        // horizontal 4
        conn = 1
        j2 = j - 1
        while (j2 >= 0 && board(i)(j2) == color) {
            if (conn == 3) {
                winner = color.toByte
                return
            }
            conn += 1
            j2 -= 1
        }
        j2 = j + 1
        while (j2 < width && board(i)(j2) == color) {
            if (conn == 3) {
                winner = color.toByte
                return
            }
            conn += 1
            j2 += 1
        }

        // diagonal (\) 4
        conn = 1
        i2 = i - 1
        j2 = j - 1
        while (i2 >= 0 && j2 >= 0 && board(i2)(j2) == color) {
            if (conn == 3) {
                winner = color.toByte
                return
            }
            conn += 1
            i2 -= 1
            j2 -= 1
        }
        i2 = i + 1
        j2 = j + 1
        while (i2 < height && j2 < width && board(i2)(j2) == color) {
            if (conn == 3) {
                winner = color.toByte
                return
            }
            conn += 1
            i2 += 1
            j2 += 1
        }

        // diagonal (/) 4
        conn = 1
        i2 = i - 1
        j2 = j + 1
        while (i2 >= 0 && j2 < width && board(i2)(j2) == color) {
            if (conn == 3) {
                winner = color.toByte
                return
            }
            conn += 1
            i2 -= 1
            j2 += 1
        }
        i2 = i + 1
        j2 = j - 1
        while (i2 < height && j2 >= 0 && board(i2)(j2) == color) {
            if (conn == 3) {
                winner = color.toByte
                return
            }
            conn += 1
            i2 += 1
            j2 -= 1
        }

        // if full
        if (count == width * height) {
            winner = 3
        }
    }

    def reset() =  {
        for (y <- 0 until height) {
            for (x <- 0 until width) {
                board(y)(x) = 0
            }
        }
        winner = 0
        count = 0
    }
}
