package com.kaufda.mubs.services

import com.kaufda.exceptions.UserServiceException
import com.kaufda.mubs.model.Blog
import com.kaufda.mubs.model.GenderTypeEnum
import com.kaufda.mubs.model.User
import grails.transaction.Transactional

@Transactional
class UserService {

    def messageSource

    def springSecurityService

    def blogService

    User saveUserInformation(String firstName, String lastName, String email, String gender,
                  String username, String password, String confirmPassword, String blogName, String blogDescription) {

        if(isPasswordConfirmed(password, confirmPassword)) {

            if (isValid(firstName, lastName, email, gender, username, password, confirmPassword, blogName, blogDescription)) {

                // Save the Blog object first because it needs to be assigned to the new User object to be created

                Blog blog = blogService.saveBlogByNameAndDescription(blogName, blogDescription)

                User user = new User()

                user = saveUser(user, firstName, lastName, email, gender, username, password, blog)

                return user
            }
        }
    }

    User updateUserInformation(User user, String firstName, String lastName, String email, String gender,
                               String username, String blogName, String blogDescription) {

        // Save the Blog object first because it needs to be assigned to the new User object to be created

        Blog blog = blogService.updateBlog(user.blog, blogName, blogDescription)

        user = saveUser(user, firstName, lastName, email, gender, username, user.password, blog)

        return user
    }

    User saveUser(User user, String firstName, String lastName, String email, String gender,
                  String username, String password, Blog blog) {

        user.username = username
        user.password = password
        user.firstName = firstName
        user.lastName = lastName
        user.email = email
        user.gender = GenderTypeEnum.valueOf(gender)
        user.blog = blog

        user.save(flush: true)

        return user
    }

    Boolean isPasswordConfirmed(String password, String confirmPassword) {

        if(!password.equals(confirmPassword)) {

            def errorMessage = messageSource.getMessage("error.user.password.does.not.match", null, "Password does not match. Please try again.", Locale.getDefault())

            log.error(errorMessage)

            throw (new UserServiceException(errorMessage, UserServiceException.ERROR_PASSWORD_DOES_NOT_MATCH))
        }
    }

    Boolean changePassword(String oldPassword, String newPassword, String confirmNewPassword) {

        User currentUser = springSecurityService.getCurrentUser()

        /*
            boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException
            Validates a specified "raw" password against an encoded password.

            Source: Spring Security Documenation
            http://docs.spring.io/spring-security/site/docs/2.0.7.RELEASE/apidocs/org/springframework/security/providers/encoding/PasswordEncoder.html
        */

        if(!springSecurityService?.passwordEncoder.isPasswordValid(currentUser.getPassword(), oldPassword, null)) {

            def errorMessage = messageSource.getMessage("error.incorrect.user.old.password", null, "Incorrect old password. Please try again.", Locale.getDefault())

            log.error(errorMessage)

            throw (new UserServiceException(errorMessage, UserServiceException.ERROR_INCORRECT_OLD_USER_PASSWORD))
        }

        if(!newPassword.equals(confirmNewPassword)) {

            def errorMessage = messageSource.getMessage("error.user.password.does.not.match", null, "Password does not match. Please try again.", Locale.getDefault())

            log.error(errorMessage)

            throw (new UserServiceException(errorMessage, UserServiceException.ERROR_PASSWORD_DOES_NOT_MATCH))
        }

        currentUser.password = newPassword

        currentUser.save()

        if(!currentUser.hasErrors()) {

            return true

        } else {

            def errorMessage = messageSource.getMessage("error.changing.password", null, "Error changing password.", Locale.getDefault())

            log.error(errorMessage)

            throw (new UserServiceException(errorMessage, UserServiceException.ERROR_CHANGING_PASSWORD))
        }
    }

    /**
     * Helper method
     *
     * Does not need to be in the interface since it is just a helper method used inside this class (that is why 'private' access modifier is added/given)
     * and we do not want to expose it to clients of different forms
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param gender
     * @param username
     * @param password
     * @param confirmPassword
     * @return
     */
    private Boolean isValid(String firstName, String lastName, String email, String gender,
                    String username, String password, String confirmPassword, String blogName, String blogDescription) {

        if(null == username || username.isEmpty()) {


        }

        return true
    }
}
