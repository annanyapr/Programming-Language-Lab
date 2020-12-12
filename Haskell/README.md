##### Commands should be run from inside the folder 170101008_annanya provided.

***
## **1) Implement Haskell Functions for basic Set Operations**

#### To load the haskell program run:
  
`$ ghci`
`Prelude>:load first `

#### Functions avaliable are:   
##### isEmptySet 
Works for all **String, Int and Float** lists   
`*Main> isEmptySet [1, 2]`
##### unionSet
Works for all **String, Int and Float** lists
`*Main> unionSet [1, 2] [3, 4]`
##### intersectionSet
Works for all **String**, **Int** and **Float** lists
`*Main> intersectionSet [1, 2] [1, 3]`
##### subtractionSet
Works for all **Strings**, **Int** and **Float** lists
`*Main> subtractionSet [1, 2] [1, 3]`
##### additionSet
Works for **only** **Int** and **Float** lists as addition is not defined on **Strings**
`*Main> additionSet [1, 2] [1, 3]`           


***
## **2) IITG Football League**     

#### To load the haskell program run:
  
`$ ghci`
`Prelude>:load second `    


##### If System.Random is not already installed install it using      
`$ sudo apt-get install libghc-random-dev`

##### Generating all fixtures:     
`*Main> fixture "all"`
```
*Main> fixture "all"
CS vs EE    1-12-2020  9:30
DS vs CV    1-12-2020  7:30
CM vs CH    2-12-2020  9:30
HU vs BS    2-12-2020  7:30
MA vs ME    3-12-2020  9:30
PH vs ST    3-12-2020  7:30
```

##### Match Details of a particular team:     
`*Main> fixture "CS"`
```
*Main> fixture "CS"
CS vs EE    1-12-2020  9:30
```
##### Next Match Details:     
`*Main> nextMatch 1 13.25`
```
*Main> nextMatch 1 13.25
DS vs CV    1-12-2020  7:30
```

***
## **3) House Planner**

#### To load the haskell program run:
  
`$ ghci`
`Prelude>:load third `     

#### To Run the designer use 
`*Main> design spaceArea bedroomCount hallCount `

##### Examples
```
Average Running time example takes around 5 sec:

    *Main> design 1000 2 3
    Bedroom: 2 (10 x 10)
    Hall: 3 (15 x 10)
    Kitchen: 1 (7 x 5)
    Bathroom: 3 (4 x 5)
    Balcony: 1 (9 x 10)
    Garden: 1 (11 x 15)
    Unused Space:: 0
```
```
Worst Case Running time example takes around 60 sec:

    *Main> design 10000 12 14
    Bedroom: 12 (15 x 15)
    Hall: 14 (20 x 15)
    Kitchen: 4 (15 x 13)
    Bathroom: 13 (8 x 9)
    Balcony: 1 (10 x 10)
    Garden: 1 (20 x 20)
    Unused Space:: 884 
```



