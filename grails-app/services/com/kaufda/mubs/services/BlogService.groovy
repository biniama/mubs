package com.kaufda.mubs.services

import com.kaufda.mubs.model.Blog
import com.kaufda.mubs.model.BlogEntry
import com.kaufda.mubs.model.User
import grails.transaction.Transactional

@Transactional
class BlogService {

    def springSecurityService

    BlogEntry saveBlogEntry(String blogTitle, String blogContent) {

        /*
            Steps to save a blog entry
            --------------------------
            Save the BlogEntry
            Save it to Blog
            Save the User
        */
        BlogEntry blogEntry = new BlogEntry()

        blogEntry.title = blogTitle

        blogEntry.content = blogContent

        blogEntry.save()

        // If saving the blog entry is successful, save it to the blog of the current user
        if(!blogEntry.hasErrors()) {

            // Save the blogEntry to the current user's blog list
            saveBlog(blogEntry)

            // At this point, everything is successfully saved
            return blogEntry

        } else {

            // throw exception
            // error saving blog entry
        }
    }

   Blog saveBlog(BlogEntry blogEntry) {

        User currentUser = springSecurityService.getCurrentUser()

        Blog blogOfCurrentUser = currentUser.blog

        if (null == blogOfCurrentUser)
            blogOfCurrentUser = new Blog()

        blogOfCurrentUser.addToBlogEntries(blogEntry)
        blogOfCurrentUser.user = currentUser

        blogOfCurrentUser.save()

        if (!blogOfCurrentUser.hasErrors()) {

            // Save it to the user object
            currentUser.blog = blogOfCurrentUser
            currentUser.save()

            if(!currentUser.hasErrors()) {

                return blogOfCurrentUser

            } else {

                // throw exception
                // error saving blog to user
            }

        } else {

            // throw exception
            // error saving to blog
        }
    }

}