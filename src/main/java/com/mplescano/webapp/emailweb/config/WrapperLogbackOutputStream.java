package com.mplescano.webapp.emailweb.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;

public class WrapperLogbackOutputStream extends OutputStream {
	
    /**
     * The maximum length after which we discard the existing string buffer and
     * start anew.
     */
    private static final int MAX_LEN = 1024;

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    
    private Logger logger;
    
    private int lastByte = -1;
    
    private boolean atFirstCharLine = true;//at the first char of the line \r\n
    
    /*private Level level;*/

	public WrapperLogbackOutputStream(Logger logger/*, Level level*/) {
		this.logger = logger;
		/*this.level = level;*/
	}

	@Override
	public void write(int b) throws IOException {
		if (b == '\r') {//CR
			writeLine();
		}
		else if (b == '\n' && lastByte != '\r') {
			writeLine();
		}
		else {
			baos.write(b);
			atFirstCharLine = false;
		}
		lastByte = b;
	}
	
    public void write(byte b[]) throws IOException {
    	write(b, 0, b.length);
    }
	
    /**
     * CR-LF \r\n
     * @see java.io.OutputStream#write(byte[], int, int)
     */
    public void write(byte[] arrB, int offset, int len) throws IOException {
    	int initialPos = offset;
    	int finalPos = offset + len;
    	for (int currentPos = initialPos; currentPos < finalPos; currentPos++) {
			if (arrB[currentPos] == '\r') {//CR
				baos.write(arrB, initialPos, currentPos - initialPos);
				writeLine();
				initialPos = currentPos + 1;
			}
			else if (arrB[currentPos] == '\n') {//LF
				if (lastByte != '\r') {
					baos.write(arrB, initialPos, currentPos - initialPos);
					writeLine();
				}
				initialPos = currentPos + 1;
			}
			lastByte = arrB[currentPos];
		}
    	if ((finalPos - initialPos) > 0) {
    		baos.write(arrB, initialPos, finalPos - initialPos);
    		atFirstCharLine = false;
    	}
        
    }

    public void writeLine() throws IOException {
    	byte[] bytes = baos.toByteArray();
    	baos.reset();
    	logger.debug(new String(bytes));
    	atFirstCharLine = true;
    }
    
	@Override
	public void flush() throws IOException {
		//do nothing
		//baos.flush();
	}

	@Override
	public void close() throws IOException {
		baos.close();
	}
}