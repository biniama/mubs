class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        /*  All blogs are accessible individually by separate URLs
            URL is: http://localhost:9090/mubs/blog/<username>/

            Example:
            URL is: http://localhost:9090/mubs/blog/biniamasnake/
        */
        "/blog/$username" (controller: 'user', action: 'getAllBlogEntriesByUserName')

        "/"(controller: 'dashboard', action: 'index')
        "500"(view:'/error')
	}
}
