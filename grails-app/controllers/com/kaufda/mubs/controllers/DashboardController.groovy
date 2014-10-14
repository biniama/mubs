package com.kaufda.mubs.controllers

import com.kaufda.mubs.model.Blog
import com.kaufda.mubs.model.BlogEntry
import com.kaufda.mubs.model.User

/**
 * Created by Biniam on 10/12/2014.
 */
/**
 * Information displayed on the home page or dashboard
 */
class DashboardController extends AbstractController {

    // Service class injection used to get all blog entries
    def blogService

    def index() {

        // Gets all blog entries
        List<BlogEntry> blogEntries = blogService.getAllBlogEntries()

        // Redirect to home page and pass appropriate objects
        respond User.list(params), model: [blogEntriesInstanceList: blogEntries, blogEntriesCount: BlogEntry.count()]
    }
}