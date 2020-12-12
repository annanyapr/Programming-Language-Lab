-- Name: Annanya Pratap Singh Chauhan
-- Roll Number: 170101008 
import Data.List 
import System.IO


-- Checks whether the provided list is empty or not . Works with Int, Float and String
isEmptySet :: [num] -> Bool
isEmptySet firstList
    | length firstList == 0 = True
    | otherwise =  False 



-- Unions the two provided sets.  Works with Int, Float and String 
unionSet :: (Eq num) => [num] -> [num] -> [num]
unionSet firstList secondList = returnList where
    unionList = firstList ++ secondList
    returnList = nub (unionList)

-- Takes the intersection of the two provided sets.  Works with Int, Float and String 
intersectionSet :: (Eq num) => [num] -> [num] -> [num]
intersectionSet firstList secondList = returnList where
    intersectionList = filter (\e -> e `elem` secondList) firstList
    returnList = nub (intersectionList)

-- Subtracts one set from the second set.  Works with Int, Float and String
subtractionSet :: (Eq num) => [num] -> [num] -> [num]
subtractionSet firstList secondList = returnList where
    commonElements = intersectionSet firstList secondList
    subtractionList = filter (\e -> not(e `elem` commonElements)) firstList
    returnList = nub (subtractionList)

-- Takes two sets as input. Adds any two possible elements in the sets and creates a new set.  Works with Int, Float. Can't be defined for String
additionSet :: (Num num, Ord num) => [num] -> [num] -> [num]
additionSet firstList secondList = returnList where
    tempList = [x + y | x <- firstList, y <- secondList]
    returnList = nub tempList    
