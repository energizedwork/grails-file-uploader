<!DOCTYPE html>

<%@ page import="grails.plugin.fileuploader.test.Person" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <nav>
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
        </nav>
        <section>
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            	<aside class="message">${flash.message}</aside>
            </g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="id" title="${message(code: 'person.id.label', default: 'Id')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'person.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="photo" title="${message(code: 'person.photo.label', default: 'Photo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${personInstanceList}" status="i" var="personInstance">
					<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
					
						<td><g:link action="show" id="${personInstance.id}">${fieldValue(bean: personInstance, field: "id")}</g:link></td>
					
						<td>${fieldValue(bean: personInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: personInstance, field: "photo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
            <nav class="paginateButtons">
                <g:paginate total="${personInstanceTotal}" />
            </nav>
        </section>
    </body>
</html>
