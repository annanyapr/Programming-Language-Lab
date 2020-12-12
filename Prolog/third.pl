% Stores shortest paths for all endpoints
:-dynamic(reversedPath/2).

% Stores all valid paths that end in 'G17'
:-dynamic(path/1).

% Edge info with their weights
weightedEdge('G1','G5',4).
weightedEdge('G2','G5',6).
weightedEdge('G3','G5',8).
weightedEdge('G4','G5',9).
weightedEdge('G1','G6',10).
weightedEdge('G2','G6',9).
weightedEdge('G3','G6',3).
weightedEdge('G4','G6',5).
weightedEdge('G5','G7',3).
weightedEdge('G5','G10',4).
weightedEdge('G5','G11',6).
weightedEdge('G5','G12',7).
weightedEdge('G5','G6',7).
weightedEdge('G5','G8',9).
weightedEdge('G6','G8',2).
weightedEdge('G6','G12',3).
weightedEdge('G6','G11',5).
weightedEdge('G6','G10',9).
weightedEdge('G6','G7',10).
weightedEdge('G7','G10',2).
weightedEdge('G7','G11',5).
weightedEdge('G7','G12',7).
weightedEdge('G7','G8',10).
weightedEdge('G8','G9',3).
weightedEdge('G8','G12',3).
weightedEdge('G8','G11',4).
weightedEdge('G8','G10',8).
weightedEdge('G10','G15',5).
weightedEdge('G10','G11',2).
weightedEdge('G10','G12',5).
weightedEdge('G11','G15',4).
weightedEdge('G11','G13',5).
weightedEdge('G11','G12',4).
weightedEdge('G12','G13',7).
weightedEdge('G12','G14',8).
weightedEdge('G15','G13',3).
weightedEdge('G13','G14',4).
weightedEdge('G14','G17',5).
weightedEdge('G14','G18',4).
weightedEdge('G17','G18',8).

% We can start our movement from these core unit gates
begin('G1').
begin('G2').
begin('G3').
begin('G4').

% Freedom Gate
end('G17').





% We can move in both the directions from an edge

edge(X, Y):- weightedEdge(X, Y, _).
edge(X, Y):- weightedEdge(Y, X, _).

weightedEdgeBidirectional(X, Y, C):- weightedEdge(X, Y, C).
weightedEdgeBidirectional(X, Y, C):- weightedEdge(Y, X, C).






%%%%%%%%%%%%%%%%%%% Q3:
% We call the valid predicate to check the validity of a path . It checks if the start of the path is valid. We then call the validTail predicate which checks whether 
% an edge exists between first two nodes in the path. It then discards the first node and calls validTail recursively on the remaining list till the end is reached


validTail([Head|[]]):- end(Head).
validTail([Head, Second|Tail]):- edge(Head, Second), validTail([Second|Tail]).
valid([Head|Tail]):-  begin(Head), validTail([Head|Tail]), !, writef("Given Path is Valid"). 
valid(_):- writef("Given Path is Invalid").
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



%%%%%%%%%%%%%%%%%%% Q2 :
%% This method is similar to what I implemented in Bus Travel Planner problem to calculate the shortest path to a point 



%%  shortestPath predicate checks is the new path is shortest and stores it if it is the shorter than the current stored path for that node

shortestPath([Head|Tail], Dist) :-reversedPath([Head|_], D), !, Dist =< D, retractall(reversedPath([Head|_],_)),assert(reversedPath([Head|Tail], Dist)).
shortestPath([Head|Tail], Dist) :-assert(reversedPath([Head|Tail], Dist)).






%% Traverses through all possible paths using backtracking and stores the shortest path ending at a particular node

nodeTraversal(Node, Path, Distance) :-		    
        weightedEdgeBidirectional(Node, Neighbour, DistanceEdge), 		    
        not(memberchk(Neighbour, Path)),
        NewDistance is Distance+DistanceEdge, 
        shortestPath([Neighbour|Path], NewDistance), 
        nodeTraversal(Neighbour, [Neighbour|Path], NewDistance).	    

remove:- retractall(reversedPath([_|_],_)).





%% Used to print the route

printList([Head|[]]):- writef('%w \n',[Head]).
printList([Head|Tail]):- writef('%w->',[Head]),printList(Tail).





% Calculates the shortest paths from begin nodes(G1, G2, G3, G4) to all the nodes in the graph. In the end we take out the shortest route which ends at G17
% and we print that route

optimal(X):-
        retractall(reversedPath([_|_],_)),
        retractall(path([_|_])),
        \+ nodeTraversal('G1', ['G1'], 0),
        \+ nodeTraversal('G2', ['G2'], 0),
        \+ nodeTraversal('G3', ['G3'], 0),
        \+ nodeTraversal('G4', ['G4'], 0),
        reversedPath(['G17'|Rest], _),
        reverse(['G17'|Rest], Path),
        writef('Optimum path is '),
        X = Path,
        printList(Path),nl,!.


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%




%%%%%%%%%%%%%%%%%%% Q1:

%% This module generate all the valid paths that start with a begin node and ends with the end node 


%% Termination condition of allPaths search

allPaths('G17', Path):- reverse(Path, ForwardPath),assert(path(ForwardPath)),false.





%% Recursively generate all paths

allPaths(Node, Path):- 
        Node \= 'G17',	    
        weightedEdgeBidirectional(Node, Neighbour, _), 		    
        not(memberchk(Neighbour, Path)),  
        allPaths(Neighbour, [Neighbour|Path]).	    







%% Calls allPaths to generate paths. Than prints out the count of paths

findPaths:-
        retractall(reversedPath([_|_],_)),
        retractall(path([_|_])),
        \+allPaths('G1', ['G1']),
        \+allPaths('G2', ['G2']),
        \+allPaths('G3', ['G3']),
        \+allPaths('G4', ['G4']),
        aggregate_all(count, path(_), Count),writef("Total Number of Paths: %w \n", [Count]), writef("You can use predicate seePath to print all the paths one by one.\n").





%% after running findpaths we can run seepaths to show all valid paths one by one

seePath:-
        retractall(reversedPath([_|_],_)),
        retractall(path([_|_])),
        \+allPaths('G1', ['G1']),
        \+allPaths('G2', ['G2']),
        \+allPaths('G3', ['G3']),
        \+allPaths('G4', ['G4']),
        path(X), writeln(X).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
