package com.sensing.HW1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import android.hardware.SensorEvent;

public class ProducerConsumer {

	class Setup {
		void main() {
			BlockingQueue<SensorEvent> q = new ArrayBlockingQueue<SensorEvent>(10);
			Producer p = new Producer(q);
			Consumer c1 = new Consumer(q);
			Consumer c2 = new Consumer(q);
			new Thread(p).start();
			new Thread(c1).start();
			new Thread(c2).start();
		}
	}
	
	class Producer implements Runnable {
		private final BlockingQueue<SensorEvent> queue;
		Producer(BlockingQueue<SensorEvent> q) { queue = q; }
		public void run(SensorEvent event) {
			while(true) { 
		    /*...
		    event = _queue.take();
		    save to disk (event);
		    _memoryQueue.put(event);
		    ...*/
			}
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
	}
	
	class Consumer implements Runnable {
		   private final BlockingQueue<SensorEvent> queue;
		   Consumer(BlockingQueue<SensorEvent> q) { queue = q; }
		   public void run() {
		     try {
		       while(true) { consume(queue.take()); }
		     } catch (InterruptedException ex) { 
		    	 //... 
		     }
		    	 
		   }
		   void consume(Object x) {
			   // ...
		   }
	}
	
}
