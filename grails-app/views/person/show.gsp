<!DOCTYPE html>
<%@ page import="grails.plugin.fileuploader.test.Person" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}"/>
		<title><g:message code="default.show.label" args="[entityName]" default="Show {0}"/></title>
	</head>
	<body>
		<nav>
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
			</ul>
		</nav>
		<section>
			<h1><g:message code="default.show.label" args="[entityName]"/></h1>
			<g:if test="${flash.message}">
				<aside class="message">${flash.message}</aside>
			</g:if>
			<dl>
				<dt><g:message code="person.id.label" default="Id"/></dt>
				<dd>${fieldValue(bean: personInstance, field: "id")}</dd>
				<dt><g:message code="person.name.label" default="Name"/></dt>
				<dd>${fieldValue(bean: personInstance, field: "name")}</dd>
				<dt><g:message code="person.photo.label" default="Photo"/></dt>
				<dd><img style="max-height: 250px; max-width: 250px" src="${createLink(controller: 'person', action: 'thumbnail', id: personInstance.id)}"></dd>
			</dl>
			<fieldset class="buttons">
				<g:form>
					<g:hiddenField name="id" value="${personInstance?.id}"/>
					<g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
				</g:form>
			</fieldset>
		</section>
	</body>
</html>
