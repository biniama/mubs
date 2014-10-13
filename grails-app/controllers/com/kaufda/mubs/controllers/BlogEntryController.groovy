package com.kaufda.mubs.controllers

import com.kaufda.mubs.model.BlogEntry

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * What this controller does is it accepts request from the view layer and calls the service layer
 *
 * This controller extends AbstractController where common methods to all controllers is defined
 */
class BlogEntryController extends AbstractController {

    // Service class injection
    def blogService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * renders a view
     * @return
     */
    def newBlogEntry() {

        render (view: 'newBlogEntry')
    }

    /**
     * Calls the save blog method from the service and handles errors
     * @return
     */
    def saveBlogEntry() {

        BlogEntry blogEntry = blogService.saveBlogEntry(params.blogTitle, params.blogContent, null /* user */)

        // According to Burt Beckwith, rather than using failOnError:true, use the following
        if (blogEntry.hasErrors()) {

            respond blogEntry.errors, view:'newBlogEntry'
            return

        } else {

            flash.message = message(code: 'blog.save.success.result', default: 'Blog entry saved successfully.')

            // Call the method from AbstractController
            goToHomePage()
        }
    }

    /**
     * Increment the number of people who visited this blog entry
     * Redirects to detail view of selected blog entry
     *
     * @param blogEntryInstance
     * @return
     */
    def blogEntryDetail(BlogEntry blogEntryInstance) {

        // Increment the number of people who visited this blog entry by One
        blogEntryInstance.numberOfVisits = blogEntryInstance.numberOfVisits + 1

        blogEntryInstance.save(flush: true)

        respond blogEntryInstance
    }

    /**
     * Redirects to edit blog entry view
     *
     * @param blogEntryInstance
     * @return
     */
    def editBlogEntry(BlogEntry blogEntryInstance) {

        respond blogEntryInstance
    }

    /**
     * Does some validation, Calls update blog entry method from the sercice and handles errors
     * @param blogEntryInstance
     * @return
     */
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

        // According to Burt Beckwith, rather than using failOnError:true, use the following
        if (blogEntry.hasErrors()) {

            respond blogEntry.errors, view:'newBlogEntry'
            return

        } else {

            flash.message = message(code: 'blog.update.success.result', default: 'Blog entry updated successfully.')

            // Go to home page
            goToHomePage()
        }
    }

    /**
     * Deletes the selected blog entry after confirmation dialog is displayed
     *
     * @param blogEntryInstance
     * @return
     */
    @Transactional
    def delete(BlogEntry blogEntryInstance) {

        if (blogEntryInstance == null) {
            notFound()
            return
        }

        blogEntryInstance.delete flush:true

        flash.message = message(code: 'default.deleted.message', args: [message(code: 'blogEntry.label', default: 'Blog Entry'), blogEntryInstance.title])

        // Go to home page
        goToHomePage()
    }
}
