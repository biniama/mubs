package com.kaufda.mubs.model

class Blog extends AbstractDomain {

    static belongsTo = [user: User]

    static hasMany = [blogEntries : BlogEntry]

    static constraints = {
    }
}
