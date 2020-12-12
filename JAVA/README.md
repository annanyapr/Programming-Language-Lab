##### Commands are assumed to be run from the root of the folder provided.
##### Hence the folder from which commands are run should contain src and resources folder , as well as "forms_rt.jar".

The source code is compiled used java 8 (Openjdk  "1.8.0_265").
***
## ***1) Sock Matching Robot:***

To execute the program run the following commands from the terminal.

`$ javac src/Q1/*.java`

`$java -classpath ./src/  Q1.Main`



- The input file will be located at ./resource/Q1/input.txt. The format of the input file is as follows:
    - First line contains an integer specifying the number of robot arms.
    - All subsequent lines contain socks (can have multiple socks in the same line)
- Sock are identified by their color in the input file with the following scheme   
    - 1 : White
    - 2 : Black
    - 3 : Blue
    - 4 : Grey  
    
- The socks are automatically assigned IDs based on the order in the file. (Incremental counter starting from 0)
- The output of the program will display the socks picks up by each Robot arm and the IDs of the socks of same color matched by the Matching Machine.
- The final output will contain the number of pairs of socks in the Shelf once all possible matchings are done. 


## ***2) Data Modification System:***

To execute the program run the following commands from the terminal:

`$javac src/Q2/*.java`

`$java -classpath ./src/  Q2.Main`


After this follow along the instructions from terminal.The following sequence of actions may be taken: 

-  "Type 1 to Schedule an Update"(give 1 as input) -> It is used for scheduling an update. We can use it multiple times.
    -   The Teachers name needs to be given(It can be either CC/TA1/TA2).
    -   Next enter the Roll number of the Student. (Invalid roll numbers will be dealt during execution)
    -   Next Choose if you wish to increse or decrese marks.
    -   Finally enter by how much do you want to change the marks.
    - To schedule multiple updates repeat the first step.
- To execute the pending updates select "Type 2 to Execute all scheduled updates"(give 2 as input). This will read the data from the file and execute all the valid updates.
- Type 3 to Exit(give 3 as input).


The input file will be located at ../resource/Q2/stud_info.txt. It is a comma-separated text file with the following format:
Roll_Number,Name,Mail_id,Marks,Teacher_Code

The output files sorted by name and roll number can be found at  ./resource/Q2/stud_info_name.txt and ./resource/Q2/stud_info_roll.txt respectively



## ***3) Calculator for Differently Abled Persons: Part a and Part b***
    
To execute Part a(using only ENTER keys) run the following commands from the terminal:

`$javac -cp "forms_rt.jar" src/Q3_a/*.java`

`$java -classpath ./src:./forms_rt.jar Q3_a.Main`

Use ENTER key to select number or function. The two portions 
(number area and function area) will be alternatively highlighted.

To execute Part b(Using ENTER AND SPACE keys) run the following commands from the terminal:

`$javac -cp "forms_rt.jar" src/Q3_b/*.java`

`$java -classpath ./src:./forms_rt.jar Q3_b.Main`

Use ENTER key to select number and SPACE key to select function. The two portions 
(number area and function area) will be simultaneously highlighted.

##### Note: 
-For Both use CALCULATE to evaluate the expression. 
-To calculate further, remeber to use CLEAR, else no other input will be detected. You have to use CLEAR after using CALCULATE.


("forms_rt.jar" is needed since the GUI form was created using InteliJ's IDEA, which uses custom jar files.)
