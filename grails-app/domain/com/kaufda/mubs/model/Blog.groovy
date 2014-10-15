package com.kaufda.mubs.model

class Blog extends AbstractDomain {

    static searchable = true

    String name

    String description

    static belongsTo = [user: User]

    static hasMany = [blogEntries : BlogEntry]

    static mapping = {

        // set the constraint for a String field so that its MySQL column type is TEXT
        description type: 'text'
    }

    static constraints = {

        name unique: true

        description blank: true

        blogEntries nullable: true

        /* To avoid the overhead of Grails forcing us to create a User instance
        every time we create a Blog object */
        user nullable: true
    }
}
