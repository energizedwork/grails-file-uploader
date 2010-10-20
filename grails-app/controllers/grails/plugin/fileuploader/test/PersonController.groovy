package grails.plugin.fileuploader.test

import org.springframework.web.multipart.MultipartHttpServletRequest
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND

class PersonController {

	def beforeInterceptor = {
		if (request.method == "POST" && request.contentType =~ /^multipart/) {
			MultipartHttpServletRequest multipartRequest = request
			multipartRequest.fileNames.each { fileName ->
				println "checking $fileName"
				if (multipartRequest.getFile(fileName).empty) {
					println "empty, removing from params"
					multipartRequest.multiFileMap.remove(fileName)
					params.remove(fileName)
				}
			}
			println "params: $params"
			println "files: $multipartRequest.fileMap"
		}
	}

	static scaffold = true

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

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

	def save = {
		def personInstance = new Person(params)
		if (personInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'person.label', default: 'Person'), personInstance.id])}"
			redirect(action: "show", id: personInstance.id)
		} else {
			render(view: "create", model: [personInstance: personInstance])
		}
	}

	def update = {
		def personInstance = Person.get(params.id)
		if (personInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (personInstance.version > version) {
					personInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'person.label', default: 'Person')] as Object[], "Another user has updated this Person while you were editing")
					render(view: "edit", model: [personInstance: personInstance])
					return
				}
			}
			println "just before bind, $params"
			personInstance.properties = params
			println "after bind"
			if (!personInstance.hasErrors() && personInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'person.label', default: 'Person'), personInstance.id])}"
				redirect(action: "show", id: personInstance.id)
			} else {
				render(view: "edit", model: [personInstance: personInstance])
			}
		} else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), params.id])}"
			redirect(action: "list")
		}
	}

}
