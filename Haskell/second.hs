-- Name: Annanya Pratap Singh Chauhan
-- Roll Number: 170101008 
import Data.IORef
import System.IO.Unsafe
import Data.Maybe
import Data.List
import System.IO
import Text.Printf ( printf )
import qualified System.Random as Random
import qualified Data.Set as Set


-- returns the list of all teams 
teams :: [String]
teams = ["BS", "CM", "CH", "CV", "CS", "DS", "EE", "HU", "MA", "ME", "PH", "ST"]


-- returns the list of all time slots possible
time_and_date_string :: [(String, String)] 
time_and_date_string = [("1-12-2020","9:30"),("1-12-2020","7:30"),("2-12-2020","9:30"),("2-12-2020","7:30"),("3-12-2020","9:30"),("3-12-2020","7:30")]

-- returns the list of all time slots in numerical value
time_and_date :: [(Int, Float)] 
time_and_date = [(1,9.5),(1,19.5),(2,9.5),(2,19.5),(3,9.5),(3,19.5)]

-- stores a global list of all draws
global_draws :: IORef [([Char], [Char])]
{-# NOINLINE global_draws #-}
global_draws = unsafePerformIO (newIORef [("None", "None")])


-- stores a shuffled list of all teams, required to generate the global team list
global_teams :: IORef [[Char]]
{-# NOINLINE global_teams #-}
global_teams = unsafePerformIO (newIORef teams)

-- generate a random value between x, and y
generate_seed :: Int -> Int -> IO Int 
generate_seed x y = Random.getStdRandom (Random.randomR (x, y))


-- uses seed to shuffle the array
shuffle :: [String] -> Int -> [String]
shuffle teams seed = permutations teams !! seed

-- shuffle:: [String] ->  [String]
-- shuffle teams = if length teams < 2 then return teams else do
-- 	i <- Random.randomRIO (0, length(teams)-1)
-- 	r <- shuffle ((take i teams) ++ (drop (i+1) teams))
-- 	return (teams!!i : r)

-- given the shuffled list of teams, makes the draws
make_draws :: [String] -> [(String, String)]
make_draws teams =  draws where 
    draws = [(teams !! index, teams !! (index+1)) | index <- [0,2..10]]

-- function used for printing a particular draw
print_draw :: [(String, String)] -> Int -> IO()
print_draw draws index = do
    printf "%s vs %s    %s  %s\n" (fst $ draws !! index) (snd $ draws !! index) (fst $ time_and_date_string !! index) (snd $ time_and_date_string !! index)


-- function required to print all draws
print_all_draws :: [(String, String)] -> IO()
print_all_draws draws = do
    print_draw draws 0
    print_draw draws 1
    print_draw draws 2
    print_draw draws 3
    print_draw draws 4
    print_draw draws 5



-- generates random fixtures when run with "all" arguement and prints them, if given a team name as argument then shows its particular drwa as output 
fixture:: String -> IO()
fixture option = do
    if option == "all" -- geneartes random fixtures
        then do
            seed <- generate_seed 0 10000
            let shuffled_teams = shuffle teams seed
            let draws = make_draws shuffled_teams
            writeIORef global_draws draws
            writeIORef global_teams shuffled_teams
            print_all_draws draws

    else 
        do
            draws <- readIORef global_draws
            teams <- readIORef global_teams
            if length draws == 1 
                then
                    print "The Fixtures have not been generated yet"
            else 
                if option `elem` teams -- print the input team fixture
                    then
                        print_draw draws (fromJust(elemIndex option teams) `div` 2)
                else 
                    print "No such team exists"


-- takes the date and time and input and generates the next match as output, in  this function we just check the validity of the input date and time,
-- we call find_and_print_draw if the date and time is valid to print the next draw
nextMatch:: Int -> Float -> IO() 
nextMatch date time = do
    draws <- readIORef global_draws
    if length draws == 1 
        then
            print "The Fixtures have not been generated yet"
    else
        do
            if date > 3 || date < 1 
                then
                    print "Wrong Date Time Given"
            else 
                if time < 0 || time > 24
                    then
                        print "Wrong Date Time Given"
                else
                    if date == 3 && time >= 19.5
                        then
                            print "Wrong Date Time Given"
                    else find_and_print_draw date time draws


-- finds and prints the next match as output given the date and time
find_and_print_draw:: Int -> Float -> [(String, String)] -> IO()
find_and_print_draw date time draws = do
    let time_and_date_modified = [ fromIntegral((fst $ time_and_date!!index) *100) + (snd $ time_and_date!!index)| index <- [0,1..5]] 
    let current_time_date = fromIntegral(date*100)+time
    let greater_than_element =  fromJust(Set.lookupGT current_time_date (Set.fromList time_and_date_modified))
    if greater_than_element == 109.5 then
        print_draw draws 0
        
    else if greater_than_element == 119.5 then
        print_draw draws 1

    else if greater_than_element == 209.5 then
        print_draw draws 2

    else if greater_than_element == 219.5 then
        print_draw draws 3

    else if greater_than_element == 309.5 then
        print_draw draws 4
        
    else
        print_draw draws 5





