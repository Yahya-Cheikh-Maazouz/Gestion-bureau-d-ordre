package com.maazouz.servicegestioncourrier;

import com.maazouz.servicegestioncourrier.MultiTenant.Config.Domain.DataSource;
import com.maazouz.servicegestioncourrier.MultiTenant.Config.Domain.DataSourceRepository;
import com.maazouz.servicegestioncourrier.MultiTenant.Interceptor.RequestInterceptor;
import com.maazouz.servicegestioncourrier.Security.SpringSecurity.SecurityAESEncoder;
import com.sun.tools.javac.Main;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SpringBootApplication
@EnableConfigurationProperties
@EnableFeignClients
@EnableEurekaClient
public class ServiceGestionCourrierApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGestionCourrierApplication.class, args);
    }

    /// THIS IS FOR AUTO DDL CREATE DATABASE FOR EACH TENANT

    @Autowired
    private DataSourceRepository configRepo;

    @Value("${decoder.key}")
    String keyDecoder;
    @Value("${prefixTenant}")
    String prefix;

    String AutoCreate="?createDatabaseIfNotExist=true";


    @Bean
    public void AutoDDLConfig() {
        List<DataSource> configTenants = configRepo.findAll();
        // configTenants.stream().forEach(item->System.out.println(item));
        if (configTenants.isEmpty()) {
            return;
        }


        for (DataSource tenant : configTenants) {

            System.out.println(tenant.toString());

            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(Objects.requireNonNull(SecurityAESEncoder.decrypt(tenant.getDriverClassName(), keyDecoder)));
            dataSource.setUrl(SecurityAESEncoder.decrypt(tenant.getUrl(),keyDecoder)+"/"+ prefix +tenant.getName()+AutoCreate);
            dataSource.setUsername(SecurityAESEncoder.decrypt(tenant.getUsername(),keyDecoder));
            dataSource.setPassword(SecurityAESEncoder.decrypt(tenant.getPassword(),keyDecoder));

            LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
            emfBean.setDataSource(dataSource);
            emfBean.setPackagesToScan("com.*"); // Here mention JPA entity path / u can leave it scans all packages

            emfBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            emfBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
            Map<String, Object> properties = new HashMap<>();
            properties.put("hibernate.default_schema", tenant.getName());
            properties.put("hibernate.hbm2ddl.auto", "update");
            emfBean.setJpaPropertyMap(properties);
            emfBean.setPersistenceUnitName(String.valueOf(dataSource));

            emfBean.afterPropertiesSet();

        }

    }


}
