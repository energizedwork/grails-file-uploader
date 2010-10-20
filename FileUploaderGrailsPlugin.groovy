import grails.plugin.fileuploader.ImageEditorRegistrar

class FileUploaderGrailsPlugin {

    def version = "0.1"
    def grailsVersion = "1.3.4 > *"
    def dependsOn = [jquery: "1.4.2.6 > *"]
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "Rob Fletcher"
    def authorEmail = "rob@energizedwork.com"
    def title = "Plugin summary/headline"
    def description = '''\\
Brief description of the plugin.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/file-uploader"

    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
		imageEditorRegistrar(ImageEditorRegistrar)
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }
}
