import com.kaufda.Constants
import com.kaufda.mubs.model.BlogEntry
import com.kaufda.mubs.model.Requestmap
import com.kaufda.mubs.model.Role
import com.kaufda.mubs.model.User
import com.kaufda.mubs.model.UserRole

class BootStrap {

    def userService

    def blogService

    def init = { servletContext ->

        // Request map Stored in Database

        for (String url in [
                '/', '/index', '/index.gsp', '/**/favicon.ico',
                '/**/javascripts/**', '/**/stylesheets/**', '/**/images/**', '/assets/**',
                '/login', '/login.*', '/login/*',
                '/logout', '/logout.*', '/logout/*']) {
            Requestmap.findOrSaveByUrlAndConfigAttribute(url, 'permitAll').save()
        }

        // Permit All
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/signup', 'permitAll').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/saveUser', 'permitAll').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/getAllBlogEntriesByUserName', 'permitAll').save()

        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/blogEntryDetail/**', 'permitAll').save()

        Requestmap.findOrSaveByUrlAndConfigAttribute('/dashboard/**', 'permitAll').save()

        Requestmap.findOrSaveByUrlAndConfigAttribute('/blog/**', 'permitAll').save()

        // Permitted to Logged In Users Only
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/changePassword', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/changePasswordConfirm', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/delete/**', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/userProfile', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/editUserProfile/**', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/updateUserProfile/**', 'isFullyAuthenticated()').save()

        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/newBlogEntry', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/delete/**', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/updateBlogEntry/**', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/editBlogEntry/**', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/saveBlogEntry', 'isFullyAuthenticated()').save()

        //TBD
        //Requestmap.findOrSaveByUrlAndConfigAttribute('/blog*//**', 'permitAll').save()
        //Requestmap.findOrSaveByUrlAndConfigAttribute('/user*//**', 'permitAll').save()

        //************************************************************************************************************

        print("Test data generation in progress ...")
        // Signup user: 'tim.meyerdierks'

        User bloggerUser = User.findByUsername('tim.meyerdierks')

        if(null == bloggerUser) {

            bloggerUser = userService.saveUserInformation(
                'Tim', 'Meyerdierks', 'tim.meyerdierks@kaufda.de', 'MALE',
                'tim.meyerdierks', 'password', 'password',
                'KaufDA',
                'An international technology company.')
        }

        // Create role
        def bloggerRole = Role.findOrSaveByAuthority(Constants.ROLE_BLOGGER).save()

        // Assign role to user
        UserRole.findOrSaveByUserAndRole(bloggerUser, bloggerRole).save(flush:  true)


        // Add a New Entry, Title: '10 Reasons To Work at KaufDA'
        if(!BlogEntry.findByTitle('10 Reasons To Work at KaufDA')) {

            String blogTitle = "10 Reasons To Work at KaufDA"

            String blogContent =
            """We Think BIG\n
                We have big, ambitious goals, and our team has a singular focus on achieving them.\n

            Combining the best of the Old School with the innovation of the New School\n
                kaufDA links offline shopping with online technology, and our team
                combines talented print veterans with young digital natives.\n

            International Creativity Meets German Precision\n
                Our team has the best of both worlds: the dynamism of an
                international team with a backbone of German efficiency.\n

            An Innovative and Smart Product\n
                We’re helping to lead the charge of Berlin’s startup boom by
                creating a product that people use every day.\n

            Engage, Learn, Grow\n
                Your superior efforts for kaufDA will be met with an equal portion
                of learning and new experiences. We want you to grow along with
                us.\n

            You Are More Than Your Job\n
                Regardless of your position, we value your ideas and
                contributions.\n

            Work Hard, Play Hard\n
                Whether it’s team events, casual dress or work hours, we
                encourage a social atmosphere that allows you to be yourself.\n

            Mentorship Program\n
                You’ll never feel lost in the fray at kaufDA; we connect you right
                away with an experienced team member that will show you the
                ropes and provide you with advice.\n

            Our Doors Are Always Open\n
                Communication is key: we encourage open communication not just
                between co-workers, but also between departments and teams.\n

            Competitive Salary\n
                We know you’re valuable and we reward you accordingly.\n
            """

            // Calling the generalized saveBlogEntry method in BlogService
            blogService.saveBlogEntry(blogTitle, blogContent, bloggerUser)
        }

        //************************************************************************************************************

        // Signup user: 'biniamasnake'

        User bloggerUserBiniamAsnake = User.findByUsername('biniamasnake')

        if(null == bloggerUserBiniamAsnake) {

            bloggerUserBiniamAsnake = userService.saveUserInformation(
                'Biniam', 'Asnake', 'biniamsnake@gmail.com', 'MALE',
                'biniamasnake', 'password', 'password',
                'Computer Tips and Tricks',
                'A technology blog about Grails, Java, Android, MySql and More.')
        }

        // Create role
        def bloggerRoleBiniamAsnake = Role.findOrSaveByAuthority(Constants.ROLE_BLOGGER).save()

        // Assign role to user
        UserRole.findOrSaveByUserAndRole(bloggerUserBiniamAsnake, bloggerRoleBiniamAsnake).save(flush:  true)


        // Add a New Entry, Title: 'Grails - Introduction'
        if(!BlogEntry.findByTitle('Grails - Introduction')) {

            String blogTitle = "Grails - Introduction"

            String blogContent =
                """Grails is a full stack framework and attempts to solve as many pieces of the web development puzzle
                through the core technology and its associated plugins. Included out the box are things like:\n

                An easy to use Object Relational Mapping (ORM) layer built on Hibernate\n
                An expressive view technology called Groovy Server Pages (GSP)\n
                A controller layer built on Spring MVC\n
                A command line scripting environment built on the Groovy-powered Gant\n
                An embedded Tomcat container which is configured for on the fly reloading\n
                Dependency injection with the inbuilt Spring container\n
                Support for internationalization (i18n) built on Spring's core MessageSource concept\n
                A transactional service layer built on Spring's transaction abstraction.\n
            """

            // Calling the generalized saveBlogEntry method in BlogService
            blogService.saveBlogEntry(blogTitle, blogContent, bloggerUserBiniamAsnake)
        }

        print("Finished initializing Bootstrap (Spring Security Configurations and Test Data).")
    }

    def destroy = {
    }
}
