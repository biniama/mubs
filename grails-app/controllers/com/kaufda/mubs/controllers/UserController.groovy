package com.kaufda.mubs.controllers

import com.kaufda.mubs.model.User

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class UserController {

    def userService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def signup() {
        render (view: 'signup')
    }

    def saveUser() {

        User user = userService.saveUser(params.firstName, params.lastName, params.email, params.gender,
                params.username, params.password, params.confirmPassword)

        if(null != user) {

            flash.message = message(code: 'user.saveUser.success.result')

        } else {

            flash.message = message(code: 'user.saveUser.failed.result', default: 'Error creating User')
        }

        // Go to home page
        render (view: '/')
    }

    def changePassword() {

        render (view: 'changePassword')
    }

    def changePasswordConfirm() {

        Boolean isPasswordChanged = userService.changePassword(params.oldPassword, params.newPassword, params.confirmNewPassword)

        if(isPasswordChanged) {

            flash.message = message(code: 'user.password.change.successful', default: 'Password is successfully changed.')

        } else {

            flash.message = message(code: 'error.user.password.change.not.successful', default: 'Password change is not successful.')
        }

        // Go to home page
        render (view: '/')

    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
    }

    def show(User userInstance) {
        respond userInstance
    }

    def create() {
        respond new User(params)
    }

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'create'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'edit'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*'{ respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
