/*
* Nicholas Ouellette  3556762
*cs1073 Assignment 5, question 1
* created October 22 2016
*
*purpose: to read and display date from a textfile
*input: textfile and a int for year and string Y or N
*output: movies in alphabetic order, decadeds and if you have seen 20 of the movies
*
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class SciFiMovies {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        String movietotal, StringOfYear, SringsOfMovie, TempStringsOfMovie, OldestMovieName = null, NewestMovieName = null, UserInput;
        int oldest, Newest, UserInputAsInt, RandomNum, AdvrageAsIntTotal = 0, DecadeOfBiggestYear = 0;
        float Advrage;
        Movies[] Films = new Movies[100];
        int[] Decades = new int[100];
        int j;
      // gets the text file info
        Scanner SiMovies = new Scanner(new File("top_100_Sci-Fi_Films.txt"));
        int counter = 0;
        while (counter < 100) {
            movietotal = SiMovies.nextLine();

            StringOfYear = movietotal.substring((movietotal.length() - 5), (movietotal.length() - 1));
            TempStringsOfMovie = movietotal.substring(0, (movietotal.length() - 7));
            SringsOfMovie = TempStringsOfMovie.replaceAll("[^\\x00-\\x7F]", ""); // replace all non acii characters .. fixes first element read in
            Films[counter] = new Movies(TempStringsOfMovie, StringOfYear);
            counter++;
        }
        // finds out which movie is the newest and oldest
        counter = 0;
        oldest = 3000;
        Newest = 0;
        while (counter < 100) {

            if (Films[counter].intMovieYear < oldest) {

                oldest = Films[counter].intMovieYear;
                OldestMovieName = Films[counter].MovieTitle;

            }

            if (Films[counter].intMovieYear > Newest) {

                Newest = Films[counter].intMovieYear;
                NewestMovieName = Films[counter].MovieTitle;

            }
            counter++;
        }
        System.out.println("The Oldest Movie in this list is " + OldestMovieName + " " + oldest);
        System.out.println("The Newest Movie in this list is " + NewestMovieName + " " + Newest);

        counter = 0;
        //inictiling array Decades

        while (counter < 100) {

            Decades[counter] = 0;
            counter++;
        }

        counter = 0;
        while (counter < 100) {

            Decades[Films[counter].intMovieYear / 10 - 189]++;

            counter++;

        }
        counter = 0;
        while (counter < 100) {

            if (Decades[counter] > 0) {
                System.out.println(1890 + 10 * counter + "'s: " + Decades[counter] + " Films ");

            }
            counter++;
        }

        System.out.println("\n\nPlease enter one of the decades from above to get the list of movies form it.");
        Scanner myscanner = new Scanner(System.in);
        UserInput = myscanner.nextLine();
        UserInputAsInt = Integer.parseInt(UserInput.substring(0, 4));
        while (UserInputAsInt < 1890) {
            System.out.println("Pleas enter a decade greater then 1890.");
            UserInput = myscanner.nextLine();
            UserInputAsInt = Integer.parseInt(UserInput.substring(0, 4));
        }
        UserInputAsInt = (UserInputAsInt / 10) * 10;
        counter = 0;
        while (counter < 100) {

            if ((Films[counter].intMovieYear > UserInputAsInt || UserInputAsInt == Films[counter].intMovieYear)
                    && Films[counter].intMovieYear < (UserInputAsInt + 10)) {

                System.out.println(Films[counter].MovieTitle + " " + Films[counter].MovieYear);

            }

            counter++;
        }
        System.out.println("");
       //gets the movie for the seen or not seen making sure the same movie dose not repeat
        Random mygenerator = new Random();
        counter = 0;
        while (counter < 20) {
            RandomNum = mygenerator.nextInt(99);
            if ((Films[RandomNum].NotSeen) == 0 && (Films[RandomNum].Seen) == 0) {
                System.out.println("Please enter Y or N if you have seen this movie? " + Films[RandomNum].MovieTitle + " " + Films[RandomNum].intMovieYear + "?");
                UserInput = myscanner.nextLine();
                if (UserInput.equalsIgnoreCase("y")) {
                    Films[RandomNum].setSeen();
                    AdvrageAsIntTotal++;
                } else {
                    Films[RandomNum].setNotSeen();

                }

                counter++;
            }
        }
        System.out.println("You have seen on average " + (AdvrageAsIntTotal * 5) + "%");
        System.out.println("");

        counter = 0;
        //Persent of views most decade

        while (counter < 100) {

            Decades[counter] = 0;
            counter++;
        }

        counter = 0;
        while (counter < 100) {
            if ((Films[counter].Seen) == 1) {
                Decades[Films[counter].intMovieYear / 10 - 189]++;
            }
            counter++;

        }
        counter = 0;
        Newest = 0;
        while (counter < 100) {

            if (Decades[counter] > Newest) {

                Newest = Decades[counter];
                DecadeOfBiggestYear = counter;

            }
            counter++;
        }

        System.out.println("The decade you saw the most movies from was " + (1890 + 10 * DecadeOfBiggestYear) + "'s: " + Decades[DecadeOfBiggestYear] + " Films ");

        //bubble sort
        boolean flag = true;  // will determine when the sort is finished
        Movies temp;

        while (flag) {
            j = 0;
            flag = false;
            while (j < 100 - 1) {
                if (Films[j].MovieTitle.compareTo(Films[j + 1].MovieTitle) > 0) {                                             // ascending sort
                    temp = Films[j];
                    Films[j] = Films[j + 1];     // swapping
                    Films[j + 1] = temp;
                    flag = true;
                }
                j++;
            }
        }

        System.out.println("");
        System.out.println("The movies in Alphabetical order are: ");

        counter = 0;
        while (counter < 100) {
            System.out.println(Films[counter].MovieTitle + " " + Films[counter].MovieYear);
            counter++;
        }
    }
}
