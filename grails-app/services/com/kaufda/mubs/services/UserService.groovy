package com.kaufda.mubs.services

import com.kaufda.exceptions.UserServiceException
import com.kaufda.mubs.model.GenderTypeEnum
import com.kaufda.mubs.model.User
import grails.transaction.Transactional

@Transactional
class UserService {

    def messageSource

    User saveUser(String firstName, String lastName, String email, String gender,
                  String username, String password, String confirmedPassword) {

        if(!password.equals(confirmedPassword)) {

            def errorMessage = messageSource.getMessage("error.user.password.does.not.match", null, "Password does not match. Please try again.", Locale.getDefault())

            log.error(errorMessage)

            throw (new UserServiceException(errorMessage, UserServiceException.ERROR_PASSWORD_DOES_NOT_MATCH))
        }

        if(isValid(firstName, lastName, email, gender, username, password, confirmedPassword)) {

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

    Boolean isValid(String firstName, String lastName, String email, String gender,
                    String username, String password, String confirmedPassword) {

        if(null == username || username.isEmpty()) {


        }

        return true
    }
}
