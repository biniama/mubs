<%@ page import="com.kaufda.mubs.model.BlogEntry" %>



<div class="fieldcontain ${hasErrors(bean: blogEntryInstance, field: 'createdByUser', 'error')} ">
	<label for="createdByUser">
		<g:message code="blogEntry.createdByUser.label" default="Created By User" />
		
	</label>
	<g:select id="createdByUser" name="createdByUser.id" from="${com.kaufda.mubs.model.User.list()}" optionKey="id" value="${blogEntryInstance?.createdByUser?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blogEntryInstance, field: 'updatedByUser', 'error')} ">
	<label for="updatedByUser">
		<g:message code="blogEntry.updatedByUser.label" default="Updated By User" />
		
	</label>
	<g:select id="updatedByUser" name="updatedByUser.id" from="${com.kaufda.mubs.model.User.list()}" optionKey="id" value="${blogEntryInstance?.updatedByUser?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blogEntryInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="blogEntry.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="content" required="" value="${blogEntryInstance?.content}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blogEntryInstance, field: 'image', 'error')} required">
	<label for="image">
		<g:message code="blogEntry.image.label" default="Image" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="image" name="image" />

</div>

<div class="fieldcontain ${hasErrors(bean: blogEntryInstance, field: 'numberOfVisits', 'error')} required">
	<label for="numberOfVisits">
		<g:message code="blogEntry.numberOfVisits.label" default="Number Of Visits" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numberOfVisits" type="number" value="${blogEntryInstance.numberOfVisits}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: blogEntryInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="blogEntry.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${blogEntryInstance?.title}"/>

</div>

