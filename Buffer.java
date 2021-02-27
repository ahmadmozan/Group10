/**
 * 
 */
package iteration1;

/**
 * @author bs
 *
 */
public class Buffer {
	
	// a simple ring buffer is used to hold the data
	
	// buffer capacity
	private static final int SIZE = 100;
	private Object[] buffer = new Object[SIZE]; 
	private int inIndex = 0, outIndex = 0, count = 0;
	
	// If true, there is room for at least one object // in the buffer.
	private boolean writeable = true;
	
	// If true, there is at least one object stored // in the buffer.
	private boolean readable = false;
	
	
	
	// Method adds new object to bottom of buffer array.
	public synchronized void addLast(Object item) {
		while (!writeable) {
			try { wait();} 
			catch (InterruptedException e){ System.err.println(e); }
			}
		buffer[inIndex] = item; readable = true;
		inIndex = (inIndex + 1) % SIZE;
		count++;
		if (count == SIZE) { writeable = false;}
		notifyAll(); 
	}

	// Method returns object at the top of buffer array.	
	public synchronized Object removeFirst()
	 {
	      Object item;
	while (!readable) { 
		try { wait();} 
		catch (InterruptedException e){ System.err.println(e); }
		}
	item = buffer[outIndex]; writeable = true;
	outIndex = (outIndex + 1) % SIZE; count--;
	
	if (count == 0) { readable = false;}
	notifyAll();
	
	return item; 
	}
	

}
