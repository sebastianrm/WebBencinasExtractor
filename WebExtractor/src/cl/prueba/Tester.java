/**
 * 
 */
package cl.prueba;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

/**
 * @author Administrador
 * 
 */
public class Tester {

	public static void jQuery_Autocomplete_Lon_Suggests_London() {

		final WebClient webClient = new WebClient(
				BrowserVersion.INTERNET_EXPLORER_8);

		webClient.setJavaScriptEnabled(true);

		webClient.setCssEnabled(true);

		webClient.setAjaxController(new NicelyResynchronizingAjaxController());

		webClient.setThrowExceptionOnFailingStatusCode(true);
		webClient.setThrowExceptionOnScriptError(true);

		// Arrange: Load the demo page
		HtmlPage autocompleteDemoPage;

		try {
			autocompleteDemoPage = webClient
					.getPage("http://jquery.bassistance.de/autocomplete/demo/");

			// Act: Type "lon" into the input box
			autocompleteDemoPage.getElementById("suggest1").type("lon");
			webClient.waitForBackgroundJavaScript(1000);

			// Assert: Suggestions should include "London"
			List<?> suggestions = autocompleteDemoPage
					.getByXPath("//div[@class='ac_results']/ul/li");

			System.out.print("");

			autocompleteDemoPage.getWebClient().closeAllWindows();

		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void testWebPostReq() {

		final WebClient webClient = new WebClient();

		// Instead of requesting the page directly we create a
		// WebRequestSettings object
		WebRequest requestSettings = null;
		try {
			requestSettings = new WebRequest(new URL(
					"http://www.bencinaenlinea.cl/web2/buscador.php"),
					HttpMethod.POST);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// region=6&comuna=&combustible=7&token=bfdde0f3ad1360c911352cb0428507fc&bandera=&orden=menor_mayor
		// Then we set the request parameters
		requestSettings.setRequestParameters(new ArrayList());
		requestSettings.getRequestParameters().add(
				new NameValuePair("region", "6"));
		requestSettings.getRequestParameters().add(
				new NameValuePair("comuna", ""));
		requestSettings.getRequestParameters().add(
				new NameValuePair("combustible", "7"));
		requestSettings.getRequestParameters().add(
				new NameValuePair("token", "bfdde0f3ad1360c911352cb0428507fc"));
		requestSettings.getRequestParameters().add(
				new NameValuePair("bandera", ""));
		requestSettings.getRequestParameters().add(
				new NameValuePair("orden", "menor_mayor"));

		// Finally, we can get the page
		try {

			HtmlPage page = webClient.getPage(requestSettings);

			System.out.println(page.asText());

		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Quite a lot of work for a simple… I imagine it won’t be hard to wrap
		// this up into a neat POST method.

	}

	private static void testActualizaComunasBen() {

		final WebClient webClient = new WebClient();

		HtmlPage page = null;

		try {
			page = webClient
					.getPage("http://www.bencinaenlinea.cl/web2/buscador.php?region=6");

			page.getWebClient().setAjaxController(
					new NicelyResynchronizingAjaxController());
			page.getWebClient().setThrowExceptionOnFailingStatusCode(true);
			page.getWebClient().setThrowExceptionOnScriptError(true);
			page.getWebClient().setJavaScriptEnabled(true);
			page.getWebClient().setCssEnabled(true);

			// esperamos
			// page.getWebClient().waitForBackgroundJavaScriptStartingBefore(10000);
			// esperamos a que carge
			// page.getWebClient().waitForBackgroundJavaScript(10000);

			final List collectedAlerts = new ArrayList();
			page.getWebClient().setAlertHandler(
					new CollectingAlertHandler(collectedAlerts));

			/**
			 * selcionamos region
			 */
			HtmlSelect selectRegion = (HtmlSelect) page
					.getElementByName("reporte_region");

			HtmlOption optionReg = selectRegion.getOptionByValue("1");

			selectRegion.setSelectedAttribute(optionReg, true);

			HtmlSelect selectcomuna = (HtmlSelect) page
					.getElementByName("reporte_comuna");

			/**
			 * selecionamos comuna
			 * 
			 */
			if (selectcomuna != null) {

				HtmlOption optionComuna = selectcomuna.getOptionByValue("");
				selectcomuna.setSelectedAttribute(optionComuna, true);

			}

			/**
			 * Seleccionamos conbustible
			 */
			HtmlSelect selectGAS = (HtmlSelect) page
					.getElementByName("reporte_combustible");

			HtmlOption optionGas = selectGAS.getOptionByValue("1");

			selectGAS.setSelectedAttribute(optionGas, true);

			/**
			 * selecionamos por orden de precios
			 * 
			 */

			HtmlSelect selectPrecios = (HtmlSelect) page
					.getElementByName("orden");

			HtmlOption optionPrecios = selectPrecios
					.getOptionByValue("menor_mayor");

			selectPrecios.setSelectedAttribute(optionPrecios, true);

			/**
			 * selecionamos por ServiCentros
			 * 
			 */

			HtmlSelect selectServiCentros = (HtmlSelect) page
					.getElementByName("reporte_bandera");

			HtmlOption optionServiCentros = selectServiCentros
					.getOptionByValue("**");

			selectServiCentros.setSelectedAttribute(optionServiCentros, true);

			// dormir();

			/**
			 * recuperamos el boton por su xpat
			 */
			List<?> byXPath = page
					.getByXPath("/html/body/table/tbody/tr[4]/td/table/tbody/tr/td/table/tbody/tr[3]/td[5]");

			// System.out.println(page.asXml());
			/**
			 * identificamos el elmento input
			 */
			HtmlButtonInput button = null;

			if (!byXPath.isEmpty()) {
				HtmlTableCell cell = (HtmlTableCell) byXPath.get(0);

				Iterator<DomNode> iterator = cell.getChildren().iterator();

				while (iterator.hasNext()) {
					DomNode next = iterator.next();
					button = (HtmlButtonInput) next;

				}

				/**
				 * hacemos click en el boton
				 */
				page = button.click();
				// button.fireEvent("onclick");

				dormir();

				HtmlTable tablaResumen = (HtmlTable) page
						.getElementById("tabla_resumen");

				
				
				Object firstByXPath = page.getFirstByXPath("/html/body/table/tbody/tr[4]/td/table/tbody/tr/td/div/table");
				
				HtmlTable tablaTotal = null;
				if (firstByXPath instanceof HtmlTable){
				tablaTotal = (HtmlTable)firstByXPath;	
				}
				
				
				System.out.println(tablaResumen.asXml());

				if (tablaTotal != null) {
					
					System.out.println(tablaTotal.asXml());
				}

			}
		} catch (FailingHttpStatusCodeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			page.getWebClient().closeAllWindows();
		}

	}

	private static void dormir() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// jQuery_Autocomplete_Lon_Suggests_London();

		testActualizaComunasBen();

		// testWebPostReq();

	}

	public static void llamaalaPagCne(String[] args) {

		final WebClient webClient = new WebClient(
				BrowserVersion.INTERNET_EXPLORER_8);

		webClient.setJavaScriptEnabled(true);

		webClient.setCssEnabled(true);

		webClient.setAjaxController(new NicelyResynchronizingAjaxController());

		webClient.setThrowExceptionOnFailingStatusCode(true);
		webClient.setThrowExceptionOnScriptError(true);

		HtmlPage page;
		try {
			page = webClient
					.getPage("http://www.bencinaenlinea.cl/web2/buscador.php?region=6");

			/**
			 * selcionamos region
			 */
			HtmlSelect selectRegion = (HtmlSelect) page
					.getElementByName("reporte_region");

			HtmlOption optionReg = selectRegion.getOptionByValue("1");
			selectRegion.setSelectedAttribute(optionReg, true);

			// lanza evento
			selectRegion.fireEvent("onchange");

			// esperamos
			webClient.waitForBackgroundJavaScriptStartingBefore(100000);
			// esperamos a que carge
			webClient.waitForBackgroundJavaScript(100000);

			/**
			 * selecionamos comuna
			 * 
			 */
			HtmlSelect selectcomuna = null;

			for (int i = 0; i < 200; i++) {

				selectcomuna = (HtmlSelect) page
						.getElementById("reporte_comuna");
				if (selectcomuna != null) {
					break;
				}
				synchronized (page) {
					try {
						page.wait(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			if (selectcomuna != null) {

				HtmlOption optionComuna = selectcomuna.getOptionByValue("");

				webClient.waitForBackgroundJavaScript(10000);

				selectcomuna.setSelectedAttribute(optionComuna, true);

				webClient.waitForBackgroundJavaScript(10000);

			}

			HtmlSelect selectGAS = (HtmlSelect) page
					.getElementById("reporte_combustible");

			HtmlOption optionGas = selectGAS.getOptionByValue("1");

			webClient.waitForBackgroundJavaScript(10000);

			selectGAS.setSelectedAttribute(optionGas, true);

			webClient.waitForBackgroundJavaScript(10000);

			/**
			 * selecionamos por orden de precios
			 * 
			 */

			HtmlSelect selectPrecios = (HtmlSelect) page
					.getElementById("orden");

			HtmlOption optionPrecios = selectPrecios
					.getOptionByValue("menor_mayor");

			webClient.waitForBackgroundJavaScript(10000);
			selectPrecios.setSelectedAttribute(optionPrecios, true);
			webClient.waitForBackgroundJavaScript(10000);

			/**
			 * selecionamos por ServiCentros
			 * 
			 */

			HtmlSelect selectServiCentros = (HtmlSelect) page
					.getElementById("reporte_bandera");

			HtmlOption optionServiCentros = selectServiCentros
					.getOptionByValue("**");
			webClient.waitForBackgroundJavaScript(10000);
			selectServiCentros.setSelectedAttribute(optionServiCentros, true);
			webClient.waitForBackgroundJavaScript(10000);

			/**
			 * por ultimo hacemos click en el boton buscar
			 */

			List<?> byXPath = page
					.getByXPath("/html/body/table/tbody/tr[4]/td/table/tbody/tr/td/table/tbody/tr[3]/td[5]/input");

			HtmlInput button1 = (HtmlInput) byXPath.get(1);
			HtmlInput button0 = (HtmlInput) byXPath.get(0);
			webClient.waitForBackgroundJavaScript(10000);
			// button.fireEvent("onclick");
			HtmlPage nueva = (HtmlPage) button0.click();
			nueva = (HtmlPage) button1.click();

			// esperamos
			nueva.getWebClient().waitForBackgroundJavaScriptStartingBefore(
					100000);
			// esperamos a que carge
			nueva.getWebClient().waitForBackgroundJavaScript(100000);

			nueva.getWebClient().setAjaxController(
					new NicelyResynchronizingAjaxController());

			List<?> byXPath3 = page

					.getByXPath("/html/body/table/tbody/tr[4]/td/table/tbody/tr/td/table[2]/tbody/tr/td/b");

			System.out.println(nueva.asXml());

			webClient.closeAllWindows();

		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void submittingForm() throws Exception {
		final WebClient webClient = new WebClient();

		// Get the first page
		final HtmlPage page1 = webClient.getPage("http://some_url");

		// Get the form that we are dealing with and within that form,
		// find the submit button and the field that we want to change.
		final HtmlForm form = page1.getFormByName("myform");

		final HtmlSubmitInput button = form.getInputByName("submitbutton");
		final HtmlTextInput textField = form.getInputByName("userid");

		// Change the value of the text field
		textField.setValueAttribute("root");

		// Now submit the form by clicking the button and get back the second
		// page.
		final HtmlPage page2 = button.click();

		webClient.closeAllWindows();
	}

	public void cookie(WebClient webClient) {
		// If the intent is just to save the authentication time try caching the
		// cookies and create new webClient per thread, this however can be a
		// bit memory intensive depending upon the what you are browsing.
		// To get the cookies after the authentication try

		Set<Cookie> cookie = webClient.getCookieManager().getCookies();

		// To set the cookies to try

		if (cookie != null) {

			Iterator<Cookie> i = cookie.iterator();

			while (i.hasNext()) {

				webClient.getCookieManager().addCookie(i.next());

			}

		}
	}
}
