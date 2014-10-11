package com.kaufda.mubs.services

import com.kaufda.exceptions.UserServiceException
import com.kaufda.mubs.model.GenderTypeEnum
import com.kaufda.mubs.model.User
import grails.transaction.Transactional

@Transactional
class UserService {

    def messageSource

    def springSecurityService

    User saveUser(String firstName, String lastName, String email, String gender,
                  String username, String password, String confirmPassword) {

        if(!password.equals(confirmPassword)) {

            def errorMessage = messageSource.getMessage("error.user.password.does.not.match", null, "Password does not match. Please try again.", Locale.getDefault())

            log.error(errorMessage)

            throw (new UserServiceException(errorMessage, UserServiceException.ERROR_PASSWORD_DOES_NOT_MATCH))
        }

        if(isValid(firstName, lastName, email, gender, username, password, confirmPassword)) {

            User user = new User()

            user.username = username
            user.password = password
            user.firstName = firstName
            user.lastName = lastName
            user.email = email
            user.gender = GenderTypeEnum.valueOf(gender)

            user.save()

            // According to Burt Beckwith, rather than using failOnError:true, use the following

            if(!user.hasErrors()) {

                // handle success case
                return user

            } else {

                // handle failure case
                def errorMessage = messageSource.getMessage("error.user.cannot.be.created", null, "User cannot be created.", Locale.getDefault())

                log.error(errorMessage)

                throw (new UserServiceException(errorMessage, UserServiceException.ERROR_USER_CANNOT_BE_CREATED))
            }
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
                    String username, String password, String confirmPassword) {

        if(null == username || username.isEmpty()) {


        }

        return true
    }
}
