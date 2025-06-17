package chinook.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Define Jakarta Transaction API (JTA) data source definitions for usage in a
 * development environment that can reference the `name` attribute of the `@DataSourceDefinition`
 * in `persistence.xml` using the `<jta-data-source>` element.
 * <p>
 * In a production environment where the data source definition are defined in operating system environment variables
 * the <a href="https://github.com/wildfly-extras/wildfly-datasources-galleon-pack">WildFly Datasoruces Galleon Feature-Pack</a>
 * are used as an alternative to these data source definitions.
 */
@DataSourceDefinitions({

        @DataSourceDefinition(
                name = "java:jboss/datasources/chinookDS",
                className = "org.h2.jdbcx.JdbcDataSource",
                // url="jdbc:h2:file:~/jdk/databases/h2/DMIT2015_1223_CourseDB;",
                url = "jdbc:h2:mem:dmit2015-exercises-samwu;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1;",
                user = "user2015",
                password = "Password2015"),


})

@ApplicationScoped
public class ApplicationConfig {

}