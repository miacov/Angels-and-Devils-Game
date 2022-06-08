# Angels and Devils Game
Java Program to play the Angels and Devils game in terminal. 

The game can either be played in User vs. User mode or in Automated mode where the computer plays against itself.

## Game Rules
* The game has 2 players, an angel and a devil.

* The board at the start just has the angel placed at the center, with all other squares being empty. The vertical axis of the board is the X axis, and the horizontal axis is the Y axis. A board with a size N has NxN dimensions.

* The angel has a power of value k, which determines which squares it can jump to. The angel can only move to squares that are reachable by at most k moves of a chess king piece. Jumping over blocked squares and staying stationary is allowed. In essence, the angel at square (x, y) can move to a square (X, Y) as long as |X - x| <= k and |Y - y| <= k. The angel's power cannot be greater than half the size of the board.

* After the angel makes a move, the devil can move to any empty square to block it. This square will no longer be accessible for the rest of the game.

* The goal of the angel is to reach a square in one of the edges of the board.

* The goal of the devil is to block the angel in a square that is not at the edge of the board, so that the angel has no legal move.

## Game Modes
* To launch the general menu to select a game mode, run PlayAD. A prompt will appear to choose either User vs. User mode or Automated mode. The size of the board and the power of the angel are chosen after the mode.

* In User vs. User mode, one player plays as the angel, and the other plays as the devil. In this mode, the users will be prompted to choose the X and Y coordinates to move their pieces. 

* In Automated mode, the computer plays against itself, controlling both the angel and the devil.

  * For the angel, if a square on an edge of the board is accessible and can be reached on the next move, then the computer will play this move to win. If the angel cannot win on the next move, then it is moved to a position that couldn't be reached by the angel in its previous position (the position before the current one). If the angel is on its first move (no previous position) and cannot win on the next move, then the computer will choose a random position for the angel to move to.
 
  * For the devil, the computer checks the angel's direction, which is determined by dividing the board into 4 equal rectangles and seeing in which of the 4 the angel is currently placed (either top-left, top-right, bottom-left, or bottom-right). The computer will block one of the squares on the edges of the board that are in the rectangle that the angel is placed in. If all these squares are already blocked with devil pieces, then the computer will block one of the squares that the angel can reach on its next move.
 
## Game Display
* Angel piece is marked with A
* Devil pieces are marked with @
* Empty squares are marked with +
 
~~~
     1   2   3   4   5   6   7   8   9   10      
1   @   +   @   +   @   +   +   +   +   +   1   
2   +   +   +   +   +   +   +   +   +   +   2   
3   +   +   A   +   +   +   +   +   +   +   3   
4   +   +   +   +   +   +   +   +   +   +   4   
5   +   +   +   +   +   +   +   +   +   +   5   
6   +   +   +   +   +   +   +   +   +   +   6   
7   +   +   +   +   +   +   +   +   +   +   7   
8   +   +   +   +   +   +   +   +   +   +   8   
9   +   +   +   +   +   +   +   +   +   +   9   
10  +   +   +   +   +   +   +   +   +   +   10  
    1   2   3   4   5   6   7   8   9   10      
~~~
