/** ProducerConsumer.java
 *
 * This is a Java implementation of the classic producer/consumer/bounded buffer 
 * program.
 *
 * @author D.L. Bailey, 
 * Systems and Computer Engineering,
 * Carleton University
 * @version 1.3, January 23, 2002
 */

public class ProducerConsumer
{
    public static void main(String[] args)
    {
        Thread producer, consumer;
        BoundedBuffer buffer;

        buffer = new BoundedBuffer(); 

        // Create the producer and consumer threads, passing each thread
        // a reference to the shared BoundedBuffer object.
        producer = new Thread(new Producer(buffer),"Producer");
        consumer = new Thread(new Consumer(buffer),"Consumer");
        producer.start();
        consumer.start();
    }
}

/**
 * Producer is the class for the producer thread.
 */
class Producer implements Runnable
{ 
    private BoundedBuffer buffer;

    public Producer(BoundedBuffer buf)
    {
        buffer = buf;
    }

    public void run()
    {
        for(int i = 0; i < 10; i++) {
            Integer item = new Integer(i);
            System.out.println(Thread.currentThread().getName() + " produced " + item);
            buffer.addLast(item);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
}

/**
 * Consumer is the class for the consumer thread.
 */
class Consumer implements Runnable
{
    private BoundedBuffer buffer;
    
    public Consumer(BoundedBuffer buf)
    {
        buffer = buf;
    }

    public void run()
    {
        for(int i = 0; i < 10; i++) {
            Object item = buffer.removeFirst();
            System.out.println(Thread.currentThread().getName() + " consumed " + item);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}
