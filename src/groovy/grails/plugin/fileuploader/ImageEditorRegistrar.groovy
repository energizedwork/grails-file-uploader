package grails.plugin.fileuploader

import org.springframework.beans.PropertyEditorRegistrar
import org.springframework.beans.PropertyEditorRegistry

class ImageEditorRegistrar implements PropertyEditorRegistrar {

	void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor Image, new ImageEditor()
	}

}
