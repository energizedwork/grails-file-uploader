package grails.plugin.fileuploader.test

import static javax.servlet.http.HttpServletResponse.*
import org.springframework.web.multipart.MultipartHttpServletRequest

class PersonController {

	def beforeInterceptor = {
		if (request.method == "POST" && request.contentType =~ /^multipart/) {
			MultipartHttpServletRequest multipartRequest = request
			multipartRequest.fileNames.each { fileName ->
				println "checking $fileName"
				if (multipartRequest.getFile(fileName).empty) {
					println "empty, removing from params"
					multipartRequest.fileMap[fileName] = null
					params.remove(fileName)
				}
			}
			println "params: $params"
			println "files: $multipartRequest.fileMap"
		}
	}

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
