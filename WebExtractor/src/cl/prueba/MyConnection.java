package cl.prueba;

import java.io.IOException;
import java.net.URL;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

public class MyConnection {
	private WebClient webClient;

	public MyConnection() {
		this.webClient = new WebClient();
	}

	// does this need to be synchronized since it is acting on a HtmlTable
	// object??
	public String getTableCaption() throws FailingHttpStatusCodeException,
			IOException {
		return getHtmlTable().getCaptionText();
	}

	// does this need to be synchronized since it is acting on a HtmlTable
	// object??
	private HtmlTable getHtmlTable() throws FailingHttpStatusCodeException,
			IOException {
		return (HtmlTable) getHtmlPage("http://foobar.com/")
				.getElementsByTagName("table").get(0);
	}

	private synchronized HtmlPage getHtmlPage(String urlStr)
			throws FailingHttpStatusCodeException, IOException {
		URL url = new URL(urlStr);
		WebRequest webRequest = new WebRequest(url);

		return this.webClient.getPage(webRequest);
	}

}
