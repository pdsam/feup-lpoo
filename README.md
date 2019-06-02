# LPOO_71 Crack Attack

> The project goal is to recreate the game [Crack attack](http://www.aluminumangel.org/attack/). The game's goal is to not let the blocks get to the top (like tetris) by making lines with at least 3 colored blocks with the same colour to pop them thus increasing the score. In a nutshell this a game is a mix between tetris and candy crush like games.

## Short Introduction

In this game, you have a board with colored blocks, over time, a new line of block appears from bellow pushing all the blocks up. Your goal is to move your selector and swap blocks so you create line/columns of three equal block in a row to break them, preventing them from touching the top.

- User your arrow keys to move the selector, and SPACE to swap blocks.
- If you feal like quitting you can press Q.

## Screenshots

<!-- Some screenshots that illustrate the game.-->
!["Swing GUI"](https://i.imgur.com/b4nuHle.png)
!["Lantera GUI"](https://imgur.com/zy7qwwc.png)
!["Start Screen"](https://i.imgur.com/ssIb6TW.png)
!["You lost screen"](https://imgur.com/nOOkoLG.png)

## Install Instructions

> How can someone install the game in their computer.

Make sure you have Java 8 installed.

### Linux

1. Navigate into the code folder
2. Run "./gradlew run --args [view]" where [view] can be "lanterna" or "swing".

### Windows

1. Navigate into the code directory
2. Open a command line in that folder and run "gradlew.bat run --args [view]" where [view] can be "lanterna" or "swing".
