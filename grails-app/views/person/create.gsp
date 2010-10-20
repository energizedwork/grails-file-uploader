<!DOCTYPE html>
<%@ page import="grails.plugin.fileuploader.test.Person" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}"/>
		<title><g:message code="default.create.label" args="[entityName]"/></title>
	</head>
	<body>
		<nav>
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
			</ul>
		</nav>
		<section>
			<h1><g:message code="default.create.label" args="[entityName]"/></h1>
			<g:if test="${flash.message}">
				<aside class="message">${flash.message}</aside>
			</g:if>
			<g:hasErrors bean="${personInstance}">
				<aside class="errors">
					<g:renderErrors bean="${personInstance}" as="list"/>
				</aside>
			</g:hasErrors>
			<g:form action="save" enctype="multipart/form-data">
				<fieldset>
					<ul>
						<li class="${hasErrors(bean: personInstance, field: 'name', 'errors')}">
							<label for="name"><g:message code="person.name.label" default="Name"/></label>
							<g:textField name="name" value="${personInstance?.name}"/>
						</li>
						<li class="${hasErrors(bean: personInstance, field: 'photo', 'errors')}">
							<label for="photo"><g:message code="person.photo.label" default="Photo"/></label>
							<input type="file" id="photo" name="photo"/>
						</li>
					</ul>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"/>
				</fieldset>
			</g:form>
		</section>
	</body>
</html>
