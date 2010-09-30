package grails.plugin.fileuploader.test

class Person {
	
	String name
	byte[] photo

    static constraints = {
		name blank: false, unique: true
    }
}
