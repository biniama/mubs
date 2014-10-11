package com.kaufda.mubs.controllers

import com.kaufda.mubs.model.Blog

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BlogController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Blog.list(params), model: [blogInstanceCount: Blog.count()]
    }

    def show(Blog blogInstance) {
        respond blogInstance
    }

    def create() {
        respond new Blog(params)
    }

    @Transactional
    def save(Blog blogInstance) {
        if (blogInstance == null) {
            notFound()
            return
        }

        if (blogInstance.hasErrors()) {
            respond blogInstance.errors, view: 'create'
            return
        }

        blogInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'blog.label', default: 'Blog'), blogInstance.id])
                redirect blogInstance
            }
            '*' { respond blogInstance, [status: CREATED] }
        }
    }

    def edit(Blog blogInstance) {
        respond blogInstance
    }

    @Transactional
    def update(Blog blogInstance) {
        if (blogInstance == null) {
            notFound()
            return
        }

        if (blogInstance.hasErrors()) {
            respond blogInstance.errors, view: 'edit'
            return
        }

        blogInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Blog.label', default: 'Blog'), blogInstance.id])
                redirect blogInstance
            }
            '*' { respond blogInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Blog blogInstance) {

        if (blogInstance == null) {
            notFound()
            return
        }

        blogInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Blog.label', default: 'Blog'), blogInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'blog.label', default: 'Blog'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
