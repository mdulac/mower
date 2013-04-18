Mower
=====

[![Build Status](https://api.travis-ci.org/mdulac/mower.png?branch=master)](http://travis-ci.org/mdulac/mower)

Dependencies
------------
* JDK 1.6
* Maven 3
* SLF4J + LogBack
* TestNG
* AssertJ
* Mockito

Compile & Run
-------------

    git clone https://github.com/mdulac/mower.git
    cd mower
    mvn clean package
    java -jar target/mowitnow-0.0.1-SNAPSHOT.one-jar.jar src/main/resources/mower.txt

Latest Sonar Statistics
-----------------------
* 784 lines
* 92,6 rules compliances
* 80,9 unit tests coverage
* 0% duplications
* 100% tests success (55 unit tests)

How to use MowItNow
-------------------
MowItNow runs simulations of moving mowers.

You simply have to describe the simulation, as a file:
* the first line must be the coordinates of the top right position (two numbers separated by a space),
* the second line must be the coordinates of the mower and the orientation (two **digits** and [NESW] separated by a space)
* the third line must be a list of command (A to move forward, G to rotate left, and D to rotate right).

The second and third lines can be repeated, if you want to simulate several mowers **sequentially**.

Here is an example of a configuration file:

    5 5
    1 2 N
    GAGAGAGAA
    3 3 E
    AADAADADDA

The program finally prints the final position of each mowers.

Be careful of these three rules:
* if the initial position of a mower is outside the grid, the mower will not be taken into account in the simulation,
* if the position after movements is out of the field, mower does not move, retains its orientation, and processes the next command,
* if the position after movements is on an other mower, mower does not move, retains its orientation, and processes the next command.
