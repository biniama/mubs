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

        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/blogEntryDetail', 'permitAll').save()

        // Permitted to Logged In Users Only
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/changePassword', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/changePasswordConfirm', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/userProfile', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/editUserProfile/**', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/updateUserProfile/**', 'isFullyAuthenticated()').save()

        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/newBlogEntry', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/delete', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/updateBlogEntry', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/editBlogEntry', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/saveBlogEntry', 'isFullyAuthenticated()').save()



        //TBD
        Requestmap.findOrSaveByUrlAndConfigAttribute('/blog*//**', 'permitAll').save()

        Requestmap.findOrSaveByUrlAndConfigAttribute('/user*//**', 'permitAll').save()

        //************************************************************************************************************

        // Signup user

        User bloggerUser = userService.saveUserInformation(
                'Biniam', 'Asnake', 'biniamsnake@gmail.com', 'Male',
                'biniamasnake', 'password', 'password',
                'Computer Tips and Tricks',
                'A technology blog about Grails, Java, Android, MySql and More.')

        // Create role
        def bloggerRole = Role.findOrSaveByAuthority(Constants.ROLE_BLOGGER).save()

        // Assign role to user
        UserRole.create bloggerUser, bloggerRole, true

        // Add a New Entry

        String blogTitle = "10 REASONS TO WORK AT KAUFDA"

        String blogContent = """
            We Think BIG
                We have big, ambitious goals, and our team has a singular focus
                on achieving them.

            Combining the best of the Old School with the innovation of the New School
            kaufDA links offline shopping with online technology, and our team
            combines talented print veterans with young digital natives.
            International Creativity Meets German Precision
            Our team has the best of both worlds: the dynamism of an
            international team with a backbone of German efficiency.
            An Innovative and Smart Product
            We’re helping to lead the charge of Berlin’s startup boom by
            creating a product that people use every day.
            Engage, Learn, Grow
            Your superior efforts for kaufDA will be met with an equal portion
            of learning and new experiences. We want you to grow along with
            us.
            """

        // Calling the generalized saveBlogEntry method in BlogService
        blogService.saveBlogEntry(blogTitle, blogContent, bloggerUser)

        print("Finished initializing Bootstrap (Spring Security Configurations and Test Data).")
    }

    def destroy = {
    }
}
