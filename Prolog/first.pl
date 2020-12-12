% Database of relationships

parent(jatin,avantika).
parent(jolly,jatin).
parent(jolly,kattappa).
parent(manisha,avantika).
parent(manisha,shivkami).
parent(bahubali,shivkami).
male(kattappa).
male(jolly).
male(bahubali).
female(shivkami).
female(avantika).



% Question 1 Create Uncle relationship
% verifySibling(X, Y) Verifies whether the X provided is a sibling of Y provided
% uncle(X, Y) checks whether X is an uncle of Y.  It tries finding a parent of Y which is a sibling of X thus defining the uncle relationship
% X should also be a male.

verifySibling(X,Y):- parent(Z,X),parent(Z,Y),X \= Y,!.
uncle(X, Y) :- parent(Z, Y),male(X),verifySibling(Z,X).




% Question 2 Halfsister relationship
% sibling(X, Y, Z): checks whether X is a sibling of Y and assigns the common parent to Z 
% halfsister(X, Y): This checks whether X is a half sister of Y by checking that X is female and X and Y are siblings and should have only one common parent

sibling(X,Y,Z) :- parent(Z,X),parent(Z,Y),X \= Y.
halfsister(X, Y) :- female(X),sibling(X, Y, Z),parent(A,X),parent(B,Y), Z \= A, Z \= B, A \= B.