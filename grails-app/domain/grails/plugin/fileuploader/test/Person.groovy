package grails.plugin.fileuploader.test

import grails.plugin.fileuploader.Image

class Person {
	
	String name
	Image photo

	static embedded = ["photo"]

    static constraints = {
		name blank: false, unique: true
		photo nullable: true
    }
}
