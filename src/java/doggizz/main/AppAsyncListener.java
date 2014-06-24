/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppAsyncListener implements AsyncListener {
        long start = System.currentTimeMillis();
	@Override
	public void onComplete(AsyncEvent asyncEvent) throws IOException {
                long runtime = System.currentTimeMillis() - start;
                Date date = new Date(runtime);
                DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
                String dateFormatted = formatter.format(date);
		System.out.println("AppAsyncListener Complete in "+dateFormatted+ " in ms "+runtime);
		// we can do resource cleanup activity here
	}

	@Override
	public void onError(AsyncEvent asyncEvent) throws IOException {
		System.out.println("AppAsyncListener onError");
		//we can return error response to client
	}

	@Override
	public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
		System.out.println("AppAsyncListener onStartAsync");
		//we can log the event here
	}

	@Override
	public void onTimeout(AsyncEvent asyncEvent) throws IOException {
		System.out.println("AppAsyncListener onTimeout");
		//we can send appropriate response to client
		ServletResponse response = asyncEvent.getAsyncContext().getResponse();
		PrintWriter out = response.getWriter();
		out.write("TimeOut Error in Processing");
	}

}