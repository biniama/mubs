package com.kaufda.mubs.controllers

import com.kaufda.mubs.model.BlogEntry

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class BlogEntryController {

    def blogService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def newBlogEntry() {

        render (view: 'newBlogEntry')
    }

    def saveBlogEntry() {

        BlogEntry blogEntry = blogService.saveBlogEntry(params.blogTitle, params.blogContent)

        if(null != blogEntry) {

            flash.message = message(code: 'blog.save.success.result', default: 'Blog entry saved successfully.')

        } else {

            flash.message = message(code: 'blog.save.failed.result', default: 'Error saving blog entry.')
        }

        // Go to home page
        redirect controller: 'dashboard', action: 'index'
    }

    def blogEntryDetail(BlogEntry blogEntryInstance) {

        // Increment the number of people who visited this blog entry
        blogEntryInstance.numberOfVisits = blogEntryInstance.numberOfVisits + 1
        blogEntryInstance.save(flush: true)

        respond blogEntryInstance
    }

    def editBlogEntry(BlogEntry blogEntryInstance) {

        respond blogEntryInstance
    }

    def updateBlogEntry(BlogEntry blogEntryInstance) {

        if (blogEntryInstance == null) {
            notFound()
            return
        }

        if (blogEntryInstance.hasErrors()) {
            respond blogEntryInstance.errors, view:'edit'
            return
        }

        BlogEntry blogEntry = blogService.updateBlogEntry(blogEntryInstance, params.blogTitle, params.blogContent)

        if(null != blogEntry) {

            flash.message = message(code: 'blog.update.success.result', default: 'Blog entry updated successfully.')

        } else {

            flash.message = message(code: 'blog.update.failed.result', default: 'Error updating blog entry.')
        }

        // Go to home page
        redirect controller: 'dashboard', action: 'index'
    }

    @Transactional
    def delete(BlogEntry blogEntryInstance) {

        if (blogEntryInstance == null) {
            notFound()
            return
        }

        blogEntryInstance.delete flush:true

        flash.message = message(code: 'default.deleted.message', args: [message(code: 'blogEntry.label', default: 'Blog Entry'), blogEntryInstance.title])

        // Go to home page
        redirect controller: 'dashboard', action: 'index'
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BlogEntry.list(params), model:[blogEntryInstanceCount: BlogEntry.count()]
    }

    def show(BlogEntry blogEntryInstance) {
        respond blogEntryInstance
    }

    def create() {
        respond new BlogEntry(params)
    }

    @Transactional
    def save(BlogEntry blogEntryInstance) {
        if (blogEntryInstance == null) {
            notFound()
            return
        }

        if (blogEntryInstance.hasErrors()) {
            respond blogEntryInstance.errors, view:'create'
            return
        }

        blogEntryInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'blogEntry.label', default: 'BlogEntry'), blogEntryInstance.id])
                redirect blogEntryInstance
            }
            '*' { respond blogEntryInstance, [status: CREATED] }
        }
    }


    @Transactional
    def update(BlogEntry blogEntryInstance) {
        if (blogEntryInstance == null) {
            notFound()
            return
        }

        if (blogEntryInstance.hasErrors()) {
            respond blogEntryInstance.errors, view:'edit'
            return
        }

        blogEntryInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'BlogEntry.label', default: 'BlogEntry'), blogEntryInstance.id])
                redirect blogEntryInstance
            }
            '*'{ respond blogEntryInstance, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogEntry.label', default: 'BlogEntry'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
