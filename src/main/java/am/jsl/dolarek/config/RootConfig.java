package am.jsl.dolarek.config;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import am.jsl.dolarek.service.databasedump.DatabaseDumpJob;
import am.jsl.dolarek.service.databasedump.DatabaseDumpService;

import java.util.HashMap;
import java.util.Map;

/**
*Konfiguracja root zarządzana wiosną.
*Konfiguruje zadania aplikacji i PropertySourcesPlaceholderConfigurer.
*/
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"am.jsl.dolarek"})
public class RootConfig {
/**
*Wyrażenie Cron dla zadania zrzutu bazy danych.
*/
    @Value("${dolarek.db.export.cronExpression}")
    private String dbDumpCronExp;

/**
*Usługa zrzutu bazy danych.
*/
    @Autowired
    @Qualifier("databaseDumpService")
    private DatabaseDumpService databaseDumpService;

/**
*Tworzy instancję Quartz {@link JobDetail} do wykonywania zadania zrzutu bazy danych.
*@return szczegóły zadania
*/
    @Bean
    public JobDetail databaseDumpJobDetail() {
        //przekaż usługę databaseDumpService
        Map<String, Object> jobDataAsMap = new HashMap<>();
        jobDataAsMap.put("databaseDumpService", databaseDumpService);

        return JobBuilder.newJob(DatabaseDumpJob.class).withIdentity("databaseDumpJob")
                .setJobData(new JobDataMap(jobDataAsMap)).storeDurably().build();
    }

/**
*Tworzy instancję Quartz {@link Trigger} do planowania databaseDumpJob.
*@return wyzwalacz
*/
    @Bean
    public Trigger databaseDumpJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(3000).repeatForever();

        return TriggerBuilder.newTrigger().forJob(databaseDumpJobDetail())
                .withIdentity("databaseDumpJobTrigger").withSchedule(scheduleBuilder).build();
    }

/**
*Tworzy Springs {@link PropertySourcesPlaceholderConfigurer} do rozwiązywania symboli zastępczych ${...}
*w ramach definicji Spring bean.
*
*@return konfigurator PropertySourcesPlaceholder
*/
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}