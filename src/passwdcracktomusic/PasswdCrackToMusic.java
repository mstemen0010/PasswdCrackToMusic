/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwdcracktomusic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author matthew.stemen
 */

class MusicalPhrase implements List<Note>
{
    Stack<Note> notes;
    
    public MusicalPhrase()
    {
        notes = new Stack<Note>();
    }

    public void parseFromString( String stringToParse, String delimiter )
    {
        String[] strArray = stringToParse.split(delimiter);
        Iterator<String> it = Arrays.asList(strArray).iterator();
        while( it.hasNext() )
        {
            String[] vals = it.next().split(":");
            String noteVal = vals[0];
            String noteOct = vals[1];
            Note newNote = new Note(noteVal, Integer.parseInt(noteOct));  
            newNote.setNoteValue();
            this.add(newNote);
        }
    }
    
    public void printStack()
    {
        Iterator<Note> it = notes.iterator();
        while( it.hasNext())
        {
            Note note = it.next();
            System.out.println("orig Note= " + note.getValue() + " value=" + note.getNatValue() + " oct=" + note.getOctave() + " isNat=" + note.isWholeNote() + " midiEvent=" + note.getMidiEvent()+ " myNoteVal=" + note.getNoteValue() );
        }
    }
    @Override
    public int size() {
        return notes.size();
    }

    @Override
    public boolean isEmpty() {
        return notes.empty();
    }

    @Override
    public boolean contains(Object o) {
        return notes.contains(o);
    }

    @Override
    public Iterator<Note> iterator() {
        return notes.iterator();
    }

    @Override
    public Object[] toArray() {
        return notes.toArray();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean containsAll(Collection c) {
        return notes.containsAll(c);
    }

    @Override
    public boolean addAll(Collection c) {
        return notes.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        notes.clear();
    }

    @Override
    public Note get(int index) {
        return notes.get(index);
    }

    @Override
    public int indexOf(Object o) {
        return notes.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(int index, Note element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Note e) {
       return notes.add(e);
    }

    @Override
    public Note set(int index, Note element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Note remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        return notes.remove(o);
    }
}
class Note {

    /**
     * @return the natValue
     */
    public String getNatValue() {
        return natValue;
    }

    /**
     * @return the midiEvent
     */
    public int getMidiEvent() {
        return midiEvent;
    }

    /**
     * @param midiEvent the midiEvent to set
     */
    public void setMidiEvent(int midiEvent) {
        this.midiEvent = midiEvent;
    }
    
    enum HalfStep
    {
        Undefined,
        Sharp,
        Flat;
        
        
    }

    enum NoteValue {
        A(21),
        B(23),
        C(24),
        D(26),
        E(28),
        F(29),
        G(31);
        private int midiEventVal;
        private HalfStep halfStep = HalfStep.Undefined;

        NoteValue(int val) {
            this.midiEventVal = val;
        }

        public int getMidiNote() {
            return this.midiEventVal;
        }
        public void setHalfStep( HalfStep newHalfStep )
        {
            this.halfStep = newHalfStep;
        }
        
        public HalfStep getHalfStep()
        {
            return this.halfStep;
        }
    }

    // Note is A octave 0 (A0) on PianoRoll
    final private int baseMidiWholeNoteEvents[] = {21, 23, 24, 26, 28, 29, 31};
    final private int baseMidiHalfNoteEvent[] = {22, 25, 27, 30};

    private int myMidiNoteEventValue = -1;

    private NoteValue myNoteValue = NoteValue.A;
    private NoteValue myNatNoteValue = NoteValue.A;

    final static private int baseMidiNoteEventMult = 12;

    public Note(String value, int octave) {        
        this.octave = octave;
        this.parseNote(value);

        this.value = value;
        if (value.contains("b") )
        {
            this.natValue = value.split("b")[0];
            this.setHalfNote();
        }
        else if (value.contains("#")) {  
            this.natValue = value.split("#")[0];
            this.setHalfNote();
        } else {
            this.natValue = value;
            this.setWholeNote();
        }

    }

    public NoteValue parseNote(String noteToParse) {
        boolean isSharp = false;
        boolean isFlat = false;

        if (noteToParse.length() > 2) {
            return null;
        }
        if (noteToParse.contains("b")) {
            this.value = noteToParse.split("b")[0];
            isFlat = true;
            this.myNoteValue.setHalfStep(HalfStep.Flat);
            this.setHalfNote();
        } else if (noteToParse.contains("#")) {
            this.value = noteToParse.split("#")[0];
            this.myNoteValue.setHalfStep(HalfStep.Sharp);
            isSharp = true;
            this.setHalfNote();
        } else {
            this.value = noteToParse;
            this.setWholeNote();
        }

        if (this.value.toUpperCase().equals("A")) {
            myNoteValue = NoteValue.A;
        } else if (this.value.toUpperCase().equals("B")) {
            myNoteValue = NoteValue.B;
        } else if (this.value.toUpperCase().equals("C")) {
            myNoteValue = NoteValue.C;            
        } else if (this.value.toUpperCase().equals("D")) {
            myNoteValue = NoteValue.D;
        } else if (this.value.toUpperCase().equals("E")) {
            myNoteValue = NoteValue.E;
        } else if (this.value.toUpperCase().equals("F")) {
            myNoteValue = NoteValue.F;
        } else if (this.value.toUpperCase().equals("G")) {
            myNoteValue = NoteValue.G;
        }
        this.myMidiNoteEventValue = myNoteValue.getMidiNote();
        if (isSharp) {
            this.myMidiNoteEventValue++;
        } else if (isFlat) {
            this.myMidiNoteEventValue--;
        }
        this.calcMidiEvent();
        return myNoteValue;
    }

    private boolean isWholeNote = false;
    private boolean isHalfNote = false;
    private String value;
    private String natValue;
    private int octave;
    private int midiBaseValue; // the midi value based octave 0 (i.e. 21 = A0, 22 = Bb0, etc)
    private int midiNote;
    private int midiEvent;

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
        this.calcMidiEvent();
    }

    public NoteValue getNoteValue()
    {
        return this.myNoteValue;
    }
    
    public void setNoteValue() {
        this.parseNote(this.getValue());
        this.calcMidiEvent();
    }

    public int getMidiNoteEventNumber() {
        // return a midi number based on the current note
        int midiNoteEventNumber = -1;

        switch (myNoteValue) {
            case A:
                midiNoteEventNumber = NoteValue.A.getMidiNote();
                break;
            case B:
                midiNoteEventNumber = NoteValue.B.getMidiNote();
                break;
            case C:
                midiNoteEventNumber = NoteValue.C.getMidiNote();
                break;
            case D:
                midiNoteEventNumber = NoteValue.D.getMidiNote();
                break;
            case E:
                midiNoteEventNumber = NoteValue.E.getMidiNote();
                break;
            case F:
                midiNoteEventNumber = NoteValue.F.getMidiNote();
                break;
            case G:
                midiNoteEventNumber = NoteValue.G.getMidiNote();
                break;
        }
        return midiNoteEventNumber;
    }

    private int calcMidiEvent() {
        int octFactor = (12 * this.octave) - 12;
        if( myNoteValue == null )
            return -1;
        int midiNoteResult = -1;
        this.midiNote = getMidiNoteEventNumber();
        midiNoteResult = this.getMidiNoteEventNumber();
        if( isHalfNote && myNoteValue.getHalfStep().equals(HalfStep.Sharp))
        {
            midiNoteResult++;
        }
        else if( isHalfNote && myNoteValue.getHalfStep().equals(HalfStep.Flat))
        {
            midiNoteResult--;
        }
       
        midiNoteResult = midiNoteResult + octFactor;
        this.midiNote = midiNoteResult;
        this.setMidiEvent(midiNoteResult);
        return midiNoteResult;
    }

    public void setWholeNote() {
        isWholeNote = true;
        isHalfNote = false;
    }

    public boolean isHalfNote()
    {
        return this.isWholeNote;
    }
    public boolean isWholeNote()
    {
        return this.isWholeNote;
    }
    
    public void setHalfNote() {
        isWholeNote = false;
        isHalfNote = true;
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

    /**
     * @return the midiNote
     */
    public int getMidiNote() {
        return midiNote;
    }

    /**
     * @param midiNote the midiNote to set
     */
    public void setMidiNote(int midiNote) {
        this.midiNote = midiNote;
        this.calcMidiEvent();
    }

}

class MusicScale<Note> extends Stack<Note> {
    public Note getRandomNoteInScale()
    {
        Note noteToRet = null;
        
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(this.size());
        noteToRet = this.get(randomInt);
    
        return noteToRet;
    }
    
}

class BruteForce implements Algorithm {

    private StringBuilder phrase = new StringBuilder();
    PasswdCrackToMusic myPWCTM;
    int iteration = 1;
    int targetIteration = 3;

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
        
        String val = computePermutations(size, password, 0, pass);
       // System.out.println("Phrase = " + val );
        this.phrase.append(val);
        setPhrase(phrase);
//return val;
        return computePermutations(size, password, 0, pass);
        
    }

    private String computePermutations(int size, int[] password, int position, String pass) {
        String testString = "";
        StringBuilder assemble = new StringBuilder();
        Note noteToUse = new Note("C", 0);
        StringBuilder song = new StringBuilder();
        if( iteration == targetIteration )
        {
            System.out.println("Iteration#" + iteration++ );
        }
        else{
           iteration++; 
        }
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
                if( iteration == targetIteration )
                {
                    getPhrase().append(assemble);
                    song.append(getPhrase());
                    System.out.println(assemble);
                }
                if (assemble.toString().equalsIgnoreCase(pass)) {
                    System.out.println("Password is: " + assemble);
                    break; //replace this with: return assemble;
                } else {
                    assemble.append("");
                }
            }

        }
        // System.out.println("Song = "  + song.toString() );
        return assemble.toString();
    }

    /**
     * @return the phrase
     */
    public StringBuilder getPhrase() {
        return phrase;
    }

    /**
     * @param phrase the phrase to set
     */
    public void setPhrase(StringBuilder phrase) {
        this.phrase = phrase;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AlgorithmResult getResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

public class PasswdCrackToMusic {

    int octaveOffSet = 1;
    static MusicScale<Note> scale = null;
    
    
    public PasswdCrackToMusic()
    {
    
            scale = new MusicScale<>();
            scale.add(new Note("C", 1));
            scale.add(new Note("Eb", 1));
            scale.add(new Note("F", 1));
            scale.add(new Note("Gb", 1));
            scale.add(new Note("G", 1));
            scale.add(new Note("Bb", 1));
            scale.add(new Note("C", 1));        
    }
    public static void main(String[] args) {
        BruteForce myBrute = new BruteForce();
        String passwd = myBrute.bruteForce(3);
        String out = myBrute.getPhrase().toString();
        MusicalPhrase myPhrase = new MusicalPhrase();
        myPhrase.parseFromString(out, "~");
        System.out.println("Phrase is: " + out );
        myPhrase.printStack();
        System.out.println("Musical Phrase contains: " + myPhrase.size() + " notes");
        MidiFile newMidiFile = new MidiFile();        
        Iterator<Note> notes = myPhrase.iterator();
        boolean salt = false; // used to mix up a repeating seqence of notes
        Note lastNoteSeen = null;
        while( notes.hasNext() )
        {
            Note note = notes.next();
            if( salt && lastNoteSeen != null && note.getNoteValue().equals(lastNoteSeen.getNoteValue()))
            {
                if( scale != null )
                {
                    note = scale.getRandomNoteInScale();
                }
            }
            newMidiFile.noteOnOffNow(4, note.getMidiEvent(), 78);
            if( salt && lastNoteSeen == null )
            {
                lastNoteSeen = note;
            }
            
                        
        }
        try
        {
            newMidiFile.writeToFile("test.mid");
        }
        catch( Exception e)
        {
            e.printStackTrace();
        }
    }
    String charRange = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
 //  D, E, F, G, A, B♭, and C. dm scale
 // C - Eb - F - Gb - G - Bb - C "blues" scale
    public String toNote(char charToConvert) {
        String retVal = "#";
       
       //  hexTonicScale.add(new Note("C", 1));

        PasswdCrackToMusic myCrack = new PasswdCrackToMusic();

        // figure out the note based on the scale and the octave based on how 
        // many times it "loops" though the scale to get the relative note
        int charPos = charRange.indexOf(charToConvert);
        int relativeNotePos = charPos % scale.size();
        int relativeOctave = charPos / scale.size();
        if (relativeNotePos >= 7) {
            relativeNotePos = 0;
        }
        // System.out.println("Relative pos is: " + relativeNotePos );
        // System.out.println("Mod value is: " + relativeNotePos);
        Note noteToSend = scale.elementAt(relativeNotePos);
        noteToSend.setOctave(relativeOctave + this.octaveOffSet );
        retVal = noteToSend.getValue() + ":" + noteToSend.getOctave() + "~";
        
        //  C E G♯and E♭ G B
        // we are using a hexatonic (8 note) scale... so mod by 8 first
        return retVal;
    }

}
