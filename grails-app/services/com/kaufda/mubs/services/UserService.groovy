package com.kaufda.mubs.services

import com.kaufda.exceptions.UserServiceException
import com.kaufda.mubs.model.Blog
import com.kaufda.mubs.model.GenderTypeEnum
import com.kaufda.mubs.model.User
import grails.transaction.Transactional

/**
 * Author: Biniam Asnake
 * All methods related to User are defined here
 */
@Transactional
class UserService {

    // Injecting services (Grails uses Spring for dependecy injection)

    // Helps to access items defined in messages.properties file
    def messageSource

    // Helps to access security related methods such as getCurrentUser()
    def springSecurityService

    // The blog service defined in the project used to save and update Blog object
    def blogService

    /**
     * When a user signs up, it saves the user information and
     * creates a blog associated with the user.
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param gender
     * @param username
     * @param password
     * @param confirmPassword
     * @param blogName
     * @param blogDescription
     *
     * @return user
     */
    User saveUserInformation(String firstName, String lastName, String email, String gender,
                  String username, String password, String confirmPassword, String blogName, String blogDescription) {

        /*
            Confirm if the password and the confirmation are the same
            If correct, then continue the saving logic
            Else, throw an exception in the helper method (isPasswordConfirmed)
        */
        if(isPasswordConfirmed(password, confirmPassword)) {

            // Save the Blog object first because it needs to be assigned to the new User object to be created
            Blog blog = blogService.saveBlogByNameAndDescription(blogName, blogDescription)

            // Create a new user object
            User user = new User()

            // Call the method that assigns the values passed from the view then to the controller to the new object
            // and saves the object to the database
            user = saveUser(user, firstName, lastName, email, gender, username, password, blog)

            // return the saved user object
            return user
        }
    }

    /**
     * Accepts an existing user and properties to be changed and applies the changes to the object.
     *
     * @param user
     * @param firstName
     * @param lastName
     * @param email
     * @param gender
     * @param username
     * @param blogName
     * @param blogDescription
     *
     * @return user
     */
    User updateUserInformation(User user, String firstName, String lastName, String email, String gender,
                               String username, String blogName, String blogDescription) {

        // Update the Blog object first because it needs to be assigned to the new User object to be created
        Blog blog = blogService.updateBlog(user.blog, blogName, blogDescription)

        // Saves the new changes
        user = saveUser(user, firstName, lastName, email, gender, username, user.password, blog)

        // return the updated usr object
        return user
    }

    /**
     * Accepts user object (new for sign up and existing for updating) and
     * applies the properties and saves it to the database.
     *
     * @param user
     * @param firstName
     * @param lastName
     * @param email
     * @param gender
     * @param username
     * @param password
     * @param blog
     *
     * @return user
     */
    User saveUser(User user, String firstName, String lastName, String email, String gender,
                  String username, String password, Blog blog) {

        user.username = username
        user.password = password
        user.firstName = firstName
        user.lastName = lastName
        user.email = email

        // If gender is null, then assign null
        // Otherwise, get the GenderType enumeration and assign it to the user object
        user.gender = gender ==  null ? null : GenderTypeEnum.valueOf(gender)
        user.blog = blog

        // Saves the object to the database
        user.save(flush: true)

        // Returns the user object after saving it to the database
        return user
    }

    /**
     * A helper method to check if the password and the confirmed password are the same and
     * throws an exception
     *
     * @param password
     * @param confirmPassword
     *
     * @return Boolean (true if confirmed, false otherwise) or throws an exception
     */
    Boolean isPasswordConfirmed(String password, String confirmPassword) {

        if(!password.equals(confirmPassword)) {

            def errorMessage = messageSource.getMessage("error.user.password.does.not.match", null, "Password does not match. Please try again.", Locale.getDefault())

            log.error(errorMessage)

            throw (new UserServiceException(errorMessage, UserServiceException.ERROR_PASSWORD_DOES_NOT_MATCH))

        } else {

            return true
        }
    }

    /**
     * Accepts the old, new and confirmed password and
     * do validation, then
     * apply the change
     *
     * @param oldPassword
     * @param newPassword
     * @param confirmNewPassword
     *
     * @return Boolean (true if changed) or throws an exception
     */
    Boolean changePassword(String oldPassword, String newPassword, String confirmNewPassword) {

        User currentUser = springSecurityService.getCurrentUser()

        /*
            boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException
            Validates a specified "raw" password against an encoded password.

            Source: Spring Security Documenation
            http://docs.spring.io/spring-security/site/docs/2.0.7.RELEASE/apidocs/org/springframework/security/providers/encoding/PasswordEncoder.html
        */

        /** Check if the old password the user entered is the same as the one saves in the database
            Since Spring Security plugin stores passwords in encrypted format,
            I used isPasswordValid method from PasswordEncoder class to compare the passwords.
        */
        if(!springSecurityService?.passwordEncoder?.isPasswordValid(currentUser?.getPassword(), oldPassword, null)) {

            def errorMessage = messageSource.getMessage("error.incorrect.user.old.password", null, "Incorrect old password. Please try again.", Locale.getDefault())

            log.error(errorMessage)

            // A custom exception method is defined which extends RuntimeException
            throw (new UserServiceException(errorMessage, UserServiceException.ERROR_INCORRECT_OLD_USER_PASSWORD))
        }

        // Use the helper method defined in this class to check the password and the confirmed password
        isPasswordConfirmed(newPassword, confirmNewPassword)

        // Update the password
        currentUser.password = newPassword

        // Save it to the database
        currentUser.save()

        // If the object is saved without any error, return true
        if(!currentUser.hasErrors()) {

            return true

        } else {

            // Other wise, throw an exception using a custom exception class
            def errorMessage = messageSource.getMessage("error.changing.password", null, "Error changing password.", Locale.getDefault())

            log.error(errorMessage)

            throw (new UserServiceException(errorMessage, UserServiceException.ERROR_CHANGING_PASSWORD))
        }
    }

}
