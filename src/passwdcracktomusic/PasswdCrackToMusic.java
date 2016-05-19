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

class Note 
{
    public Note( String value, int octave)
    {
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

class MusicScale<Note> extends Stack<Note>
{
    
}

class BruteForce {  

    public String bruteForce(int size) {
        int[] password = new int[size];
        String[] finalPassword = new String[size];
        for (int i = 0; i < size; i++) {
            password[i] = 0;
            finalPassword[i] = "";
        }
        String pass = "GUEST";
        return computePermutations(size, password, 0, pass);
    }

    private static String computePermutations(int size, int[] password, int position, String pass) {
        String testString = "";
        StringBuilder assemble = new StringBuilder();
        
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
                            assemble.append("A");
                            break;
                        case 2:
                            assemble.append("B");
                            break;
                        case 3:
                            assemble.append("C");
                            break;
                        case 4:
                            assemble.append("D");
                            break;
                        case 5:
                            assemble.append("E");
                            break;
                        case 6:
                            assemble.append("F");
                            break;
                        case 7:
                            assemble.append("G");
                            break;
                        case 8:
                            assemble.append("H");
                            break;
                        case 9:
                            assemble.append("I");
                            break;
                        case 10:
                            assemble.append("J");
                            break;
                        case 11:
                            assemble.append("K");
                            break;
                        case 12:
                            assemble.append("L");
                            break;
                        case 13:
                            assemble.append("M");
                            break;
                        case 14:
                            assemble.append("N");
                            break;
                        case 15:
                            assemble.append("O");
                            break;
                        case 16:
                            assemble.append("P");
                            break;
                        case 17:
                            assemble.append("Q");
                            break;
                        case 18:
                            assemble.append("R");
                            break;
                        case 19:
                            assemble.append("S");
                            break;
                        case 20:
                            assemble.append("T");
                            break;
                        case 21:
                            assemble.append("U");
                            break;
                        case 22:
                            assemble.append("V");
                            break;
                        case 23:
                            assemble.append("W");
                            break;
                        case 24:
                            assemble.append("X");
                            break;
                        case 25:
                            assemble.append("Y");
                            break;
                        case 26:
                            assemble.append("Z");
                            break;
                        case 27:
                            assemble.append("0");
                            break;
                        case 28:
                            assemble.append("1");
                            break;
                        case 29:
                            assemble.append("2");
                            break;
                        case 30:
                            assemble.append("3");
                            break;
                        case 31:
                            assemble.append("4");
                            break;
                        case 32:
                            assemble.append("5");
                            break;
                        case 33:
                            assemble.append("6");
                            break;
                        case 34:
                            assemble.append("7");
                            break;
                        case 35:
                            assemble.append("8");
                            break;
                        case 36:
                            assemble.append("9");
                            break;
                    }

                }
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
     myBrute.bruteForce(5);     
    }
    String charRange = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public char toNote( char charToConvert )
    {
        char retVal = '#';
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
        System.out.println("Mod value is: " + relativeNotePos );
        Note NoteToSend = hexTonicScale.get(charPos);
        
        
        
        //  C E G♯and E♭ G B
        
        // we are using a hexatonic (8 note) scale... so mod by 8 first
        
        return retVal;
    }
  
}
