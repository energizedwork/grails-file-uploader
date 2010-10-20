package grails.plugin.fileuploader

import geb.Page

class EditPersonPage extends Page {

	static url = "/person/edit"
	static at = { title == "Edit Person" }

	static content = {
		form { $("form") }
		updateButton(to: ShowPersonPage) { form.find("input.save") }
	}
	
}
