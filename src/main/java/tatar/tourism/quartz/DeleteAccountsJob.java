package tatar.tourism.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import tatar.tourism.dao.DaoFactory;
import tatar.tourism.dao.TokenDao;

/**
 * Created by Andrey on 27.10.2016.
 */
public class DeleteAccountsJob implements Job {
    static Logger log = Logger.getLogger(DeleteAccountsJob.class);
    static TokenDao tokenDao = DaoFactory.getDAOFactory(1).getTokenDao();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //log.info("Job "+(this.getClass()).getCanonicalName()+ " is called");
        try {
            java.util.Date newDate=new java.util.Date();
            log.trace("Accounts with tokens not confirmed to this time " + newDate.toString() + "are deleted");
            tokenDao.deleteToken(newDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
