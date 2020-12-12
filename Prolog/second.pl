% stores reversed paths. 
% Reversed Path are of the form ([Path in a list], Time Taken, Distance , Cost, Value of Optimization Variable)
% [Path in a list] : Reversed Path in a list which contains the locations and the bus number used to reach those locations. For the starting location we assign the bus used to depart starting location
% Time Taken: Time taken in the path
% Distance: Distance of the path
% Value of Optimization Variable: value of the optimization variable(one among time taken, distance of path, cost of path) which depends upon the optimization objective

:-dynamic(reversedPath/5).




% Database of bus routes (Bus Number, From, To, DepartureTime, Arrival Time, Distance, Cost)

bus(100,'Amingaon','Jalukbari',14,15,10,10).
bus(101,'Amingaon','Maligaon',14,18,10,20).
bus(200,'Jalukbari','Paltanbazaar',15,17,10,6).
bus(201,'Jalukbari','Fancybazaar',15,18.5,9,5).
bus(202,'Jalukbari','Maligaon',15,16,7,10).
bus(300,'Paltanbazaar','Panbazaar',18,20,6,9).
bus(400,'Fancybazaar','Lokhra',19,19.5,6,6).
bus(401,'Maligaon','Lokhra',16,17,7,16).
bus(402,'Maligaon','Chandmari',16,18,10,30).
bus(500,'Lokhra','Panbazaar',17.5,19,8,5).
bus(501,'Panbazaar','Chandmari',19,20,7,8).
bus(502,'Panbazaar','Chandmari',18,19,8,4).






% updateShortestPath predicate saves the path if it better than the already stored path for that particular endpoint and 
% deletes the earlier best path for that particular endpoint. If there is no earlier path to compare to than it will store this new path directly.
% comparator predicate helps us in comparing paths

comparator(Opt, OptimizationObjective, T, Time, D, Distance, C, Cost):-   
	OptimizationObjective < Opt;
	T >= Time, D >= Distance, C >=  Cost.

updateShortestPath([[End,Number]|Tail], Time, Distance, Cost, OptimizationObjective):-		       
	reversedPath([[End,_]|_], T, D, C, Opt), !, 
	comparator(Opt, OptimizationObjective, T, Time, D, Distance, C, Cost),
	retractall(reversedPath([[End,_]|_], _, _,_, _)),
	assert(reversedPath([[End,Number]|Tail], Time, Distance, Cost, OptimizationObjective)).

updateShortestPath([[End,Number]|Tail], Time, Distance, Cost, OptimizationObjective):-		       
	assert(reversedPath([[End,Number]|Tail], Time, Distance, Cost, OptimizationObjective)).





% nodeTraversal is the Recursive predicate which finds all the paths. We use backtracking to find these paths
% We call this function on some Location with the information stored about the path which we took to come this Location
% We than search for Neighbour locations that are connected with Node using a bus and try extending the path.
% We now have some new paths. We discard those paths which have a repeated node. We than check whether these new paths are best for their endpoints
% If they are best for their endpoints than we save these paths. We use updateShortestPath to check and do these updates

nodeTraversal(Node, [Head|Tail],  PathTime, PathDistance, PathCost, PathArrivalTime, Type) :-		    
	bus(Number, Node, Neighbour, DepartureTime, ArrivalTime, Distance, Cost), 		    
	(PathTime=0->AdjustedPathTime is -DepartureTime, Path = [[Head,Number]] ; AdjustedPathTime is PathTime, Path = [Head|Tail]),
	not(memberchk([Neighbour,_], Path)),
	(DepartureTime>=PathArrivalTime-> NewPathTime is AdjustedPathTime + ArrivalTime - PathArrivalTime; NewPathTime is AdjustedPathTime + 24 + ArrivalTime - PathArrivalTime), 	    
	NewPathDistance is PathDistance+Distance,
	NewPathCost is PathCost + Cost,
	(Type='time'->OptimizationObjective is NewPathTime;Type='distance'->OptimizationObjective is NewPathDistance;OptimizationObjective is NewPathCost),
	updateShortestPath([[Neighbour,Number]|Path], NewPathTime, NewPathDistance, NewPathCost, OptimizationObjective), 
	nodeTraversal(Neighbour, [[Neighbour,Number]|Path], NewPathTime, NewPathDistance, NewPathCost, ArrivalTime, Type).	    








% Used to print the paths which are given in the form of a list

printList([[Place,Number]|[]]):- writef('%w,%w\n',[Place, Number]).
printList([[Place,Number]|Tail]):- writef('%w,%w->',[Place, Number]),printList(Tail).




% Computes the shortest path of the type Type for all the nodes reachable from "From" and stores those paths in form of reversedPath
% After this computation we take out the shortest which ends at "To" and print that path

findShortestPath(From, To, Type) :-
	\+nodeTraversal(From, [From], 0, 0, 0, 0, Type),                   
	(reversedPath([[To,Number]|Rest], Time, Distance, Cost, _)
	-> 
	  reverse([[To, Number]|Rest], Path),
	  writef("\nOptimum %w path is:\n",[Type]),
	  printList(Path),      
	  writef('Time:%w Distance:%w Cost:%w \n', [Time, Distance, Cost])	  
	; writef('There is no route from %w to %w\n', [From, To])),!.





% Calls the predicate which computes and prints the shortest paths according to time taken, distance value, and cost.
route(From, To) :-
    retractall(reversedPath([_|_],_,_,_,_)),		          
	findShortestPath(From, To, 'time'), 
    retractall(reversedPath([_|_],_,_,_,_)),
	findShortestPath(From, To, 'distance'), 
	retractall(reversedPath([_|_],_,_,_,_)),
	findShortestPath(From, To, 'cost').