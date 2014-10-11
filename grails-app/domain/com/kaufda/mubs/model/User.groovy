package com.kaufda.mubs.model

/**
 * Created by biniam on 10/11/2014.
 */

/**
    All models or domains extend from AbstractDomain in order to use
    the common properties such as Auditing Fields.
 */
class User extends AbstractDomain {

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired = false
	boolean accountLocked = false
	boolean passwordExpired = false

    // The following fields are added for the Mubs project to persist data
    // First Name of the Blogger
    String firstName

    // Last Name of the Blogger
    String lastName

    // Email of the Blogger
    String email

    // Gender of the Blogger
    GenderTypeEnum gender

    // One User has One Blog (based on the specification
    Blog blog

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false

        firstName blank: false
        lastName blank: false
        blog nullable: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

    def beforeInsert() {

        // Call the AbstractDomain's beforeInsert() method before this one
        // to set the createdByUser field
        super.beforeInsert()
        encodePassword()
    }

    def beforeUpdate() {

        // Call the AbstractDomain's beforeInsert() method before this one
        // to set the updatedByUser field
        super.beforeUpdate()

        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
