dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
    url = "jdbc:mysql://localhost:3306/mubs?useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://localhost:3306/mubs?useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull"
            username = "mubs"
            password = "mubs"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://test.kaufda.com:3306/mubs?useUnicode=yes&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull"
            username = "mubs"
            password = "mubs"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect

            uri = new URI(System.env.DATABASE_URL?:"postgres://eudafkufrlfadx:9H-IeIE-yOIgYSr3mIdXrVWGNH@ec2-54-235-250-41.compute-1.amazonaws.com:5432/dcd4hr2ak1r5c9")

            url = "jdbc:postgresql://"+uri.host+uri.path
            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]
        }
    }
}
