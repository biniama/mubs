package com.kaufda.mubs.services

import com.kaufda.Constants
import com.kaufda.exceptions.BlogServiceException
import com.kaufda.mubs.model.Blog
import com.kaufda.mubs.model.BlogEntry
import com.kaufda.mubs.model.User
import grails.transaction.Transactional

/**
 * Author: Biniam Asnake
 * All methods related to Blog and BlogEntry are defined here
 */
@Transactional
class BlogService {

    // Helps to access security related methods such as getCurrentUser()
    def springSecurityService

    // Helps to access items defined in messages.properties file
    def messageSource

    /**
     * Saves a blog entry and assigns it to a blog object
     *
     * User param is added to make the method more general and applicable
     * to multiple scenarios such as the one called from Bootstrap.groovy at startup
     *
     * @param blogTitle
     * @param blogContent
     * @param user
     *
     * @return blogEntry
     */
    BlogEntry saveBlogEntry(String blogTitle, String blogContent, User user) {

        /*
            Steps to save a blog entry
            --------------------------
            Save the BlogEntry
            Add it to Blog
        */

        // Create a new BlogEntry object
        BlogEntry blogEntry = new BlogEntry()

        // Assign values
        blogEntry.title = blogTitle

        blogEntry.content = blogContent

        // Saves it to the database
        blogEntry.save()

        // If saving the blog entry is successful, save it to the blog of the current user
        if(!blogEntry.hasErrors()) {

            // Save the blogEntry to the current user's blog list
            saveBlogByBlogEntry(blogEntry, user)

            // At this point, everything is successfully saved
            // Hence, return the object
            return blogEntry

        } else {

            // throw exception
            def errorMessage = messageSource.getMessage("error.saving.blog.entry", null, "Error saving blog entry", Locale.getDefault())

            log.error(errorMessage)

            throw (new BlogServiceException(errorMessage, BlogServiceException.ERROR_SAVING_BLOG_ENTRY))
        }
    }

    /**
     * Saves Blog By BlogEntry
     *
     * @param blogEntry
     *
     * @return blog
     */
   Blog saveBlogByBlogEntry(BlogEntry blogEntry, User currentUser) {

        // If currentUser object is null, get the user from spring security
        if(null == currentUser) {

            // Get the current logged in user from spring security service
            currentUser = springSecurityService.getCurrentUser()
        }

        // Get the blog of the current user
        Blog blogOfCurrentUser = currentUser?.blog

        // In case the user has no blog, create a new one using the default blog name
        if (null == blogOfCurrentUser)
            blogOfCurrentUser = new Blog(name: Constants.DEFAULT_BLOG_NAME, description: Constants.DEFAULT_BLOG_DESCRIPTION)

        // Add the blog entry passed to the blog of the user
        blogOfCurrentUser.addToBlogEntries(blogEntry)

        // Save the blog to the database
        blogOfCurrentUser.save(flush: true)

        // If the blog is saved successfully
        if (!blogOfCurrentUser.hasErrors()) {

            // Save it to the user object
            currentUser?.blog = blogOfCurrentUser

            // Save the user to the database
            currentUser.save(flush: true)

            // If user is successfully saved, return the user
            if(!currentUser.hasErrors()) {

                return blogOfCurrentUser

            } else {

                // throw exception
                def errorMessage = messageSource.getMessage("error.saving.blog.to.user", null, "Error saving blog to user.", Locale.getDefault())

                log.error(errorMessage)

                throw (new BlogServiceException(errorMessage, BlogServiceException.ERROR_SAVING_BLOG_TO_USER))
            }

        } else {

            // throw exception
            def errorMessage = messageSource.getMessage("error.saving.blog.entry.to.blog", null, "Error saving blog entry to blog.", Locale.getDefault())

            log.error(errorMessage)

            throw (new BlogServiceException(errorMessage, BlogServiceException.ERROR_SAVING_BLOG_ENTRY_TO_BLOG))
        }
   }

    /**
     * Saves Blog By Name And Description
     *
     * @param blogName
     * @param blogDescription
     *
     * @return blog
     */
   Blog saveBlogByNameAndDescription(String blogName, String blogDescription) {

        // Create a new Blog object
        Blog blog = new Blog()

        // Assign the values
        blog.name = blogName
        blog.description = blogDescription

        // Save the blog to the database
        blog.save(flush: true)

        // If saved successfully
        if(!blog.hasErrors()) {

            return blog

        } else {

            // handle errors
            def errorMessage = messageSource.getMessage("error.saving.blog", null, "Error saving blog.", Locale.getDefault())

            log.error(errorMessage)

            throw (new BlogServiceException(errorMessage, BlogServiceException.ERROR_SAVING_BLOG))
        }
   }

    /**
     * Returns the list of all blog entries in reverse chronological order
     *
     * @return List of Blog entries
     */
    List<BlogEntry> getAllBlogEntries() {

        return BlogEntry.list(sort: 'lastUpdated', order: 'desc')
    }

    /**
     * Gets all Blog Entries By UserName
     * @param username
     *
     * @return List of Blog entries
     */
    List<BlogEntry> getAllBlogEntriesByUserName(String username) {

        // Get the user object by username
        User selectedUser = User.findByUsername(username)

        // If the user is found
        if(null != selectedUser) {

            // Grails (GORM's and Hiberante) create criteria can be used to do queries
            return BlogEntry.createCriteria().list {

                // All blogs of the current user will be selected
                eq('blog', selectedUser?.blog)
            }

        } else {

            // Null will be returned and the controller handles the messaging
            return null
        }
    }

    /**
     * Gets the Total Number Of Visits To All Blog Entries By User
     *
     * @param user
     *
     * @return Integer
     */
    Integer getTotalNumberOfVisitsToAllBlogEntriesByUser(User user) {

        // Grails (GORM's and Hiberante) create criteria can be used to do queries
        return BlogEntry.createCriteria().get {

            // Projection is used to calculates the sum
            projections {
                sum('numberOfVisits')
            }

            // from the user's blog
            eq('blog', user?.blog)
        }
    }

    /**
     * Updates a blog entry
     *
     * @param blogEntry
     * @param blogTitle
     * @param blogContent
     *
     * @return updated blogEntry
     */
    BlogEntry updateBlogEntry(BlogEntry blogEntry, String blogTitle, String blogContent) {

        // Assign values to the passed blogEntry object, save it and return
        blogEntry.title = blogTitle

        blogEntry.content = blogContent

        blogEntry.save(flush: true)

        return blogEntry
    }

    /**
     * Updates a blog
     *
     * @param blog
     * @param blogName
     * @param blogDescription
     *
     * @return blog
     */
    Blog updateBlog(Blog blog, String blogName, String blogDescription) {

        // Assign values to the passed blog object, save it and return
        blog.name = blogName

        blog.description = blogDescription

        blog.save()

        return blog
    }
}