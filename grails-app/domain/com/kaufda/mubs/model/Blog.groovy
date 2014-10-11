package com.kaufda.mubs.model

class Blog {

    static belongsTo = [user: User]

    static hasMany = [blogEntries : BlogEntry]

    static constraints = {
    }
}
