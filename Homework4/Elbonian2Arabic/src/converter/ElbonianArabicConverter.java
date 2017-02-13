package src.converter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class implements a converter that takes a string that represents a
 * number in either Elbonian or Arabic numeral form and offers methods that will
 * return the integer value.
 * 
 * @version February 8, 2017
 */
public class ElbonianArabicConverter {

    private String Elbonian;
    private ArrayList<Character> AlphabetCharacters;

    /**
     * Constructor that takes in a string. The string should contain a
     * valid Elbonian numeral. The string can have
     * leading and/or trailing spaces. There are no spaces within the actual
     * number characters.
     *
     * @param value The string representing the Elbonian number.
     * @throws MalformedNumberException if the string (not including leading and trailing
     *                                  spaces) does not represent a valid Elbonian number.
     */
    public ElbonianArabicConverter(String value) throws MalformedNumberException {

        AlphabetCharacters = new ArrayList<Character>();

        AlphabetCharacters.add('M');
        AlphabetCharacters.add('D');
        AlphabetCharacters.add('C');
        AlphabetCharacters.add('Y');
        AlphabetCharacters.add('X');
        AlphabetCharacters.add('J');
        AlphabetCharacters.add('I');

        // Check for MalformedNumberException here and set Elbonian = value

        value = value.replaceAll("\\s",""); // remove the whitespace from the incoming string

        System.out.println(value);

        Boolean validCharsResult = checkValidChars(value);
        //System.out.println("Valid chars passed? " + Boolean.toString(validCharsResult));
        if (!validCharsResult)
        {
            throw new MalformedNumberException("The string to convert contained invalid characters");
        }

        Boolean singleLowercaseResult = checkSingleLowercase(value);
        //System.out.println("Single Lower Case passed? " + Boolean.toString(singleLowercaseResult));
        if (!singleLowercaseResult)
        {
            throw new MalformedNumberException("More than one lowercase character of the same character");
        }

        Boolean ruleOneResult = checkRuleOne(value);
        //System.out.println("Rule one passed? " + Boolean.toString(ruleOneResult));
        if (!ruleOneResult)
        {
            throw new MalformedNumberException("Language rule 1 failed");
        }

        Boolean ruleTwoResult = checkRuleTwo(value);
        //System.out.println("Rule two passed? " + Boolean.toString(ruleTwoResult));
        if (!ruleTwoResult)
        {
            throw new MalformedNumberException("Language rule 2 failed");
        }

        Boolean ruleThreeResult = checkRuleThree(value);
        //System.out.println("Rule three passed? " + Boolean.toString(ruleThreeResult));
        if (!ruleThreeResult)
        {
            throw new MalformedNumberException("Language rule 3 failed");
        }

        Boolean ruleFourResult = checkRuleFour(value);
        //System.out.println("Rule four passed? " + Boolean.toString(ruleFourResult));
        if (!ruleFourResult)
        {
            throw new MalformedNumberException("Language rule 4 failed");
        }

        Elbonian = value;

    }

    /**
     * Checks to see if the input characters are in the Elbonian Alphabet
     *
     * @param value: The string to check
     * @return True if the test passes, false if it fails
     */
    private Boolean checkValidChars(String value) {

        for (char c : value.toCharArray()) {
            if (!(AlphabetCharacters.contains(c) || AlphabetCharacters.contains(Character.toUpperCase(c)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * There can only be one of each lower case letter in the alphabet, checks this rule.
     *
     * @param value: The string to check
     * @return True if the test passes, false if it fails
     */
    private Boolean checkSingleLowercase(String value)
    {
        for (char c: AlphabetCharacters)
        {
            int lowerCount = 0;

            c = Character.toLowerCase(c);

            for (char cx: value.toCharArray())
            {
                if (cx == c)
                {
                    lowerCount += 1;

                    if (lowerCount > 1)
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Returns the result of checking if the string passes the first rule of the language:
     *
     * The same letter that is a multiple of 10 (including 1) can be repeated in a row to represent a multiple
     * of that value. However, a letter can only be repeated up to three times in a row.  MMM = 3000, XX = 20
     *
     * @param value: The string to check
     * @return True if the test passes, false if it fails
     */
    private Boolean checkRuleOne(String value)
    {
        int maxNum = 3;

        char previousChar = ' ';
        int charCount = 1;

        for (char c: value.toCharArray())
        {
            if (c == previousChar)
            {
                charCount += 1;

                if (charCount > maxNum)
                {
                    return false;
                }
            }
            else
            {
                previousChar = c;
                charCount = 1;
            }
        }
        return true;
    }

    /**
     * Returns the result of checking if the string passes the second rule of the language:
     *
     * Numbers are represented by the letters from the greatest value down to the lowest value.
     * In other words, the letter I would never appear before the letters J, X, Y, C, D, or M.
     * The letter C would never appear before the letters D or M.
     * The letters are summed together to determine the value.
     *
     * @param value: The string to check
     * @return True if the test passes, false if it fails
     */
    private Boolean checkRuleTwo(String value)
    {
        String upperString = value.toUpperCase();

        ArrayList<Integer> indices = new ArrayList<>();

        for (char c: upperString.toCharArray())
        {
           indices.add(AlphabetCharacters.indexOf(c));
        }

        for (int index = 0; index < indices.size(); index++)
        {
            for (int i = index; i < indices.size(); i++)
            {
                if (indices.get(index) > indices.get(i))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * 3. Since a letter cannot be repeated four times, a subtraction rule is used. Writing the same lowercase letter to the left of a matching uppercase letter represents subtraction.
     *   a.	For multiples of 10, a lowercase letter subtracts one-tenth of the succeeding matching letter. For example, mM = 900, cC = 90, and xX = 9.
     *   b.	For multiples of 5, a lowercase letter subtracts one-fifth of the succeeding letter. For example, dD = 400, yY = 40, and jJ = 4.
     *   c.	If a lowercase letter precedes an uppercase letter, that uppercase letter may not be repeated. You cannot have the following numbers: cCC, MmMMM, DxXXJ
     *
     * @param value: The string to check
     * @return True if the test passes, false if it fails
     */
    private Boolean checkRuleThree(String value)
    {
        for (char ci: AlphabetCharacters)
        {
            Character lower = Character.toLowerCase(ci);
            Boolean lowerFound = false;
            int subsequentChars = 0;
            for (char c : value.toCharArray())
            {
                if (c == lower)
                {
                    lowerFound = true;
                }

                else if (lowerFound)
                {
                    if (c == ci)
                    {
                        subsequentChars += 1;

                        if (subsequentChars > 1)
                        {
                            return false;
                        }

                    }
                }
            }

            if (subsequentChars == 0 && lowerFound)
            {
                return false;
            }

        }

        return true;
    }

    /**
    * Returns the result of checking if the string passes the fourth rule of the language:
    *
    * The letters D, Y and J may appear only once in a number. You cannot have YyY or JJ
    *
    * @param value: The string to check
    * @return True if the test passes, false if it fails
    */
    private Boolean checkRuleFour(String value)
    {
        ArrayList<Character> onlyOnce = new ArrayList<>();

        onlyOnce.add('D');
        onlyOnce.add('Y');
        onlyOnce.add('J');

        for (char c: onlyOnce)
        {
            int count = 0;

            for (char cx : value.toCharArray())
            {
                if (c == cx)
                {
                    count += 1;

                    if (count > 1)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Returns the value (field Elbonian) of this object as an Arabic number.
     *
     * @return The integer value of the field Elbonian
     */
    public int toArabic()
    {

        HashMap<Character, Integer> addmap = new HashMap<>();

        addmap.put('M', 1000); // multiple of 10
        addmap.put('D', 500);  // multiple of 5
        addmap.put('C', 100);  // multiple of 10
        addmap.put('Y', 50);   // multiple of 5
        addmap.put('X', 10);   // multiple of 10
        addmap.put('J', 5);    // multiple of 5
        addmap.put('I', 1);

        HashMap<Character, Integer> submap = new HashMap<>();

        submap.put('m', 100);
        submap.put('c', 10);
        submap.put('x', 1);
        submap.put('d', 100);
        submap.put('y', 10);
        submap.put('j', 1);

        int sum = 0;

        for (char c: Elbonian.toCharArray())
        {
            try
            {
                sum += addmap.get(c);
            }

            catch (NullPointerException name)
            {
                sum -= submap.get(c);
            }
        }
        return sum;
    }
}
