package com.wipro.candidate.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.wipro.candidate.dao.CandidateDAO;
import src.com.wipro.candidate.bean.Candidatebean;

public class CandidateMain {

    CandidateDAO dao = new CandidateDAO(); // DAO object

    //  Add candidate
    public String addCandidate(Candidatebean candidate) {
        return dao.addCandidate(candidate);
    }

    //  Display candidates based on criteria
    public ArrayList<Candidatebean> displayAll(String criteria) {
        return dao.getByResult(criteria);
    }

    //  Main method
    public static <A> void main(String[] args) {
        CandidateMain service = new CandidateMain();
        Scanner sc = new Scanner(System.in);

        //  Add a new candidate
        System.out.println("Enter candidate name:");
        String name = sc.nextLine();

        System.out.println("Enter marks for M1:");
        int m1 = sc.nextInt();

        System.out.println("Enter marks for M2:");
        int m2 = sc.nextInt();

        System.out.println("Enter marks for M3:");
        int m3 = sc.nextInt();
        sc.nextLine(); // consume newline

        //  Generate candidate ID
        String id = service.dao.generateCandidateId(name);

        //  Calculate total & result
        int total = m1 + m2 + m3;
        String result = (m1 >= 40 && m2 >= 40 && m3 >= 40) ? "PASS" : "FAIL";

        //  Calculate grade
        String grade = "";
        double avg = total / 3.0;
        if (avg >= 80) grade = "A";
        else if (avg >= 60) grade = "B";
        else if (avg >= 40) grade = "C";
        else grade = "F";

        // Create Candidatebean object
        Candidatebean c = new Candidatebean();
        c.setId(id);
        c.setName(name);
        c.setM1(m1);
        c.setM2(m2);
        c.setM3(m3);
        c.setResult(result);
        c.setGrade(grade);

        //  Add candidate to DB
        String status = service.addCandidate(c);
        System.out.println("Insert Status: " + status);

        //  Loop for criteria display
        while (true) {
            System.out.println("\nEnter criteria to display candidates (PASS/FAIL/ALL/EXIT):");
            String criteria = sc.nextLine();

            if ("EXIT".equalsIgnoreCase(criteria)) {
                System.out.println("Exiting program...");
                break;
            }

            ArrayList<Candidatebean> list = service.displayAll(criteria);

            if (list == null) {
                System.out.println("No candidates found for criteria: " + criteria);
            } else {
                System.out.println("\nCandidates List (" + criteria + "):");
                for (Candidatebean cb : list) {
                    System.out.println(cb.getId() + " - " + cb.getName() + " - "
                            + cb.getM1() + ", " + cb.getM2() + ", " + cb.getM3()
                            + " - " + cb.getResult() + " - " + cb.getGrade());
                }
            }
        }

        sc.close();
    }
}
