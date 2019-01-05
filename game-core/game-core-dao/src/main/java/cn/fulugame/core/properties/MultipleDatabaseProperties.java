package cn.fulugame.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "datasource")
@Component
@Data
public class MultipleDatabaseProperties {

    DataSourceProperties playAdminDb = new DataSourceProperties();


}
