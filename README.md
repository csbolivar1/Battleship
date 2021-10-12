# Battleship

Battleship is a strategy type where two players guess where the opponent's ships are through a coordinate system.
More info found [here](https://en.wikipedia.org/wiki/Battleship_(game) "Battleship") 

## How to run

Prerequisites: Ensure you have Java installed on your computer. You can download and install the latest Java version [here](https://www.java.com/download/ie_manual.jsp)

In the PlayBattleship folder, run PlayBattleship.bat (safe to bypass Windows Defender warnings; .bat runs Battleship.jar)

## Board Layout
        1 2 3 4 5 6 7 8 9 10
 	  1 # # # # # # # # # # 
	  2 # # # # # # # # # # 
 	  3 # # # # # # # # # # 
	  4 # # # # # # # # # # 
 	  5 # # # # # # # # # # 
	  6 # # # # # # # # # # 
 	  7 # # # # # # # # # # 
	  8 # # # # # # # # # # 
 	  9 # # # # # # # # # # 
	 10 # # # # # # # # # # 
	 
## How to Play      
Players will begin by placing their ships on the board. 

* Player will first select a row number, 
* Player will then select a column number
* Player will enter 1 to position ship horizontally to the right, or 2 to position ship vertically downwards.
* Repeat for second player
	
Once ships have been placed, the game can begin.

* Player 1 attempt to sink one of Player 2's ships by selecting a row number, then column number. 
* If the guess is a hit, Player 1 can select another row and column number
* If the guess is a miss, Player 1's turn ends, process repeats with Player 2
* First player to sink all of their opponent's ships wins.		
