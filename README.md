# Minesweeper Java project [minesweeper]

This is a very primitive version of a Minesweeper game I implemented in Java. The game is playable in a Graphical User Interface. I am not yet satisfied with the end result and would like to continue developping this project in the future if I have more free time. 

## Prerequisites

The command line commands specified in this README-file are those for the Fedora Linux distro. Other operation systems may require different commands.

### Git

Install git via their [website](https://git-scm.com/) or by running the following command:

```bash
$ sudo dnf install git
```
Clone the repository

```bash
$ git clone git@github.com:michielskobe/minesweeper.git
$ cd minesweeper
```

### Java Development Kit 

Make sure you have the Java Development Kit (JDK) installed on your system. You can check this by running:

```bash
$ java -version
$ javac -version
```

If these commands return version information, you have the JDK installed. If not, you need to download and install it from the [Oracle JDK website](https://www.oracle.com/java/technologies/javase-downloads.html) or use an open-source alternative

## Play the game

The game can be played by running the play.sh Shell script:

```bash
$ sh play.sh
```

This Shell script compiles the Java files and runs the main class. A pop-up window should appear where you can start playing the minesweeper game. 

In the main menu, you get the possibility to select your difficulty level. The _Beginner_-level has a playing field of 8 by 8 tiles and 10 mines. The _Intermediate_-level has a playing field of 16 by 16 tiles and 40 mines. The _Expert_-level has a playing field of 30 by 16 tiles and 99 mines.

<p align="center">
  <img src = "https://github.com/michielskobe/minesweeper/assets/146984416/6c29b3bb-2b89-4b86-a38e-f5fb53a826ff" />
</p>

After you select a difficulty level, the game board will open and you can start playing the game. You can open a tile by left-clicking it. You can place a flag on a suspected mine by right-clicking it.

<p align="center">
  <img src = "https://github.com/michielskobe/minesweeper/assets/146984416/c1bc76a8-6160-4425-b67f-460ed77a1bf2" />
</p>

When you accidentally open a mine, a _GAME OVER_-message will appear.

<p align="center">
  <img src = "https://github.com/michielskobe/minesweeper/assets/146984416/46cc91b3-cb87-41cb-b373-69d5ab14ae6e" />
</p>

When you successfully flaged all mines, a congratulations-message will appear.

<p align="center">
  <img src = "https://github.com/michielskobe/minesweeper/assets/146984416/26ca8236-9461-4cfa-adb5-29abeaf8fc15" />
</p>






