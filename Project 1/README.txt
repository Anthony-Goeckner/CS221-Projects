****************
* Grid Monitor
* CS 221 Section 002
* January 18, 2022
* Anthony Goeckner
**************** 

OVERVIEW:

 This program reads data imported from a plain text file
 to monitor the temperatures of a solar array, and determine
 if the temperatures are unstable and would cause an explosion.


INCLUDED FILES:

 * GridMonitor.java - source file
 * GridMonitorInterface.java - source file
 * GridMonitorTest.java
 * README - this file


COMPILING AND RUNNING:

 Give the command for compiling the program, the command
 for running the program, and any usage instructions the
 user needs.
 
 These are command-line instructions for a system like onyx.
 They have nothing to do with Eclipse or any other IDE. They
 must be specific - assume the user has Java installed, but
 has no idea how to compile or run a Java program from the
 command-line.
 
 e.g.
 From the directory containing all source files, compile the
 driver class (and all dependencies) with the command:
 $ javac Class1.java

 Run the compiled class file with the command:
 $ java Class1

 Console output will give the results after the program finishes.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 One of the biggest concepts used throughout this program is
 the use of a nested for loop to loop through all of the items
 in the 2D array used to store temperature data. Alongside this,
 many of the methods inside of GridMonitor.java use the this 
 operator to use methods from within the class, so that code is 
 not repeated.

 GridMonitor.java works with the GridMonitorInterface.java interface 
 by implementing and defining all of the methods found within. 
 GridMonitor.java also works with GridMonitorTest.java by 
 having GridMonitorTest.java create a GridMonitor object to test
 all of the methods using expected outputs defined within 
 GridMonitorTest.java.

 In the getSurrouningSumGrid method, a repeated set of if/else 
 statements is used to determine whether or not the cell in a 
 direction is within range of the current grid. This could 
 possibly be optimized further to use only one or two if/else
 statements, rather than the 4 that it currently uses.

  

TESTING:

 The main way I tested the GridMonitor object was using a test class 
 that I wrote alongside GridMonitor to test my methods one at a time.
 After writing a method, I would have my test class print the returned 
 data from GridMonitor and compare that output with the data I had 
 calculated by hand. I also used the GridMonitorTest class to see what
 I may have been missing.


DISCUSSION:
 
 One of the issues that I kept facing was that I was having problems 
 with the negative numbers. While I was working on the getDeltaGrid 
 method, I only divided the result by 2. I did not think to make the 
 delta be a positive number for every input. Through the debugger, 
 I found that the safe minimum temperature and the safe maximum 
 temperature were the opposite, but instead of chenging the names of
 the variables, I changed the greater than/less than signs to the 
 opposite of what they were. When I was still failing the delta test,
 I noticed that all the delta values were supposed to be positive. 
 I then changed the algorithm so that the delta value would take the 
 absolute value before adding it to the grid, which completely messed
 up the fixes I had made to the delta program, where I flipped the 
 greater than/less than signs.Aside from that, I was expecting this 
 Project to be a lot more difficult. After reading the instructions, I 
 was prepared to spend multiple days and plenty of hours on this project,
 but after I started, I realized it wasn't going to be nearly as bad as 
 I thought. I had a lot of fun working on this project.