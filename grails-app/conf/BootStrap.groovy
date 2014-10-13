import com.kaufda.Constants
import com.kaufda.mubs.model.Requestmap
import com.kaufda.mubs.model.Role
import com.kaufda.mubs.model.User
import com.kaufda.mubs.model.UserRole

class BootStrap {

    def init = { servletContext ->

        // Create user
        def bloggerUser = User.findOrSaveByUsernameAndPassword('blogger', 'password').save()

        // Create role
        def bloggerRole = Role.findOrSaveByAuthority(Constants.ROLE_BLOGGER).save()

        // Assign role to user
        UserRole.create bloggerUser, bloggerRole, true

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
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user*//**', 'permitAll').save()

        //TBD
        Requestmap.findOrSaveByUrlAndConfigAttribute('/blog*//**', 'permitAll').save()

        // Permitted to Logged In Users Only
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/changePassword', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/changePasswordConfirm', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/userProfile', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/editUserProfile/**', 'isFullyAuthenticated()').save()
        Requestmap.findOrSaveByUrlAndConfigAttribute('/user/updateUserProfile/**', 'isFullyAuthenticated()').save()

        Requestmap.findOrSaveByUrlAndConfigAttribute('/blogEntry/newBlogEntry', 'isFullyAuthenticated()').save()

        print("Finished initializing Bootstrap (Spring Security Configurations and Test Data).")
    }

    def destroy = {
    }
}
