package com.leirc.api.event;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.leirc.api.LeIRCApi;
import com.leirc.api.rsrc.Resources;

public final class EventHandler{
	private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	
	private static void captureEvent(IEvent event){
		try{
			LeIRCApi.serializer.submit(new CaptureEventThread(event));
		} catch(Exception ex){
			ex.printStackTrace(System.err);
		}
	}
	
	public static void postEvent(IEvent event, boolean capture){
		LeIRCApi.bus.post(event);
		
		if(capture){
			System.out.println("Capturing Event");
			captureEvent(event);
		}
	}
	
	private static final class CaptureEventThread implements Runnable{
		private final IEvent event;
		
		private CaptureEventThread(IEvent event){
			this.event = event;
		}
		
		@Override
		public void run(){
			String name = String.format("%s-%s.event", this.event.getUID(), formatter.format(new Date()));
			
			try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File(Resources.BIN, name)))){
				stream.writeObject(this.event);
				stream.flush();
				
				System.out.println("Done serializing Event: " + name);
			} catch(Exception ex){
				ex.printStackTrace(System.err);
			}
		}
	}
}