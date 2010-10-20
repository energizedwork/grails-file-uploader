package grails.plugin.fileuploader

import org.codehaus.groovy.grails.validation.Validateable

@Validateable
class Image {

	byte[] data
	String contentType

}
