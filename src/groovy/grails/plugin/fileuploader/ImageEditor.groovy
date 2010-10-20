package grails.plugin.fileuploader

import java.beans.PropertyEditorSupport
import org.springframework.web.multipart.MultipartFile

class ImageEditor extends PropertyEditorSupport {

	@Override
	void setValue(Object value) {
		if (value in MultipartFile) {
			super.setValue createImage(value)
		} else {
			super.setValue value
		}
	}

	private Image createImage(MultipartFile file) {
		if (file.empty) {
			null
		} else {
			def image = new Image()
			image.data = file.bytes
			image.contentType = file.contentType
			image
		}
	}

}
