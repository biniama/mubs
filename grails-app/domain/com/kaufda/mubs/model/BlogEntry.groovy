package com.kaufda.mubs.model

/**
 * Created by biniam on 10/11/2014.
 */

/**
    All models or domains extend from AbstractDomain inorder to use
    the common properties such as Auditing Fields.
*/
class BlogEntry extends AbstractDomain {

    // Title of the blog
    String title

    // Content of the blog
    String content

    // Number of people who this blog in detail view
    // Default value is set to 0
    Integer numberOfVisits = 0

    // Image attached to the blog
    /*
    The image will be stored in the database for simple access
    but the best way to store it is in a file based system and
    store the file location and filename in the database.
    */
    byte[] image

    static belongsTo = [blog: Blog]

    static mapping = {

        // set the constraint for a String field so that its MySQL column type is TEXT
        content type: 'text'
    }

    static constraints = {

        title unique: true, blank: false

        content blank: false

        numberOfVisits nullable: true

        image nullable: true

        /* To avoid the overhead of Grails forcing us to create a Blog instance
           every time we create a BlogEntry object
        */
        blog nullable: true
    }
}
