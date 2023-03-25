// local.scala
package multitier

object LocalMain extends App {
	var B = new Board(7, 6)
	var nextPlayer = 1
	
	var P1 = new Player(7)
	var P2 = new Player(7)

    // play game
	while (B.getWinner() == 0) {
		if (nextPlayer == 1) {
			while (! B.drop(1, P1.getMove())){}
			nextPlayer = 2
		}
		else {
			while (! B.drop(2, P2.getMove())){}
			nextPlayer = 1
		}
	}

	// print results
	println(B)
	if (B.getWinner() == 1)
		println("Winner is O")
	else if (B.getWinner() == 2)
		println("Winner is X")
	else
		println("It's a tie")
	B.reset()
}


