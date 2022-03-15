package com.capitanperegrina.common.ecb;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The Class EcbExchangeRateParser.
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
@Component
public class EcbExchangeRateParser implements ExchangeRateParser {

	/** The logger. */
	public static final Logger logger = Logger.getLogger(EcbExchangeRateParser.class);

	/** Node attribute name which contains currency rate validity date. */
	public static final String XML_TIME_ATTR_NAME = "time";

	/** Node attribute name which contains currency code. */
	public static final String XML_CURRENCY_ATTR_NAME = "currency";

	/** Node attribute name which contains currency rate. */
	public static final String XML_RATE_ATTR_NAME = "rate";

	/** Currency rate validity date format. */
	public static final String VALIDITY_DATE_FORMAT = "y-M-d";

	/** URL path to XML file with currency rates. */
	private static final String RATES_XML_DAILY = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

	/** The Constant DATE_XPATH. */
	public static final String DATE_XPATH = "/" + EcbNamespaceContext.NS_GMS + ":Envelope/" + EcbNamespaceContext.NS_ECB
			+ ":Cube/" + EcbNamespaceContext.NS_ECB + ":Cube";

	/** The Constant CURRENCY_XPATH. */
	public static final String CURRENCY_XPATH = "./" + EcbNamespaceContext.NS_ECB + ":Cube";

	/*
	 * (non-Javadoc)
	 *
	 * @see com.capitanperegrina.common.ecb.ExchangeRateParser#parseActual()
	 */
	@Override
	public Map<String, BigDecimal> parseActual() {
		try {
			return rawParse(RATES_XML_DAILY);
		} catch (final XPathExpressionException e) {
			logger.error("Failed to evaluate XPath expression.", e);
		}
		return null;
	}

	/**
	 * Raw parse.
	 * @param xmlUrl the xml url
	 * @return the exchange rate DTO
	 * @throws XPathExpressionException the x path expression exception
	 */
	private Map<String, BigDecimal> rawParse(String xmlUrl) throws XPathExpressionException {

		final Map<String, BigDecimal> rates = new HashMap<String, BigDecimal>();

		final XPathFactory factory = XPathFactory.newInstance();
		final XPath xpath = factory.newXPath();
		xpath.setNamespaceContext(new EcbNamespaceContext());

		final XPathExpression dateXPath = xpath.compile(DATE_XPATH);
		final XPathExpression currencyXPath = xpath.compile(CURRENCY_XPATH);

		final NodeList dateNodes = (NodeList) dateXPath.evaluate(getDocument(xmlUrl), XPathConstants.NODESET);

		for (int i = 0; i < dateNodes.getLength(); i++) {
			final NodeList currencyNodes = (NodeList) currencyXPath.evaluate(dateNodes.item(i), XPathConstants.NODESET);
			for (int j = 0; j < currencyNodes.getLength(); j++) {
				rates.put(currencyNodes.item(j).getAttributes().getNamedItem(XML_CURRENCY_ATTR_NAME).getNodeValue(),
						new BigDecimal(
								currencyNodes.item(j).getAttributes().getNamedItem(XML_RATE_ATTR_NAME).getNodeValue()));
			}
		}
		return rates;
	}

	/**
	 * Loads XML file with actual currency rates from URL saved in configuration
	 * file and parses it into {@code org.w3c.dom.Document}
	 * @param xmlUrl the xml url
	 * @return parsed document or {@code null} when any error occurs
	 */
	private Document getDocument(String xmlUrl) {

		Document document = null;
		InputStream inputStream = null;

		try {
			final URL url = new URL(xmlUrl);
			inputStream = url.openStream();

			final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true);

			final DocumentBuilder builder = domFactory.newDocumentBuilder();
			document = builder.parse(inputStream);
		} catch (final MalformedURLException e) {
			logger.error("Failed to retrieve XML file with currency rates.", e);
		} catch (final IOException e) {
			logger.error("Failed to open XML file with currency rates.", e);
		} catch (final SAXException e) {
			logger.error("Failed to parse XML file with currency rates.", e);
		} catch (final ParserConfigurationException e) {
			logger.error("Failed to configure currency rates XML file parser.", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (final IOException e) {
					logger.error("Failed to close input stream fo currency rates XML file", e);
				}
			}
		}

		return document;
	}
}