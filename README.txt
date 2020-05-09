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
 * TreePrinter.java - Allows the BTree to convert to ACTG sequences that can be printed in a dump file
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
Our b-tree class does not deviate much from the pseudo code given in class. However we had to encapsulate the frequency and 
the binary number inside of an BTreeObject class. Also, we had to add code to check for duplicates in the btree class.
We did this by checking all of the keys in each node when needed to increment the frequency. When using the random
access file we appended each node to the end of the file when writing to it. The length in bytes for each node
is (degrees^2) *12. Even thought this is a little larger than it needs to be, we overestimated to be safe. The
GeneBankCreateBTree class parses all of the arguments given by the user, converts the DNA strings to binary longs and
inserts the longs into the tree. The GeneBankSearch class uses the tree's search method to find a sequence in the tree.
Since we were not able to fully fix the GeneBankCreateBTree class we could not test the functionality of this class.

 

TESTING:
This doesn't fully work with low degrees, but using high degree, everything fully works, but running will take a while.
When using degree 50 and sequence 31 on GBKfile 1 (No Cache) all the sequences are inserted. It about 50-60 seconds on average
to insert all of the sequences. The probable reason why using low degrees does not work is that splitting a node
at one point eventualy breaks a b-tree property, or a location is being lost in the test.gbk.btree.data file. We primarly
tested by running the programs with different inputs and using the debugger, but the problem was locating where a btree property breaks
before the program crashes. In hindsight we should have created a test program. Overall, it was hard debugging after so many sequences
were inserted into the tree

 

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
