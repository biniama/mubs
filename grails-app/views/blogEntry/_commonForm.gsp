<div class="form-group">
    <label for="blogTitle" class="col-sm-4 control-label">
        <g:message code="blog.title.label" default="Blog Title" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-sm-8">
        <g:textField class="form-control" name="blogTitle" required="" value="${blogEntryInstance?.title}"/>
    </div>
</div>

<div class="form-group">
    <label for="blogContent" class="col-sm-4 control-label">
        <g:message code="blog.content.label" default="Blog Content" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-sm-8">
        <g:textArea class="form-control" name="blogContent" rows="15" required="" value="${blogEntryInstance?.content}"/>
    </div>
</div>