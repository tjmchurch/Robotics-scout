/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.with.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author trent
 */
public class ScoutWithGoogle {

    /**
     * @param args the command line arguments
     */
    public static void calc(ArrayList<team> roster) {
        for (int i = 0; i < roster.size(); i++) {
            team temp = roster.get(i);
            ArrayList<team> partner = temp.getPartner();
            double sum = 0;
            for (int j = 0; j < partner.size(); j++) {
                sum += partner.get(j).getAverageScore();
            }

            temp.setDifference(temp.getAverageScore() - (sum / partner.size()));
        }

    }

    public static int findTeam(int number, ArrayList<team> roster) {
        if (roster.size() == 0) {
            roster.add(new team(number));
            return 0;
        }
        return findTeam(number, roster, 0, roster.size() - 1);
    }

    public static int findTeam(int number, ArrayList<team> roster, int lower, int upper) {
//        int mid = (lower + upper) / 2;
//        if (lower == upper) {
//            roster.add(lower, new team(number));
//            return lower;
//        }
//        if (roster.get(mid).getNumber() > number) {
//            findTeam(number, roster, lower, mid);
//        }
//        findTeam(number, roster, mid + 1, upper);
//
//        return lower;

        for (int i = 0; i < roster.size(); i++) {
            if (roster.get(i).getNumber() == number) {
                return i;
            }

        }
        roster.add(new team(number));
        return roster.size() - 1;

    }

    public static void addTeamScore(int number, int partner, int score, int penalty, ArrayList<team> roster) {
        team temp = roster.get(findTeam(number, roster));
        temp.addPartner(roster.get(findTeam(partner, roster)));
        temp.addScore(score);
    }

    public static boolean checkMatch(int match, ArrayList<Integer> matchNumber) {
        if (matchNumber.size() == 0) {
            matchNumber.add(match);
            return false;
        }

        return checkMatch(match, matchNumber, 0, matchNumber.size() - 1);
    }

    public static void checkMissingMatch(ArrayList<Integer> matchNumber) {
        int count = 1;
        for (int i = 0; i < matchNumber.size(); i++) {
            while (true) {
                if (count == matchNumber.get(i)) {
                    break;
                }                
                System.out.println("Missing Match #" + count);
                count++;
            }
            count++;
        }
//        int count = 1;
//        for (int i = 0; count + 1 < matchNumber.get(matchNumber.size() - 1); i++) {
//            while (true) {
//                
//                if (matchNumber.get(i) != count) {
//                    count++;
//                    count++;
//                    System.out.println("Missing Match #" + count);
//                }else{ count++; break;}
//
//            }
//        }
    }

    public static boolean checkMatch(int match, ArrayList<Integer> matchNumber, int low, int hi) {
        if (low > hi) {
            matchNumber.add(low, match);
            return false;
        }
        int mid = (low + hi) / 2;
        if (matchNumber.get(mid) == match) {
            System.out.println("double");
            return true;
        }
        if (matchNumber.get(mid) < match) {
            return checkMatch(match, matchNumber, mid + 1, hi);

        }
        return checkMatch(match, matchNumber, low, mid - 1);
    }

    public static ArrayList<team> readData() throws FileNotFoundException {
        ArrayList<Integer> matchNumber = new ArrayList();
        ArrayList<team> roster = new ArrayList<team>(); // could have made linked list but needed to access middle of list more than add to the list
        String file = "result.csv";
        File f1 = new File(file);
        Scanner input = new Scanner(f1);
        //input.nextLine();
        for (int i = 0; input.hasNext(); i++) {
            String total = input.nextLine();
            String[] match;
            match = total.split(",");

            //contains in order
            //match number, r1,r2,b1,b2,rscore,bscore,rpenalty,bpenalty
            if (!checkMatch(Integer.parseInt(match[0]), matchNumber)) {

                addTeamScore(Integer.parseInt(match[1]), Integer.parseInt(match[2]), Integer.parseInt(match[5]), Integer.parseInt(match[7]), roster);
                addTeamScore(Integer.parseInt(match[2]), Integer.parseInt(match[1]), Integer.parseInt(match[5]), Integer.parseInt(match[7]), roster);
                addTeamScore(Integer.parseInt(match[3]), Integer.parseInt(match[4]), Integer.parseInt(match[6]), Integer.parseInt(match[7]), roster);
                addTeamScore(Integer.parseInt(match[4]), Integer.parseInt(match[3]), Integer.parseInt(match[6]), Integer.parseInt(match[7]), roster);
            }
           
        }
        System.out.println(matchNumber.toString());
        checkMissingMatch(matchNumber);
        return roster;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<team> roster = readData();
        calc(roster);
        Collections.sort(roster);
        System.out.println(roster.toString());
    }

}
