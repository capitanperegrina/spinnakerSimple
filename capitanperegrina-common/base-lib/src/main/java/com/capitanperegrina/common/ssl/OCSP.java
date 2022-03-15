package com.capitanperegrina.common.ssl;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.log4j.Logger;
import org.bouncycastle.asn1.ocsp.OCSPResponseStatus;
import org.bouncycastle.ocsp.BasicOCSPResp;
import org.bouncycastle.ocsp.CertificateID;
import org.bouncycastle.ocsp.CertificateStatus;
import org.bouncycastle.ocsp.OCSPException;
import org.bouncycastle.ocsp.OCSPReq;
import org.bouncycastle.ocsp.OCSPReqGenerator;
import org.bouncycastle.ocsp.OCSPResp;
import org.bouncycastle.ocsp.SingleResp;

import com.capitanperegrina.common.exceptions.ServicioErrorException;
import com.capitanperegrina.common.exceptions.ServicioException;

/**
 * Clase utilidad para validar los certificados de DNIe mediante OSCP.
 */
public class OCSP
{
    private static final Logger log = Logger.getLogger(OCSP.class);
    
    private OCSP()
    {
    }

    /**
     * Método que valida un NIF. Si el certificado no se valida o se produce un error salta una excepción.
     * @param certUsuario certificado del DNIe a validar
     */
    public static void validaDNIe( X509Certificate certUsuario ) throws ServicioException
    {
        try
        {
            // Se carga el proveedor necesario para la petición OCSP
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            InputStream inStreamIssuer1 = OCSP.class.getResourceAsStream("/certs/ACDNIE001-SHA1.crt");
            InputStream inStreamIssuer2 = OCSP.class.getResourceAsStream("/certs/ACDNIE002-SHA1.crt");
            InputStream inStreamIssuer3 = OCSP.class.getResourceAsStream("/certs/ACDNIE003-SHA1.crt");

            X509Certificate certImportadoIssuer = null;
            String issuerCN = certUsuario.getIssuerX500Principal().getName("CANONICAL");
            CertificateFactory cfIssuer = CertificateFactory.getInstance("X.509");

            // En la validación OCSP se tendrá que usar el certificado de la CA subordinada que emitió el certificado
            if (issuerCN.contains("cn=ac dnie 001")) 
            {
                certImportadoIssuer = (X509Certificate) cfIssuer.generateCertificate(inStreamIssuer1);
            } 
            else if (issuerCN.contains("cn=ac dnie 002")) 
            {
                certImportadoIssuer = (X509Certificate) cfIssuer.generateCertificate(inStreamIssuer2);
            } 
            else if (issuerCN.contains("cn=ac dnie 003")) 
            {
                certImportadoIssuer = (X509Certificate) cfIssuer.generateCertificate(inStreamIssuer3);
            }
            
            inStreamIssuer1.close();
            inStreamIssuer2.close();
            inStreamIssuer3.close();     
            
            // Se genera la petición OCSP con el certificado de la entidad emisora y con el número de serie del certificado del titular del DNIe
            OCSPReqGenerator ocspReqGen = new OCSPReqGenerator();
            ocspReqGen.addRequest(new CertificateID(CertificateID.HASH_SHA1, certImportadoIssuer, certUsuario.getSerialNumber()));

            OCSPReq ocspReq = ocspReqGen.generate();
            compruebaCertificado( certImportadoIssuer, certUsuario, ocspReq );
        }
        catch ( CertificateException e )
        {
        	log.error( "", e );
        	throw new ServicioErrorException( "OCSP.validaDNIe.CertificateException" );
        }
        catch ( IOException e )
        {
        	log.error( "", e );
        	throw new ServicioErrorException( "OCSP.validaDNIe.IOException" );
        }
        catch ( OCSPException e )
        {
        	log.error( "", e );
        	throw new ServicioErrorException( "OCSP.validaDNIe.OCSPException" );
        }
    }
    
    private static void compruebaCertificado( X509Certificate certImportadoIssuer, X509Certificate certUsuario, OCSPReq ocspReq ) throws ServicioException
    {
    	try
    	{
            URL url = new URL("http://ocsp.dnie.es");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/ocsp-request");
            con.setRequestProperty("Accept", "application/ocsp-response");
            con.setDoOutput(true);
            
            OutputStream out = con.getOutputStream();
            DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(out));
            dataOut.write(ocspReq.getEncoded());
            dataOut.flush();
            dataOut.close();

            // Se recoge la respuesta del servidor OCSP
            InputStream in = (InputStream) con.getContent();
            OCSPResp ocspResponse = new OCSPResp(in);

            // Si la respuesta OCSP es correcta, se verifica el estado del certificado
            if (ocspResponse.getStatus() == OCSPResponseStatus.SUCCESSFUL) 
            {
                CertificateID certID = new CertificateID(CertificateID.HASH_SHA1, certImportadoIssuer, certUsuario.getSerialNumber());
                BasicOCSPResp basicResp = (BasicOCSPResp) ocspResponse.getResponseObject();
                SingleResp[] singleResps = basicResp.getResponses();
                
                verificaEstadoCertificado( singleResps, certID );
            } 
            else 
            {
            	log.info( "Petición no válida" );
            	throw new ServicioErrorException( "OCSP.verificaEstadoCertificado.peticionNoValida" );
            }
    	}
        catch ( IOException e )
        {
        	log.error( "", e );
        	throw new ServicioErrorException( "OCSP.verificaEstadoCertificado.IOException" );
        }
        catch ( OCSPException e )
        {
        	log.error( "", e );
        	throw new ServicioErrorException( "OCSP.verificaEstadoCertificado.OCSPException" );
        }

    }
    
    private static void verificaEstadoCertificado( SingleResp[] singleResps, CertificateID certID ) throws ServicioException
    {
        for ( SingleResp resp : singleResps )
        {
        	CertificateID respCertID = resp.getCertID();
        	if (certID.equals(respCertID)) 
            {
                Object status = resp.getCertStatus();
                if (status != CertificateStatus.GOOD) 
                {
                	log.info( "Certificado válido" );
                } 
                else if (status instanceof org.bouncycastle.ocsp.RevokedStatus) 
                {
                	log.info( "Certificado revocado" );
                	throw new ServicioException( "OCSP.certificadoRevocado" );
                } 
                else 
                {
                	log.info( "Certificado desconocido" );
                	throw new ServicioException( "OCSP.certificadoDesconocido" );
                }
            }
        }
    }
}

