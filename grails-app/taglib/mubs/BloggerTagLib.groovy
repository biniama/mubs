package mubs

/**
 * A custom tag lib which is used to check who can access which blog entries
 * and do editing and deleting.
 */
class BloggerTagLib {

    def springSecurityService

    def isOwner = { attrs, body ->
        def loggedInUser = springSecurityService.currentUser
        def owner = attrs?.owner

        if(loggedInUser?.id == owner?.id) {
            out << body()
        }
    }
}
