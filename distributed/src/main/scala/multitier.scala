package multitier

import scala.concurrent.Await
import scala.concurrent.duration._

import loci.language._
import loci.language.transmitter.rescala._
import loci.communicator.tcp._
import loci.serializer.upickle._
import loci.transmitter.IdenticallyTransmittable

import rescala.default._

import upickle.default._



@multitier object ConnectFour {
    @peer type Server <: { type Tie <: Single[Player1] with Single[Player2] }
    @peer type Player1 <: { type Tie <: Single[Server] }
    @peer type Player2 <: { type Tie <: Single[Server] }

    val B: Local[Board] on Server = new Board(7, 6)
    var nextPlayer: Local[Int] on Server = 1

    val P1: Local[Player] on Player1 = new Player(7)
    val P2: Local[Player] on Player2 = new Player(7)
    
    val player1Move = on[Player1] {
        P1.getMove()
    }

    val player2Move = on[Player2] {
        P2.getMove()
    }

    val move1 = on[Server] { 
        player1Move.asLocal
    }

    val move2 = on[Server] { 
        player2Move.asLocal
    }

    def main() = on[Server] {
        // play game
        while (B.getWinner() == 0) {
            if (nextPlayer == 1) {
                while (! B.drop(1, Await.result(move1, Duration.Inf))){}
                println("p1 went")
                nextPlayer = 2
            }
            else {
                while (! B.drop(2, Await.result(move2, Duration.Inf))){}
                println("p2 went")
                nextPlayer = 1
            }
        }
        winner()
        
    }

    def winner() = on[Server] {
        // print results
        println(B)
        if (B.getWinner() == 1) {
            println("Winner is O")
        }
        else if (B.getWinner() == 2) {
            println("Winner is X")
        }
        else {
            println("It's a tie")
        }
        multitier.terminate()
    }
    
}


object Main extends App {
    multitier start new Instance[ConnectFour.Server](
        connect[ConnectFour.Player1] { TCP(1095).firstConnection } and
        connect[ConnectFour.Player2] { TCP(1096).firstConnection })
    /*
    1 to 2 foreach { _ =>
        multitier start new Instance[ConnectFour.Client](
            connect[ConnectFour.Server] { TCP("localhost", 1095) })
    }
    */
    multitier start new Instance[ConnectFour.Player1](
        connect[ConnectFour.Server] { TCP("localhost", 1095) })
    multitier start new Instance[ConnectFour.Player2](
        connect[ConnectFour.Server] { TCP("localhost", 1096) })
}




