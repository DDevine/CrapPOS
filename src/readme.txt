2509ICT Software Engineering COES
---------------------------------

COMPILATION
    Open terminal or command prompt to the project directory
    Compile the source using ‘javac src/au/edu/griffith/ict/*.java’

EXECUTION
    Open terminal or command prompt to the project directory after compiling
    Execute the command ‘java -cp src/ au.edu.griffith.ict.Main’

OPERATION
    Using the keyboard, select the appropriate submenu by typing the appropriate key and pressing [Enter],
    all submenu's behave in the same manner.
    By default, data is stored in the following files:
	customers.txt	-	Customer database
	menu.txt	-	Menu database
	users.txt	-	User database (no longer being used as per new requirements)


NOTE ON THE MAKE SCRIPTS
    The make scripts behave slightly differently; instead of compiling into the source directory, the compiled
    class files are placed in a newly created bin directory, along with a JAR package (coes-main.jar)
    Once built, the program can be started using either of the  following commands (from the project directory):
    	$ java -cp bin/ au.edu.griffith.ict.Main
	$ java -jar bin/coes-main.jar
