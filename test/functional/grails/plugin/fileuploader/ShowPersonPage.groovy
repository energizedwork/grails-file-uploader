package grails.plugin.fileuploader

import geb.Page

class ShowPersonPage extends Page {

	static url = "/person/show"
	static at = { title == "Show Person" }

	static content = {
		photoSrc { $("dl dd img").getAttribute("src") }
		photoURL { new URL(baseUrl, photoSrc) }
	}

	private URL getBaseUrl() {
		new URL(browser.baseUrl)
	}

}
