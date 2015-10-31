package filter;

import interfaces.IPullPipe;
import interfaces.IPushPipe;
import interfaces.Readable;
import interfaces.Writeable;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;


public class ForwardingFilter<T> extends AbstractFilter <T,T> {

    public ForwardingFilter(IPullPipe<T> input, IPushPipe<T> output) throws InvalidParameterException {
        super(input, output);

    }

    public ForwardingFilter(IPullPipe<T> input) throws InvalidParameterException {
        super(input);

    }

    public ForwardingFilter(IPushPipe<T> output) throws InvalidParameterException {
        super(output);
    }

    public T read() throws StreamCorruptedException {
        return readInput();
    }

    public void write(T value) throws StreamCorruptedException {
        writeOutput(value);
    }
    


    public void run() {
        T input = null;
        try {
            do {
    
                input = readInput();
                if (input != null) {
                    writeOutput(input);
                }
                
            }while(input != null);
            sendEndSignal();
        } catch (StreamCorruptedException e) {
            // TODO Automatisch erstellter Catch-Block
            e.printStackTrace();
        }
    }
}
