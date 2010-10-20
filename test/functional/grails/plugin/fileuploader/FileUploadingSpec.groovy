package grails.plugin.fileuploader

import grails.plugin.geb.GebSpec

class FileUploadingSpec extends GebSpec {

	File imageFile = new File("test/resources/blackbeard.jpg")

	def "can upload a file"() {
		given: "a user is on the create person page"
		to CreatePersonPage

		and: "they have entered a name"
		form.name = "Edward Teach"

		when: "they attach a file to the photo field"
		form.photo = imageFile.absolutePath

		and: "submit the form"
		createButton.click()

		then: "they see the attached image"
		at ShowPersonPage

		and: "the image is the one that was uploaded"
		photoURL.bytes == imageFile.bytes
	}

}
