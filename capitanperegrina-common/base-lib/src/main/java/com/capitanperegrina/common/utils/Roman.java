package com.capitanperegrina.common.utils;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class Roman extends NumberFormat
{
	private static final long	serialVersionUID	= -9156445717881333628L;

	public StringBuffer format( double n, StringBuffer a, FieldPosition p )
	{
		return null;
	}

	public static class SymTab
	{
		char	symbol;

		long	value;

		public SymTab( char s, long v )
		{
			this.symbol = s;
			this.value = v;
		}
	};

	public static Roman.SymTab	syms[]	= { new Roman.SymTab( 'M', 1000 ), new Roman.SymTab( 'D', 500 ), new Roman.SymTab( 'C', 100 ), new Roman.SymTab( 'L', 50 ), new Roman.SymTab( 'X', 10 ), new Roman.SymTab( 'V', 5 ), new Roman.SymTab( 'I', 1 ) };

	public Number parse( String text, ParsePosition parsePosition )
	{

		String s = text.substring( parsePosition.getIndex() );

		long tot = 0, max = 0;
		char ch[] = s.toUpperCase().toCharArray();
		int i, p;
		for ( p = ch.length - 1; p >= 0; p-- )
		{
			for ( i = 0; i < syms.length; i++ )
			{
				if ( syms[i].symbol == ch[p] )
				{
					if ( syms[i].value >= max ) tot += ( max = syms[i].value );
					else tot -= syms[i].value;
				}
			}
		}

		// say that we parsed the whole string
		parsePosition.setIndex( s.length() );
		return new Long( tot );
	}

	public static long toLong( String s )
	{
		long tot = 0, max = 0;
		char ch[] = s.toUpperCase().toCharArray();
		int i, p;
		for ( p = ch.length - 1; p >= 0; p-- )
		{
			for ( i = 0; i < syms.length; i++ )
			{
				if ( syms[i].symbol == ch[p] )
				{
					if ( syms[i].value >= max ) tot += ( max = syms[i].value );
					else tot -= syms[i].value;
				}
			}
		}
		return tot;
	};

	public StringBuffer format( long n, StringBuffer s, FieldPosition p )
	{
		int i;
		while ( n > 0 )
		{
			for ( i = 0; i < syms.length; i++ )
			{
				if ( syms[i].value <= n )
				{
					int shift = i + ( i % 2 );
					if ( i > 0 && shift < syms.length && ( syms[i - 1].value - syms[shift].value ) <= n )
					{
						s.append( syms[shift].symbol );
						s.append( syms[i - 1].symbol );
						n = n - syms[i - 1].value + syms[shift].value;

						i = -1;
					}
					else
					{
						s.append( syms[i].symbol );
						n -= syms[i].value;
						i = -1;
					}
				}
			}
		}
		return s;
	}

	public static String toRoman( long n )
	{
		int i;
		String s;
		s = "";
		while ( n > 0 )
		{
			for ( i = 0; i < syms.length; i++ )
			{
				if ( syms[i].value <= n )
				{
					int shift = i + ( i % 2 );
					if ( i > 0 && shift < syms.length && ( syms[i - 1].value - syms[shift].value ) <= n )
					{
						s = s + syms[shift].symbol + syms[i - 1].symbol;
						n = n - syms[i - 1].value + syms[shift].value;

						i = -1;

					}
					else
					{
						s += syms[i].symbol;
						n -= syms[i].value;
						i = -1;
					}
				}
			}
		}
		return s;
	}
}
