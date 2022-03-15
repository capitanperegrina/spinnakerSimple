package com.capitanperegrina.common.reports;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.capitanperegrina.common.config.CheckExecutionParametersManager;
import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.utils.StackTraceUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DefaultReport<K> {

	static Logger log = Logger.getLogger( DefaultReport.class );
	
	public byte[] generaReport( String jrxmlFileName, Map<String,String> parametros, List<K> datos ) {

		log.debug( "+ " + StackTraceUtil.getCallerInfo() );
		try {

            ClassLoader classLoader = CheckExecutionParametersManager.class.getClassLoader();
            InputStream is = classLoader.getResourceAsStream( jrxmlFileName );

            /*
            URL url = classLoader.getResource( jrxmlFileName );
            String file;
            if ( SystemUtils.IS_OS_LINUX ) {
            	file = url.getFile().substring( 5 );
            } else {
            	file = url.getFile().substring( 1 );	
            }
            */

			JasperReport report = JasperCompileManager.compileReport( is );
			JRDataSource reportDatasource = new JRBeanCollectionDataSource( datos );
			JasperPrint jasperPrint = JasperFillManager.fillReport( report,parametros,reportDatasource );
			return JasperExportManager.exportReportToPdf( jasperPrint );

		} catch ( JRException e ) {
			log.error( "", e );
			throw new ServicioErrorException( "DefaultReport.generaReport.JRException", new Object[] {}, e );
		} catch ( Exception e ) {
			log.error( "", e );
			throw new ServicioErrorException( "DefaultReport.generaReport.JRException", new Object[] {}, e );
		} finally {
			log.debug( "- " + StackTraceUtil.getCallerInfo() );
		}
	}
}