package Q3_b;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.math.BigInteger;


// This class provides functions that helps in evaluating the expressions coming from the calculator input and returns their output by evaluating using Javascript Engine
public class evaluate {

// check whether the string is numeric
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }

        int a = 0;

        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            a += 1;
        }

        try {
            float d = Float.parseFloat(strNum);
        } catch (NumberFormatException nfe) {
            a += 1;
        }

        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            a += 1;
        }

        try {
            long d = Long.parseLong(strNum);
        } catch (NumberFormatException nfe) {
            a += 1;
        }

        try {
            BigInteger d = new BigInteger(strNum);
        } catch (NumberFormatException nfe) {
            a += 1;
        }


        if(a == 5){
            return(false);
        }
        else{
            return (true);
        }

    }

    // helper function(checks whether char in array )
    public static  Boolean in_array(char check, char[] array){
        for (char c: array){
            if (c == check){
                return true;
            }
        }
        return false;
    }
    // helper function(checks whether string in array )
    public static  Boolean in_array(String check, String[] array){
        for (String c: array){
            if (c.equals(check)){
                return true;
            }
        }
        return false;
    }


    // checks whether input string doesn't have three consecutive functions. Even for two consecutive function symbols only certain combinations are allowed
    public static Boolean consecutive_symbol(String input){

        char[] symbols = new char[]{'+', '-', '/', '*'};
        String[] pair_symbols = new String[]{"+-", "/-", "*-", "--"};


        for(int i = 0; i < input.length()-2; i++){
            if(in_array(input.charAt(i), symbols)  && in_array(input.charAt(i+1), symbols) && in_array(input.charAt(i+2), symbols)){
                return false;
            }
        }

        for(int i =0; i < input.length()-1; i++){
            if(in_array(input.charAt(i), symbols) && in_array(input.charAt(i+1), symbols)){

                if(in_array(input.substring(i, i+2), pair_symbols)){

                }
                else{

                    return false;
                }
            }
        }
        return true;


    }


    // this function evaluates the string coming as input. We will use Javascript eval to evaluate our string
    public static String eval(String input){

        if(input.length() == 0){
            return("");
        }

        int[] vec1 = new int[input.length()];


        for(int i = 0; i <input.length(); i++){  // Javascript treats 028 as an octal number. So we have to pre-process to remove the zero
            if(input.charAt(i) == '0'){

                if(i == 0){
                    vec1[i] = 0;
                }
                else{
                    char prev = input.charAt(i-1);
                    if(prev == '0'){
                        vec1[i] = vec1[i-1];
                    }
                    else if(prev == '*' || prev == '-' || prev == '+' || prev == '/'){
                        vec1[i]  = 0;
                    }
                    else{
                        vec1[i] = 1;
                    }
                }
            }
            else{
                vec1[i] = 1;
            }
        }


        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '0'){

                if(i == input.length()-1){
                    vec1[i] = 1;
                }
                else{
                    char next = input.charAt(i+1);
                    if(next == '*' || next == '-' || next == '+' || next == '/'){
                        vec1[i] = 1;
                    }
                }
            }
        }

        String input2 = "";

        for(int i =0; i < input.length(); i++){
            if(vec1[i] == 1){
                input2 += input.charAt(i);
            }
        }

        input = input2;

        // now check for certain consecutive function symbols. All other consecutive function symbols will be rejected and the input will be invalid.

        if (!consecutive_symbol(input)){
            return("Not A Number or Invalid Input");
        }

        String ans;
        ScriptEngineManager mgr = new ScriptEngineManager();

        ScriptEngine engine = mgr.getEngineByName("JavaScript");



        // All this exception handling is required to interpret the output of JavaScript engine.
        Boolean exception = false;
        Object obj = null;
        try {
            obj = engine.eval(input);
        } catch (ScriptException e) {
            exception = true;
        }


        if (!exception){

            ans = String.valueOf(obj);

            if (ans.equals("Infinity")){

            }
            else if (isNumeric(ans)){   // numeric answer

            }
            else{  // Non numeric answer means error
                ans = "Not A Number or Invalid Input";
            }
        }
        else{  // exception raised, return error
            ans = "Not A Number or Invalid Input";
        }
        return (ans);  // return the output
    }

}
