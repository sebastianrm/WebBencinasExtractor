/**
 * 
 */
package cl.mobilLoyalti.webExtractor.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import cl.mobilLoyalti.webExtractor.bean.bencineras.Bencinas;
import cl.mobilLoyalti.webExtractor.bean.bencineras.Region;
import cl.mobilLoyalti.webExtractor.bean.bencineras.ServiCentro;
import cl.mobilLoyalti.webExtractor.logic.InsetsLogic;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow.CellIterator;

/**
 * @author Administrador
 * 
 */
public class Main {

	protected static HashMap<Integer, String> bencinas = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5503416114647714917L;

		{
			put(1, "Gasolina 93");
			put(2, "Gasolina 97");
			put(3, "Petroleo Diesel");
			put(4, "Kerosene");
			put(5, "GNC");
			put(6, "GLP Vehicular");
			put(7, "Gasolina 95");

		}
	};

	protected static HashMap<Integer, String> regiones = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5503416114647714917L;

		{
			put(1, "Arica y Parinacota");
			put(2, "Tarapacá");
			put(3, "Antofagasta");
			put(4, "Atacama");
			put(5, "Coquimbo");
			put(6, "Valparaíso");
			put(7, "Metropolitana");
			put(8, "Gral. Bernardo O'Higgins");
			put(9, "Maule");
			put(10, "Bío Bío");
			put(11, "Aracucanía");
			put(12, "Los Ríos");
			put(13, "Los Lagos");
			put(14, "Aysén Gral. C. Ibáñez del Campo");
			put(15, "Magallanes y la Antártida Chilena");
		}
	};

	private Region testActualizaComunasBen(Integer indiceBencina,
			Integer idRegion) {

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

			/**
			 * selcionamos region
			 */

			HtmlSelect selectRegion = (HtmlSelect) page
					.getElementByName("reporte_region");

			HtmlOption optionReg = selectRegion.getOptionByValue(String
					.valueOf(idRegion));

			selectRegion.setSelectedAttribute(optionReg, true);

			HtmlSelect selectcomuna = (HtmlSelect) page
					.getElementByName("reporte_comuna");

			/**
			 * selecionamos comuna
			 * 
			 */
			if (selectcomuna != null) {

				try {
					HtmlOption optionComuna = selectcomuna.getOptionByValue("");
					selectcomuna.setSelectedAttribute(optionComuna, true);
				} catch (com.gargoylesoftware.htmlunit.ElementNotFoundException e) {
					e.printStackTrace();
				}

			}

			/**
			 * Seleccionamos conbustible
			 */
			HtmlSelect selectGAS = (HtmlSelect) page
					.getElementByName("reporte_combustible");

			HtmlOption optionGas = selectGAS.getOptionByValue(String
					.valueOf(indiceBencina));

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

				// HtmlTable tablaResumen = (HtmlTable) page
				// .getElementById("tabla_resumen");
				//
				// System.out.println(tablaResumen.asXml());

				Object firstByXPath = page
						.getFirstByXPath("/html/body/table/tbody/tr[4]/td/table/tbody/tr/td/div/table");

				HtmlTable tablaTotal = null;
				if (firstByXPath instanceof HtmlTable) {
					tablaTotal = (HtmlTable) firstByXPath;
				}

				if (tablaTotal != null) {

					System.out.println(tablaTotal.asXml());

					Iterator<DomNode> iter = tablaTotal.getFirstChild()
							.getChildren().iterator();

					int i = 0;
					Region region = new Region();
					region.setNombre(regiones.get(idRegion));
					HashSet<ServiCentro> hashSet = new HashSet<ServiCentro>();

					while (iter.hasNext()) {
						i++;
						DomNode next = iter.next();

						if (next instanceof HtmlTableRow) {
							HtmlTableRow row = (HtmlTableRow) next;
							// por que el primer elemnto es el titulo

							if (i > 1) {
								CellIterator cellIterator = row
										.getCellIterator();

								int sw = 0;

								Bencinas bencina = new Bencinas();
								bencina.setDescripcion(bencinas
										.get(indiceBencina));

								ServiCentro serviCentro = new ServiCentro();
								serviCentro.setRegion(region);

								HashSet<Bencinas> setBencinas = new HashSet<Bencinas>();

								while (cellIterator.hasNext()) {

									HtmlTableCell next2 = cellIterator.next();

									String[] split = next2.asText().split(
											"[\n\r]");

									// si split es de largo 2 entonces contiene
									// la marca y la direccion

									if (split.length > 1 && sw == 0) {
										serviCentro.setEmpresa(split[0].trim());

										if (split.length == 2) {
											serviCentro.setDireccion(split[1]
													.trim());
										} else {
											serviCentro.setDireccion(split[1]
													.trim() + split[2].trim());
										}

									} else {
										if (sw == 1) {
											bencina.setPrecios(new Float(
													split[0].trim()));
										} else if (sw == 2) {
											SimpleDateFormat ft = new SimpleDateFormat(
													"yyyy-MM-dd HH:mm:ss");

											try {

												java.util.Date parse = ft
														.parse(split[0].trim());

												bencina.setFechaUlmtimaModificacion(new Timestamp(
														parse.getTime()));
											} catch (ParseException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}

									}

									bencina.setServiCentro(serviCentro);

									setBencinas.add(bencina);
									sw++;
								}
								serviCentro.setBencinas(setBencinas);
								hashSet.add(serviCentro);
							}

						}
					}
					region.setServiCentros(hashSet);
					return region;
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
		return null;

	}

	public static void main(String[] args) {

		Main main = new Main();
		ArrayList<Region> arrayList = new ArrayList<Region>();

		int idRegion = 1;
		while (idRegion <= 15) {
			int idBencina = 1;
			while (idBencina <= 7) {

				Region testActualizaComunasBen = main.testActualizaComunasBen(
						idBencina, idRegion);
				arrayList.add(testActualizaComunasBen);
				main.dormir();
				idBencina++;
			}
			idRegion++;
		}
		InsetsLogic.inserts(arrayList);
	}

	private void dormir() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
