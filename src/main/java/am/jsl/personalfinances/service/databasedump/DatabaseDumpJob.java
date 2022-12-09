package am.jsl.personalfinances.service.databasedump;

import am.jsl.personalfinances.log.AppLogger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
*Proste zadanie Quartz, które okresowo zrzuca bazę danych.
*/
@Component
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DatabaseDumpJob extends QuartzJobBean {

    private static final AppLogger log = new AppLogger(DatabaseDumpJob.class);

/**
*Usługa zrzutu bazy danych.
*/
    private DatabaseDumpService databaseDumpService;

/**
*Wykonuje rzeczywiste zadanie z podanym pakietem kontekstu.
*@param context JobExecutionContext
*@throws JobExecutionException, jeśli wystąpi błąd
*/
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        try {
            SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

            databaseDumpService.dumpDatabase();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

/**
*Ustawia usługę zrzutu bazy danych.
*@param databaseDumpService {@link DatabaseDumpService}
*/
    public void setDatabaseDumpService(DatabaseDumpService databaseDumpService) {
        this.databaseDumpService = databaseDumpService;
    }
}
