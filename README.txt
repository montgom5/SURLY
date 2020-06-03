-Changed getArgs to accept new input
-created the 4 new command classes
-created the Where_processor
-changed command_processing to accept new input

-Join needs to know which attibutes are from what realtion so i think were going
to need to add another variable to the attibute class that holds its relation 

-Select, Delete and join all use the where processor which returns a list of args
in the proper order in lists as well as a list of OR and AND that can than be run
one by one by another class

-So we for now i think we should work on Select and delete than project to get them
all working and join should not be that much harder, as for the advanded feature i think
import export might be a good pick as i think i know how to implement that

