package com.kaufda.mubs.controllers

import com.kaufda.mubs.model.Blog
import com.kaufda.mubs.model.BlogEntry
import com.kaufda.mubs.model.User

/**
 * Created by Biniam on 10/12/2014.
 */
class DashboardController {

    def blogService

    def index() {

        List<BlogEntry> blogEntries = blogService.getAllBlogEntries()

        respond User.list(params), model: [blogEntriesInstanceList: blogEntries, blogEntriesCount: BlogEntry.count()]
    }
}