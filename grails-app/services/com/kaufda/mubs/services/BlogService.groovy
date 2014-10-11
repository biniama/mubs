package com.kaufda.mubs.services

import com.kaufda.mubs.model.Blog
import com.kaufda.mubs.model.BlogEntry
import com.kaufda.mubs.model.User
import grails.transaction.Transactional

@Transactional
class BlogService {

    def springSecurityService

    BlogEntry saveBlogEntry(String blogTitle, String blogContent) {

        User currentUser = springSecurityService.getCurrentUser()

        if(null == currentUser.blog) {

            currentUser.blog = new Blog()
        }

        BlogEntry blogEntry = new BlogEntry()

        blogEntry.title = blogTitle

        blogEntry.content = blogContent

        blogEntry.save()

        // If saving the blog entry is successful, save it to the blog of the current user
        if(!blogEntry.hasErrors()) {

            Blog blogOfCurrentUser = currentUser.blog

            blogOfCurrentUser.blogEntries.add(blogEntry)

            blogOfCurrentUser.save()

            if(!blogOfCurrentUser.hasErrors()) {

                return blogEntry

            } else {

                // throw exception
            }
        } else {

            // throw exception
        }

    }
}
