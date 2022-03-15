package com.capitanperegrina.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import com.capitanperegrina.common.exceptions.ServicioErrorException;

public class StackTraceUtil 
{
    public static String getCallerInfo() {
    	StackTraceElement[] ste = Thread.currentThread().getStackTrace();
    	StringBuilder metodo = new StringBuilder();
    	if ( ste != null && ste.length >= 2 ) {
    		metodo.append( ste[2].getClassName() ).append( "(" )
    				.append( ste[2].getMethodName() ).append( ":" )
    				.append( ste[2].getLineNumber() ).append( ")" );
    	}
    	return metodo.toString();
    }
	
    public static String getStackTrace( Throwable throwable )
    {
        Throwable t = throwable;

        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        if ( throwable instanceof ServicioErrorException )
        {
            t = ( (ServicioErrorException) throwable ).getFinalEncapsulatedException();
        }
        
        if ( t != null )
        {
            t.printStackTrace(printWriter);        	
        }
        else
        {
        	throwable.printStackTrace(printWriter);
        }
        return result.toString();
    }
}
