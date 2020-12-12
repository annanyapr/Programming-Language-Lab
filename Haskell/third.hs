-- Name: Annanya Pratap Singh Chauhan
-- Roll Number: 170101008 
import Data.List
import System.IO
import Text.Printf ( printf )
import Data.Function



-- The following 5 functions are responsible for generating every possible series of dimensions for the rooms
-- Instead of making a single function for generating the series we broke the function into 5 steps, as it allows for optimisation after each step
-- This helps us in reducing our search space of the problem
--Make combinations of Bedroom,Hall dimensions
make_all_combinations_2:: [(Int, Int)] -> [(Int, Int)] -> [((Int, Int),(Int, Int))]
make_all_combinations_2 list1 list2 = [(x,y) | x <- list1, y <- list2]

--Make combinations of Bedroom,Hall,Kitchen dimensions
make_all_combinations_3:: [((Int, Int),(Int, Int))] -> [(Int, Int)] -> [((Int, Int),(Int, Int),(Int, Int))]
make_all_combinations_3 list1 list2 = [(tuple_1, tuple_2, y) | (tuple_1,tuple_2) <- list1, y <- list2]

--Make combinations of Bedroom,Hall,Kitchen,Bathroom dimensions 
make_all_combinations_4:: [((Int, Int),(Int, Int),(Int, Int))] -> [(Int, Int)] -> [((Int, Int),(Int, Int),(Int, Int), (Int, Int))]
make_all_combinations_4 list1 list2 = [(tuple_1, tuple_2, tuple_3, y) | (tuple_1,tuple_2,tuple_3) <- list1, y <- list2]

--Make combinations of Bedroom,Hall,Kitchen,Bathroom,Garden dimension
make_all_combinations_5:: [((Int, Int),(Int, Int),(Int, Int), (Int, Int))] -> [(Int, Int)] -> [((Int, Int),(Int, Int),(Int, Int), (Int, Int), (Int, Int))]
make_all_combinations_5 list1 list2 = [(tuple_1, tuple_2, tuple_3, tuple_4, y) | (tuple_1,tuple_2,tuple_3,tuple_4) <- list1, y <- list2]

--Make combinations of Bedroom,Hall,Kitchen,Bathroom,Garden,Balcony dimension
make_all_combinations_6:: [((Int, Int),(Int, Int),(Int, Int), (Int, Int), (Int, Int))] -> [(Int, Int)] -> [((Int, Int),(Int, Int),(Int, Int), (Int, Int), (Int, Int), (Int, Int))]
make_all_combinations_6 list1 list2 = [(tuple_1, tuple_2, tuple_3, tuple_4, tuple_5, y) | (tuple_1,tuple_2,tuple_3,tuple_4,tuple_5) <- list1, y <- list2]






-- Calculates the maximum area from the list of all possible dimension given as input
-- uses recusrsive max_area calls to iterate through the list 
answer_area:: [((Int,Int),(Int,Int),(Int,Int), (Int,Int), (Int,Int), (Int,Int))] -> Int-> Int-> Int-> Int-> Int-> Int -> Int
answer_area list1 count_1 count_2 count_3 count_4 count_5 count_6 =  max_area list1 count_1 count_2 count_3 count_4 count_5 count_6


max_area:: [((Int,Int),(Int,Int),(Int,Int), (Int,Int), (Int,Int), (Int,Int))] -> Int-> Int-> Int-> Int-> Int-> Int -> Int
max_area [] _ _ _ _ _ _= 0
max_area ((tuple_1,tuple_2,tuple_3,tuple_4,tuple_5,tuple_6):xs) count_1 count_2 count_3 count_4 count_5 count_6 = maximum [(((area tuple_1)*count_1)+((area tuple_2)*count_2)+((area tuple_3)*count_3)+((area tuple_4)*count_4)+((area tuple_5)*count_5)+((area tuple_6)*count_6)), max_area(xs) count_1 count_2 count_3 count_4 count_5 count_6]


-- given an array of areas and dimensions keeps only one copy of dimensions having same area
filter_repeating_area list  = recurse list []
recurse [] _ = []
recurse ((area,b):xs) list
    | (area `elem` list) = recurse xs list
    | otherwise = b : recurse xs (area:list)


-- helper function, caculates area given a tuple which stores the length and breadth
area:: (Int, Int)-> Int
area tup = fromIntegral( (fst $ tup) * (snd $ tup))



-- The main function that generates all possible valid series of dimensions of rooms given number of rooms and checks which among these options utilises most area and prints those dimensions
design :: Int -> Int -> Int -> IO()
design space num_bedroom num_hall = do
    -- Generate all possible dimensions for the rooms and store them in list
    let all_possible_bedroom_dimension = [(x , y) | x <- [10..15], y <- [10..15]]
    let all_possible_hall_dimension = [(x , y) | x <- [15..20], y <- [10..15]]
    let all_possible_kitchen_dimension = [(x , y) | x <- [7..15], y <- [5..13]]
    let all_possible_bathroom_dimension = [(x , y) | x <- [4..8], y <- [5..9]]
    let all_possible_garden_dimension = [(x , y) | x <- [10..20], y <- [10..20]]
    let all_possible_balcony_dimension = [(x , y) | x <- [5..10], y <- [5..10]]

    -- num of rooms of each type
    let num_garden = 1 
    let num_balcony = 1
    let num_bathroom = num_bedroom + 1
    let num_kitchen = ceiling ((fromIntegral num_bedroom) / (fromIntegral 3))



    -- creates combinations of dimensions of Bedroom, Hall. Stores only the valid combinations where area covered is less than space availabel
    let combination_of_2 = filter (\(tuple_1,tuple_2) -> ((area tuple_1)*num_bedroom) + ((area tuple_2)*num_hall) <= space) 
                            (make_all_combinations_2 all_possible_bedroom_dimension all_possible_hall_dimension)

    
    
    
    -- creates combinations of dimensions of Bedroom, Hall,Kitchen.  Stores only the valid combinations where area covered is less than space availabel
    -- Removes those cases where the kitchen dimension is more than either hall or bedroom 
    let combination_of_3 = filter (\(tuple_1,tuple_2,tuple_3) -> ((((area tuple_1)*num_bedroom) + ((area tuple_2)*num_hall) + ((area tuple_3)*num_kitchen)) <= space) && (fst(tuple_3) <= fst(tuple_1) && fst(tuple_3) <= fst(tuple_2) && snd(tuple_3) <= snd(tuple_1) && snd(tuple_3) <= snd(tuple_2)))     
                            (make_all_combinations_3 combination_of_2 all_possible_kitchen_dimension)                      


    
    
    -- creates combinations of dimensions of Bedroom, Hall,Kitchen, Bathroom.  Stores only the valid combinations where area covered is less than space availabel
    -- Removes those cases where the Bathroom dimension is more than Kitchen
    -- In this scenario we filter out the cases which have the same area and store only one among them, this helps us in optimising our solution and doesn't have any effect on solution finding ability of algorithms 
    let combination_of_4_with_duplicate_areas = filter (\(tuple_1,tuple_2,tuple_3,tuple_4) -> ((((area tuple_1)*num_bedroom) + ((area tuple_2)*num_hall) + ((area tuple_3)*num_kitchen) + ((area tuple_4)*num_bathroom))  <= space) && (fst(tuple_4) <= fst(tuple_3) && snd(tuple_4) <= snd(tuple_3)))  
                                                (make_all_combinations_4 combination_of_3 all_possible_bathroom_dimension)
    let temp_4 = [((((area a)*num_bedroom)                          
                            + ((area b)*num_hall)                 
                            + ((area c)*num_kitchen)              
                            + ((area d)*num_bathroom)),(a,b,c,d)) | (a,b,c,d)<-combination_of_4_with_duplicate_areas]
    let combination_of_4 = filter_repeating_area temp_4

    



    -- creates combinations of dimensions of Bedroom, Hall,Kitchen,Bathroom,Garden.  Stores only the valid combinations where area covered is less than space availabel
    -- In this scenario we filter out the cases which have the same area and store only one among them
    
    let combination_of_5_with_duplicate_areas = filter (\(tuple_1,tuple_2,tuple_3,tuple_4,tuple_5) -> ((((area tuple_1)*num_bedroom) + ((area tuple_2)*num_hall) + ((area tuple_3)*num_kitchen) + ((area tuple_4)*num_bathroom) + ((area tuple_5)*num_garden))   <= space))                                    
                                                (make_all_combinations_5 combination_of_4 all_possible_garden_dimension)
    let temp_5 = [((((area a)*num_bedroom)                          
                        + ((area b)*num_hall)                 
                        + ((area c)*num_kitchen)              
                        + ((area d)*num_bathroom)
                        + ((area e)*num_garden)),(a,b,c,d,e)) | (a,b,c,d,e)<-combination_of_5_with_duplicate_areas]
    let combination_of_5 = filter_repeating_area temp_5






    -- creates combinations of dimensions of Bedroom, Hall,Kitchen,Bathroom,Garden, Balcony.  Stores only the valid combinations where area covered is less than space availabel
    -- In this scenario we filter out the cases which have the same area and store only one among them    
    -- This is the final step in the process and results in all valid dimension sequences
    let combination_of_6_with_duplicate_areas = filter (\(tuple_1,tuple_2,tuple_3,tuple_4,tuple_5,tuple_6) -> ((((area tuple_1)*num_bedroom) + ((area tuple_2)*num_hall) + ((area tuple_3)*num_kitchen) + ((area tuple_4)*num_bathroom) + ((area tuple_5)*num_garden) + ((area tuple_6)*num_balcony)) <= space))                                    
                                                (make_all_combinations_6 combination_of_5 all_possible_balcony_dimension)
    let temp_6 = [((((area a)*num_bedroom)                          
                        + ((area b)*num_hall)                 
                        + ((area c)*num_kitchen)              
                        + ((area d)*num_bathroom)
                        + ((area e)*num_garden)
                        + ((area f)*num_balcony)),(a,b,c,d,e,f)) | (a,b,c,d,e,f)<-combination_of_6_with_duplicate_areas]
    let combination_of_6 = filter_repeating_area temp_6






    -- From the dimension sequences select the max area dimension sequence
    let max_space_covered = answer_area combination_of_6 num_bedroom num_hall num_kitchen num_bathroom num_garden num_balcony
    let output = filter (\(tuple_1,tuple_2,tuple_3,tuple_4,tuple_5,tuple_6) -> (((area tuple_1)*num_bedroom) + ((area tuple_2)*num_hall) + ((area tuple_3)*num_kitchen) + ((area tuple_4)*num_bathroom) + ((area tuple_5)*num_garden) + ((area tuple_6)*num_balcony)  == max_space_covered)) combination_of_6

    -- Print the result dimensions 
    if (length output == 0)then  -- No output 
        print "No design possible for the given constraints"
    else
        do  -- print the output
            let (tuple_1,tuple_2,tuple_3,tuple_4,tuple_5,tuple_6) = output !! 0
            printf "Bedroom: %d (%d x %d)\n" num_bedroom (fst $ tuple_1) (snd $ tuple_1)
            printf "Hall: %d (%d x %d)\n" num_hall (fst $ tuple_2) (snd $ tuple_2)
            printf "Kitchen: %d (%d x %d)\n" num_kitchen (fst $ tuple_3) (snd $ tuple_3)
            printf "Bathroom: %d (%d x %d)\n" num_bathroom (fst $ tuple_4) (snd $ tuple_4)
            printf "Balcony: %d (%d x %d)\n" num_balcony (fst $ tuple_6) (snd $ tuple_6)
            printf "Garden: %d (%d x %d)\n" num_garden (fst $ tuple_5) (snd $ tuple_5)
            printf "Unused Space:: %d \n" (space-max_space_covered)