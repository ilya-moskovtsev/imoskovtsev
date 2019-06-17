package ru.job4j.sqlruparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SqlRuParser implements Job {
    private static final Logger LOG = LogManager.getLogger(SqlRuParser.class.getName());
    // Scheduler
    public static final String CRON_EXPRESSION = "cronExpression";
    public static final String GROUP_1 = "group1";
    public static final String JOB_1 = "job1";
    public static final String TRIGGER_1 = "trigger1";
    Scheduler scheduler;

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Config config = new Config().init();
        SqlRuParser parser = new SqlRuParser();
        parser.schedule(config.get(CRON_EXPRESSION));
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LocalDateTime thisStartTime = LocalDateTime.now();
        Config config = new Config().init();
        ParseWeb parser = new ParseWeb();
        try (StoreSQL storeSQL = new StoreSQL(config)) {

            LocalDateTime lastStartTime = storeSQL.init().logStart(thisStartTime);
            LinkedHashSet<JavaJob> javaJobs = parser.parse(lastStartTime);

            LinkedHashSet<JavaJob> updatedJavaJobs;
            updatedJavaJobs = storeSQL.storeJobs(javaJobs).getJobs();
            updatedJavaJobs.forEach(LOG::info);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void schedule(String cronExpression) throws SchedulerException, InterruptedException {
        SchedulerFactory sf = new StdSchedulerFactory();
        scheduler = sf.getScheduler();

        JobDetail job = newJob(SqlRuParser.class)
                .withIdentity(JOB_1, GROUP_1)
                .build();

        CronTrigger trigger = newTrigger()
                .withIdentity(TRIGGER_1, GROUP_1)
                .withSchedule(cronSchedule(cronExpression))
                .build();

        scheduler.scheduleJob(job, trigger);

        scheduler.start();
    }

    @Override
    protected void finalize() throws Throwable {
        scheduler.shutdown(true);
    }
}
