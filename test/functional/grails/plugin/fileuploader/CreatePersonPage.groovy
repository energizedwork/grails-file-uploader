package grails.plugin.fileuploader

import geb.Page

class CreatePersonPage extends Page {

	static url = "/person/create"
	static at = { title == "Create Person" }

	static content = {
		form { $("form") }
		createButton(to: ShowPersonPage) { form.find("input.save") }
	}

}
