package com.kaufda.mubs.model

/**
 * Created by biniam on 10/11/2014.
 */

/**
    All models or domains extend from AbstractDomain inorder to use
    the common properties such as Auditing Fields.
*/
class Blog extends AbstractDomain {

    // Title of the blog
    String title

    // Content of the blog
    String content

    // Number of people who this blog in detail view
    Integer numberOfVisits

    // Image attached to the blog
    /*
    The image will be stored in the database for simple access
    but the best way to store it is in a file based system and
    store the file location and filename in the database.
    */
    byte[] image

    static constraints = {
    }
}
