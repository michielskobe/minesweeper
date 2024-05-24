# README: Minesweeper Java project

This is a very primitive version of a Minesweeper game I implemented in Java. The game is playable in a Graphical User Interface. I am not yet satisfied with the end result and would like to continue developping this project in the future if I have more free time. 

## Prerequisites

### Clone the repository

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

## Compile the Java files

Compile your Java files using the _javac_ command. 

```bash
$ cd src
$ javac -d ../out *.java
```

## Run the Java class

After compiling your Java files, you can run the main class.

```bash
$ cd ../out
$ java Main
```

A pop-up window should appear where you can start playing the minesweeper game.
