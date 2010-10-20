package grails.plugin.fileuploader.test

import static javax.servlet.http.HttpServletResponse.*

class PersonController {

    static scaffold = true

	def thumbnail = {
		def personInstance = Person.read(params.id)
		if (!personInstance?.photo) {
			response.sendError SC_NOT_FOUND
		} else {
			response.contentLength = personInstance.photo.data.length
			response.contentType = personInstance.photo.contentType
			response.outputStream << personInstance.photo.data
		}
	}

}
