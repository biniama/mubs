package mubs

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
