package com.kaufda.mubs.model

/**
 * Created by biniam on 10/11/2014.
 */

/**
    AbstractDomain has the common properties used by all domain classes or models
    such as Auditing Fields. These are:

        dateCreated             Grails (GORM) automatically sets the value
        lastUpdated             Grails (GORM) automatically sets the value
        createdByUser           The user object who created the object/record
        updatedByUser           The user object who updated the object/record
 */
abstract class AbstractDomain {

    transient securityService

    Date dateCreated
    Date lastUpdated
    User createdByUser
    User updatedByUser

    def beforeInsert() {

        if(null != securityService) {

            User currentUser = securityService.getCurrentUser()

            if(null != currentUser){
                this.createdByUser = currentUser
            }
        }
    }

    def beforeUpdate() {

        if(null != securityService) {

            User currentUser = securityService.getCurrentUser()

            if(null != currentUser){
                this.updatedByUser = currentUser
            }
        }
    }

    static constraints = {

        createdByUser nullable: true
        updatedByUser nullable: true
    }
}
