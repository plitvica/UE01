package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.IPullPipe;
import interfaces.IPushPipe;
import transferObject.LineWithLineNumber;
import transferObject.WordArray;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by Karin on 31.10.2015.
 */

//TODO Datentyp Object muss noch durch das Array das weiter geschickt wird ausgetauscht werden
public class LineToWordFilter extends AbstractFilter<LineWithLineNumber, WordArray>{


    public LineToWordFilter(IPushPipe<WordArray> output) throws InvalidParameterException {
        super(output);
    }

    public LineToWordFilter(IPullPipe<LineWithLineNumber> input) throws InvalidParameterException {
        super(input);
    }

    @Override
    public WordArray read() throws StreamCorruptedException {
        return lineToWord(readInput());
    }

    @Override
    public void run() {

    }

    //TODO Testausgabe l�schen
    //TODO ﻿ diese Satzzeichen l�schen und sonstige Satzzeichen (, . " @ etc.)

    @Override
    public void write(LineWithLineNumber value) throws StreamCorruptedException {
        writeOutput(lineToWord(value));
    }

    private WordArray lineToWord(LineWithLineNumber value){
        WordArray wordArray = new WordArray();
        wordArray.setLineNumber(value.getLineNumber());
        wordArray.setEndOfSignal(value.isEndOfSignal());

        String[] splitArray = value.getLine().trim().split(" ");
        for(String s : splitArray){
            if(!s.equals(" ") && !s.equals("\t")){
                wordArray.addToWordArray(s);
            }
        }
        return wordArray;
    }

}
