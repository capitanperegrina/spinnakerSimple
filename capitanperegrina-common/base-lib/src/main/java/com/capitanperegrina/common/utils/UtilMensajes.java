package com.capitanperegrina.common.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.capitanperegrina.common.config.ConfigurationParametersManager;
import com.capitanperegrina.common.exceptions.ServicioErrorException;

public class UtilMensajes 
{
	public static final String DEFAULT_BASE_NAME="Messages";
	
	public static char getChar(String key)
	{
		return getChar(null, key, null, null);
	}
	
	public static char getChar(String key, String language)
	{
		return getChar(null, key, language, null);
	}
	
	public static char getChar(String key, String language, String country)
	{
		return getString(null, key, null, language, country, null).charAt(0);
	}
	
	public static char getChar(String basename, String key, String language, String country)
	{
		return getString(basename, key, null, language, country, null).charAt(0);
	}

	public static String getString(String key)
	{
		return getString(null, key, null, null, null, null);
	}

	public static String getString(String key, Object[] parameters)
	{
		return getString(null, key, parameters, null, null, null);
	}

	public static String getString(String key, String language)
	{
		return getString(null, key, null, language, null, null);
	}

	public static String getString(String key, Object[] parameters, String language)
	{
		return getString(null, key, parameters, language, null, null);
	}

	public static String getString(String key, String language, String country)
	{
		return getString(null, key, null, language, country, null);
	}
	
	public static String getString(String key, Object[] parameters, String language, String country)
	{
		return getString(null, key, parameters, language, country, null);
	}

	public static String getString(String basename, String key, String language, String country)
	{
		return getString(basename, key, null, language, country, null);
	}

	public static String getString(String basename, String key, Locale locale)
	{
		return getString(basename, key, null, null, null, locale);
	}
	
	public static String getString(String key, Locale locale)
	{
		return getString(null, key, null, null, null, locale);
	}

	public static String getStringSafe(String key, Locale locale)
	{
		try
		{
			return getString(null, key, null, null, null, locale);	
		}
		catch ( Throwable e )
		{
			return "";
		}
	}
	
	public static String getString(Locale locale,String key)
    {
        return getString(null, key, null, null, null, locale);
    }
	
    public static String getString(Locale locale,String key, Object [] parameters)
    {
        return getString(null, key, parameters, null, null, locale);
    }	
	
	public static String getString(String basename, String key, Object [] parameters, String language, String country, Locale locale)
	{
		ResourceBundle resource_bundle = getResourceBundle(basename, language, country, locale);

		if (resource_bundle != null)
		{
			String result = null;
			
			if (parameters == null)
			{
				try
				{
					result = resource_bundle.getString(key);
				}
				catch (MissingResourceException e)
				{
					return key;
				}
			}
			else
			{
				String	pattern_string = null;
	
				try
				{
					pattern_string = resource_bundle.getString(key);
				}
				catch (MissingResourceException e)
				{
					return key;
				}
	
				MessageFormat	formatter = null;
				formatter = new MessageFormat(pattern_string);
				locale = resource_bundle.getLocale();
				if (locale != null)
				{
					formatter.setLocale(locale);
				}
				result = formatter.format(parameters);
			}
	
			return result;
		}
		else
		{
			throw new ServicioErrorException();
		}
	}
	
	public static ResourceBundle getResourceBundle(String basename, String language, String country, Locale locale)
	{
		if (basename == null )
		{
		    try
		    {
		        basename = ConfigurationParametersManager.getParameter("mensajesLocalizados/basename");
		    }
		    catch ( Exception e )
		    {
		        basename = DEFAULT_BASE_NAME;    
		    }
		}

		if (locale == null )
		{
			locale = getLocale(language, country);
		}
		
		ResourceBundle result = getResourceBundle(basename, locale);
		if (result == null)
		{
			result = getResourceBundle(basename, Locale.getDefault());
		}
		
		return result;
	}
	
	public static ResourceBundle getResourceBundle(String basename, Locale locale)
	{
		ResourceBundle	result = null;
		try
		{
			if (locale !=  null)
			{
				result = ResourceBundle.getBundle(basename, locale);
			}
			else result = ResourceBundle.getBundle(basename, Locale.getDefault());
		}
		catch ( Exception e )
		{ 
			throw new ServicioErrorException( e );
		} 
		
		return result;
	}
	
	public static Locale getLocale()
	{
		return getLocale(null, null);
	}
	
	public static Locale getLocale(String language)
	{
		return getLocale(language, null);
	}
	
	public static Locale getLocale(String language, String country)
	{
		
		Locale	locale = null;
		if (null != language)
		{
			if (country == null)
			{
				locale = new Locale(language);
			}
			else
			{
				locale = new Locale(language, country);
			}
		}
		else locale = Locale.getDefault();
		
		return locale;
	}
}
