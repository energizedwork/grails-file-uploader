package grails.plugin.fileuploader

import grails.plugin.geb.GebSpec
import grails.plugin.fileuploader.test.Person

class FileUploadingSpec extends GebSpec {

	File imageFile1 = new File("test/resources/blackbeard.jpg")
	File imageFile2 = new File("test/resources/calicojack.jpg")

	def cleanup() {
		Person.withNewSession { session ->
			Person.list()*.delete()
			session.flush()
		}
	}

	def "can upload a file"() {
		given: "a user is on the create person page"
		to CreatePersonPage

		and: "they have entered a name"
		form.name = "Edward Teach"

		when: "they attach a file to the photo field"
		form.photo = imageFile1.absolutePath

		and: "submit the form"
		createButton.click()

		then: "they see the attached image"
		at ShowPersonPage

		and: "the image is the one that was uploaded"
		photoURL.bytes == imageFile1.bytes
	}

	def "image can be replaced by uploading another"() {
		given: "an existing Person"
		def id
		Person.withNewSession {
			def person = new Person(name: "Jack Rackham", photo: new Image(data: imageFile1.bytes, contentType: "image/jpeg"))
			person.save(failOnError: true)
			id = person.id
		}

		and: "a user is on the edit person page"
		to EditPersonPage, id

		when: "the user attaches a different image to the photo field"
		form.photo = imageFile2.absolutePath

		and: "submits the form"
		updateButton.click()

		then: "the image is still visible on the show page"
		at ShowPersonPage
		photoSrc == "/person/thumbnail/$id"

		and: "the image still exists on the entity"
		def person = Person.read(id)
		person.photo != null
		person.photo.data == imageFile2.bytes
	}

	def "editing without uploading a file does not remove existing data"() {
		given: "an existing Person"
		def id
		Person.withNewSession {
			def person = new Person(name: "Edward Teach", photo: new Image(data: imageFile1.bytes, contentType: "image/jpeg"))
			person.save(failOnError: true)
			id = person.id
		}

		and: "a user is on the edit person page"
		to EditPersonPage, id

		when: "the user saves without uploading an image"
		form.name = "Blackbeard"
		updateButton.click()

		then: "the image is still visible on the show page"
		at ShowPersonPage
		photoSrc == "/person/thumbnail/$id"

		and: "the image still exists on the entity"
		def person = Person.read(id)
		person.photo != null
		person.photo.data == imageFile1.bytes
	}

}
