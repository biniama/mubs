<%@ page import="com.kaufda.mubs.model.Blog" %>



<div class="fieldcontain ${hasErrors(bean: blogInstance, field: 'blogEntries', 'error')} ">
	<label for="blogEntries">
		<g:message code="blog.blogEntries.label" default="Blog Entries" />
		
	</label>
	<g:select name="blogEntries" from="${com.kaufda.mubs.model.BlogEntry.list()}" multiple="multiple" optionKey="id" size="5" value="${blogInstance?.blogEntries*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: blogInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="blog.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.kaufda.mubs.model.User.list()}" optionKey="id" required="" value="${blogInstance?.user?.id}" class="many-to-one"/>

</div>

