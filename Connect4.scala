import Array._

object Connect4 extends App {
    println("Creating Board")
    var B = new Board(7, 6)
    println(B)

    // simple Connect4 game
    B.drop(1, 3)
    B.drop(2, 3)
    B.drop(1, 2)
    B.drop(2, 2)
    B.drop(1, 4)
    B.drop(2, 1)
    B.drop(1, 5)
    println(B)
}

class Board (var w: Byte, var h: Byte) {
    val width: Byte = w
    val height: Byte = h
    val board = ofDim[Byte](height, width)
    reset()

    override def toString(): String = {
        var sb = new StringBuilder((width + 2) * (height + 2), "+-")
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

    def drop(player: Byte, col: Byte) : Boolean = {
        if (player != 1 && player != 2) {
            return false
        }
        if (col >= width) {
            return false
        }
        var y = height - 1
        while (y >= 0)
            if (board(y)(col) == 0) {
                board(y)(col) = player
                return true
            }
            y -= 1
        return false
    }

    def reset() =  {
        for (y <- 0 until height) {
            for (x <- 0 until width) {
                board(y)(x) = 0
            }
        }
    }
}
