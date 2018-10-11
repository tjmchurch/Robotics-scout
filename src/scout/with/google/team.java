/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.with.google;

import java.util.ArrayList;

/**
 *
 * @author trent
 */
public class team implements Comparable<team>{
    private int number;
    private int rp;
    private int qp;
    private ArrayList<team> partner = new ArrayList(); 
    private ArrayList<team> opp = new ArrayList(); 

    public team(int number) {
        this.number = number;
    }
    private int score = 0;
    private int partnerScore = 0;
    private int matchCount = 0;
    private double averageScore = 0;
    private double difference = 0;
    public void averageScore(int score){
        this.score += score;
    }
    public void addScore(int score){
        this.score += score;
    }
    public void addPartner(team partner){
        this.partner.add(partner);
    }

    public double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }
    

    public double getAverageScore() {
        return (double)score/partner.size();
    }

    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRp() {
        return rp;
    }

    public void setRp(int rp) {
        this.rp = rp;
    }

    public int getQp() {
        return qp;
    }

    public void setQp(int qp) {
        this.qp = qp;
    }

    public ArrayList getPartner() {
        return partner;
    }

    
    public ArrayList getOpp() {
        return opp;
    }

    public void setOpp(ArrayList opp) {
        this.opp = opp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPartnerScore() {
        return partnerScore;
    }

    public void setPartnerScore(int partnerScore) {
        this.partnerScore = partnerScore;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    @Override
    public String toString() {
        return  number + "," + ((double)((int)(difference*100)))/100;
    }

    @Override
    public int compareTo(team t) {
        return -(int) (this.difference-t.getDifference());
    }

 
    
}
