/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwdcracktomusic;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author matthew.stemen
 */
class Note {

    public Note(String value, int octave) {
        this.value = value;
        this.octave = octave;

    }

    private String value;
    private int octave;

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the octave
     */
    public int getOctave() {
        return octave;
    }

    /**
     * @param octave the octave to set
     */
    public void setOctave(int octave) {
        this.octave = octave;
    }

}

class MusicScale<Note> extends Stack<Note> {

}

class BruteForce {

    PasswdCrackToMusic myPWCTM;

    public String bruteForce(int size) {
        if (myPWCTM == null) {
            myPWCTM = new PasswdCrackToMusic();
        }
        int[] password = new int[size];
        String[] finalPassword = new String[size];
        for (int i = 0; i < size; i++) {
            password[i] = 0;
            finalPassword[i] = "";
        }
        String pass = "GUEST";
        return computePermutations(size, password, 0, pass);
    }

    private String computePermutations(int size, int[] password, int position, String pass) {
        String testString = "";
        StringBuilder assemble = new StringBuilder();
        Note noteToUse = new Note("C", 0);

        for (int i = 0; i < 36; i++) {
            password[position] = i;

            if (position != size - 1) {
                testString = computePermutations(size, password, position + 1, pass);
                if (testString != "") {
                    return testString;
                }
            } else if (position == size - 1) {
                for (int j = 0; j < size; j++) {

                    switch (password[j] + 1) {
                        case 1:
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('A')), 0);
                            assemble.append(noteToUse.getValue());
                            break;
                        case 2:
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('B')), 0);
                            assemble.append(noteToUse.getValue());
                            break;
                        case 3:
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('C')), 0);
                            assemble.append(noteToUse.getValue());
                            break;
                        case 4:
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('D')), 0);
                            assemble.append(noteToUse.getValue());
                            break;
                        case 5:
                            // assemble.append("E");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('E')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 6:
                            // assemble.append("F");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('F')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 7:
                            // assemble.append("G");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('G')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 8:
                            // assemble.append("H");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('H')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 9:
                            // assemble.append("I");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('I')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 10:
                            // assemble.append("J");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('J')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 11:
                            // assemble.append("K");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('K')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 12:
                            // assemble.append("L");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('L')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 13:
                            // assemble.append("M");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('M')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 14:
                            // assemble.append("N");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('N')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 15:
                            // assemble.append("O");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('O')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 16:
                            // assemble.append("P");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('P')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 17:
                            // assemble.append("Q");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('Q')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 18:
                            // assemble.append("R");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('R')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 19:
                            // assemble.append("S");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('S')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 20:
                            // assemble.append("T");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('T')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 21:
                            //assemble.append("U");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('U')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 22:
                            // assemble.append("V");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('V')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 23:
                            // assemble.append("W");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('W')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 24:
                            // assemble.append("X");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('X')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 25:
                            // assemble.append("Y");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('Y')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 26:
                            // assemble.append("Z");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('Z')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 27:
                            // assemble.append("0");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('0')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 28:
                            // assemble.append("1");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('1')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 29:
                            // assemble.append("2");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('2')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 30:
                            // assemble.append("3");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('3')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 31:
                            // assemble.append("4");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('4')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 32:
                            /// assemble.append("5");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('5')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 33:
                            /// assemble.append("6");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('6')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 34:
                            /// assemble.append("7");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('7')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 35:
                            // assemble.append("8");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('8')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                        case 36:
                            /// assemble.append("9");
                            noteToUse = new Note(String.valueOf(this.myPWCTM.toNote('9')), 0);
                            assemble.append(noteToUse.getValue());

                            break;
                    }

                }
                // System.out.println("Password is: " + assemble);
                System.out.println(assemble);
                if (assemble.toString().equalsIgnoreCase(pass)) {
                    System.out.println("Password is: " + assemble);
                    break; //replace this with: return assemble;
                } else {
                    assemble.append("");
                }
            }

        }
        return "";
    }
}

public class PasswdCrackToMusic {

    public static void main(String[] args) {
        BruteForce myBrute = new BruteForce();
        String passwd = myBrute.bruteForce(3);
    }
    String charRange = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String toNote(char charToConvert) {
        String retVal = "#";
        MusicScale<Note> hexTonicScale = new MusicScale<>();
        hexTonicScale.add(new Note("C", 0));
        hexTonicScale.add(new Note("D#", 0));
        hexTonicScale.add(new Note("E", 0));
        hexTonicScale.add(new Note("G", 0));
        hexTonicScale.add(new Note("Ab", 0));
        hexTonicScale.add(new Note("B", 0));
        hexTonicScale.add(new Note("C", 1));

        PasswdCrackToMusic myCrack = new PasswdCrackToMusic();

        // figure out the note based on the scale and the octave based on how 
        // many times it "loops" though the scale to get the relative note
        int charPos = charRange.indexOf(charToConvert);
        int relativeNotePos = charPos % hexTonicScale.size();
        int relativeOctave = charPos / hexTonicScale.size();
        if( relativeNotePos >= 7 )
        {            
            relativeNotePos = 0;            
        }
        // System.out.println("Relative pos is: " + relativeNotePos );
        // System.out.println("Mod value is: " + relativeNotePos);
        Note noteToSend = hexTonicScale.elementAt(relativeNotePos);
        noteToSend.setOctave(relativeOctave);
        retVal = noteToSend.getValue() + ":" + noteToSend.getOctave() + "~";
        //  C E G♯and E♭ G B
        // we are using a hexatonic (8 note) scale... so mod by 8 first
        return retVal;
    }

}
