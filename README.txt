****************
* Lab 4
* CS 321
* 05/08/2020
* Luke Ptomey, Jeremy Bouchard, Daniel McDougall
**************** 

OVERVIEW:

 This program creates a BTree that is made up of Nodes which are filled
 with data provided by a Gene Bank. The BTree is set up so that it can be
 searched and so that information about the tree can be provided.


INCLUDED FILES:

 * BTree.java - Handles all processes involving the BTree.
 * BTreeNode.java - Handles all processes involving the Nodes of the BTree.
 * BTreeObject.java - Handles what information can be placed in the BTree.
 * Cache.java - Handles the Cache for the overall process, needed for Cache implementation
 * FileRW.java - Allows for the manipulation of files provided by the user.
 * GeneBankCreateBTree.java - Handles the creation of the BTree from the user input.
 * GeneBankSearch.java - Allows for the BTree to be searched and lets the user search the BTree.
 * README - this file


COMPILING AND RUNNING:

 From the directory containing all of the .java files 
 as listed above, compile all necessary files with
 the following command:
 $ javac *.java

 Run the compiled class files (with a gbk file) with the command:
 $ java GeneBankCreateBTree <0/1(no/with Cache)> <degree> <gbk file> <sequence length> [<cache size>] [<debug level>]

 Run the compiled class files (with a BTree file) with the command:
 $ java GeneBankSearch <0/1(no/with Cache)> <BTree file> <query file> [<cache size>] [<debug level>]

 Console output will give the results after the program finishes.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:


 

TESTING:

 

DISCUSSION:

Main issues encountered during the build process included off by one errors, index out of bounds
exceptions, slitting nodes, and pushing the median key up the tree. We found the structure of the 
program to be the hardest to solve rather than the implementation to actually use the tree correctly. 
Even given the pseudo code from the powerpoint slides, debugging these issues was still necessary. Our
group also encountered many issues with Github and VSCode. Even though Github works seamlessly with VSCode,
and everyone had taken CS 271, there were issues with lanch.json files and pushing / pulling between
systmes with different jdk environments. This required the most self research, as any problems with the 
actual BTree could be check with the course slides / notes.

It was challenging to:
(1) set up the cache correctly, even having experience with writing a cache.
(2) Knowing when to conver to Binary, when to use a String
(3) Knowing when to have a long vs an int, and other type conversions

Something that finally "clicked" when building this program was the functionality and importance of the 
BTree. Attending class and reading the slides was enough to understand why the BTree is important, but
actually using the BTree to store large amounts of data helps understand its structure, mainly the
insertion process. It finally clicked as to why the insertion process is split into two methods,
which are both used inside a 3rd method, controlling whether split, or non-full insertions are
called.

 
EXTRA CREDIT:

 No extra credit was completed for this project.

----------------------------------------------------------------------------
