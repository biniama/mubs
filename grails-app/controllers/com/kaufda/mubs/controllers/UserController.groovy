package com.kaufda.mubs.controllers

import com.kaufda.mubs.model.BlogEntry
import com.kaufda.mubs.model.User

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class UserController extends AbstractController {

    // Service classes are injected to the controller
    // Grails gets this behavior from Spring
    def userService

    def blogService

    // Helps to access security related methods such as getCurrentUser()
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * Gets All Blog Entries By UserName
     *
     * @return
     */
    def getAllBlogEntriesByUserName() {

        // Call the service to gets All Blog Entries By UserName
        List<BlogEntry> blogEntries = blogService.getAllBlogEntriesByUserName(params.username)

        // handle if there are errors
        if(null == blogEntries) {

            flash.message = message(code: 'user.get.blog.entries.error.result', default: 'No blog entry found.')

            // Call the method from AbstractController
            goToHomePage()
        }

        // redirect to userBlogEntries view passing appropriate objects
        respond User.list(params), model: [blogEntriesInstanceList: blogEntries, blogEntriesCount: blogEntries?.size()], view: 'userBlogEntries'
    }

    /**
     * Redirects to signup view
     * @return
     */
    def signup() {
        render (view: 'signup')
    }

    /**
     * Calls save user information method from user service and handle any error
     * @return
     */
    def saveUser() {

        User user = userService.saveUserInformation(params.firstName, params.lastName, params.email, params.gender,
                params.username, params.password, params.confirmPassword, params.blogName, params.blogDescription)

        // According to Burt Beckwith, rather than using failOnError:true, use the following
        if (user.hasErrors()) {

            respond user.errors, view:'signup'
            return

        } else {
            // successfully saved
            flash.message = message(code: 'user.saveUser.success.result', default: 'User information saved successfully.')

            // Go to home page
            goToHomePage()
        }
    }

    /**
     * Redirects to change password view
     *
     * @return
     */
    def changePassword() {

        render (view: 'changePassword')
    }

    /**
     * Calls service method to save the change in password after validation and handles any error
     *
     * @return
     */
    def changePasswordConfirm() {

        try {

            Boolean isPasswordChanged = userService.changePassword(params.oldPassword, params.newPassword, params.confirmNewPassword)

            if(isPasswordChanged) {

                flash.message = message(code: 'user.password.change.successful', default: 'Password is successfully changed.')

            } else {

                flash.message = message(code: 'error.user.password.change.not.successful', default: 'Password change is not successful.')
            }

            // Go to home page
            goToHomePage()

        } catch(e) {

            flash.message = e.getMessage()

            changePassword()
        }
    }

    /**
     * Loads the user profile and passes Total Number Of Visits To All Blog Entries from blog service
     *
     * @return
     */
    def userProfile() {

        // Get the current user from spring security service
        User currentUser = springSecurityService.getCurrentUser()

        // Get Total Number Of Visits To All Blog Entries from blog service
        Integer totalNumberOfVisitsToAllBlogEntries = blogService.getTotalNumberOfVisitsToAllBlogEntriesByUser(currentUser)

        // Redirect to user prifle view
        respond currentUser,  model:[totalNumberOfVisitsToAllBlogEntries: totalNumberOfVisitsToAllBlogEntries]
    }

    /**
     * Loads the user profile in edit mode
     *
     * @return
     */
    def editUserProfile(User userInstance) {

        respond userInstance, view:'editUserProfile'
        return
    }

    /**
     * Does some validation, Calls the service method to update the user profile and handles any error
     *
     * @param userInstance
     * @return
     */
    def updateUserProfile(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'edit'
            return
        }

        // Calls the service method to update the user profile
        User user = userService.updateUserInformation(userInstance, params.firstName, params.lastName, params.email, params.gender,
                params.username, params.blogName, params.blogDescription)

        // According to Burt Beckwith, rather than using failOnError:true, use the following
        if (null != user && user.hasErrors()) {

            respond userInstance.errors, view:'edit'
            return

        } else {

            // successfully saved
            flash.message = message(code: 'user.update.success.result', default: 'User information updated successfully.')

            // Go to home page
            goToHomePage()
        }
    }


    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush:true

        flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.username])

       // Go to home page
        goToHomePage()
    }
}
