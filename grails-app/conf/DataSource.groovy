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
            password = "password"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            //uri = new URI(System.env.DATABASE_URL?:"postgres://avdpblkkrmgnat:mkGqcZ5jqbzSFoVJS8wH8JAfvS@ec2-54-217-238-100.eu-west-1.compute.amazonaws.com:5432/d22us5v71g45gl")
            // uri = new URI(System.env.DATABASE_URL?:"postgres://test:test@localhost/test")
            // url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path
            // username = uri.userInfo.split(":")[0]
            // password = uri.userInfo.split(":")[1]
            //url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
            //postgres://jgwypzkgqtgqfn:kGRBz6FgRhW3ScMSSJ02BcJfhH@ec2-54-217-208-102.eu-west-1.compute.amazonaws.com:5432/d9ielq8pokeai4
            url = "jdbc:postgresql://ec2-54-217-208-102.eu-west-1.compute.amazonaws.com:5432/d9ielq8pokeai4?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"
            username = "jgwypzkgqtgqfn"
            password = "kGRBz6FgRhW3ScMSSJ02BcJfhH"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            uri = new URI(System.env.DATABASE_URL?:"postgres://jgwypzkgqtgqfn:kGRBz6FgRhW3ScMSSJ02BcJfhH@ec2-54-217-208-102.eu-west-1.compute.amazonaws.com:5432/d9ielq8pokeai4")                                                    
            url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path
            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]
        }
    }
}
