package com.kaufda.mubs.controllers

/**
 * Created by Biniam on 10/13/2014.
 */
/**
 * AbstractController is where common methods to all controllers is defined
 */
abstract class AbstractController {

    def goToHomePage() {

        // Go to home page
        redirect controller: 'dashboard', action: 'index'
    }
}
