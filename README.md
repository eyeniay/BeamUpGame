# BeamUpGame
The aim of the project is to develop a turn based game,  in which players collect squares' points while walking and  beaming up on the board

The game is played on a board including squares. The squares have different points. There are two players.
The players collect the squares' points to increase their scores in turns. The aim of the game is gaining the
highest score in 25 rounds. There are 3 levels to select at the start of the game.

              Game Initialization
In the beginning of the game 4 lines are formed. Each of these lines has minimum 4 squares and maksimum
8 squares. Squares have 0-4 points. The first player begins the game. Two players determine their beginning
squares by beaming up operation.

              Movement Options
There are two movement options:
1. Beam up; Player selects row or column. The remaining one (which is not selected) will be
determined randomly. If there is no square or other player at beam up point, new beam up point
will be determined completely at random (random row and random column).
2. Follow arrows; Player moves depending on the arrow directions
  
   Players use cursor keys to move step by step
   Players use space key to beam up

            Game Playing Information
When a player comes to a square, point of the square is added to
his/her score. Then this square's point is decreased to 0. If there are three 0 consecutively in the game at
any time and players are not at any of these three squares, these squares will be deleted. Then new 3
squares are created and added to the line which has minumum squares with the random addition point in
the line. Two players cannot be on the same square at the same time
**************************
Data Structure: Single Linked List - Double Linked List - Multi Linked List
**************************
Project's trailer --> https://youtu.be/zNpNvsiyUbM
