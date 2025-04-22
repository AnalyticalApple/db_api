package xyz.matthewsavage.db_api;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class Config {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().username(Credentials.getCREDENTIALS().get("mariadb").LOGIN()).password(Credentials.getCREDENTIALS().get("mariadb").PASSWORD()).url("jdbc:mariadb://localhost:3306/manga").driverClassName("org.mariadb.jdbc.Driver").build();
    }

}
