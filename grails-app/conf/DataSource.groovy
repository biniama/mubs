dataSource {
    pooled = true
    driverClassName = "org.postgresql.Driver"
    dialect = "org.hibernate.dialect.PostgreSQLDialect"
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
            url = "jdbc:postgresql://localhost:5432/mubs"
            username = "postgres"
            password = "Samsung"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql://127.0.0.1:5432/mubs"
            username = "root"
            password = ""
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            uri = new URI(System.env.DATABASE_URL?:"postgres://test:test@localhost/test")
            url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path
            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]
        }
    }
}