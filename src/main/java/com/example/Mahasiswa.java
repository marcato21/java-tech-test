package com.example;

/**
 * Class representing a Student (Mahasiswa) with name, student ID, and score.
 */
public class Mahasiswa {
    private String nama;
    private String nim;
    private int nilai;
    
    /**
     * Sets the data for the student.
     * 
     * @param nama Student's name
     * @param nim Student's ID number
     * @param nilai Student's score
     */
    public void setData(String nama, String nim, int nilai) {
        this.nama = nama;
        this.nim = nim;
        this.nilai = nilai;
    }
    
    /**
     * Prints the graduation status based on the student's score.
     * If nilai > 70, prints "Lulus" (Pass).
     */
    public void printGraduationStatus() {
        if (nilai > 70) {
            StringBuilder sb = new StringBuilder();
            sb.append("Mahasiswa ");
            sb.append(nama);
            sb.append(" dengan NIM ");
            sb.append(nim);
            sb.append(" dinyatakan Lulus");
            System.out.println(sb.toString());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Mahasiswa ");
            sb.append(nama);
            sb.append(" dengan NIM ");
            sb.append(nim);
            sb.append(" dinyatakan Tidak Lulus");
            System.out.println(sb.toString());
        }
    }
}

