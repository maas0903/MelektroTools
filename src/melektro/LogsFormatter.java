/**
 * melektro description
 */
package melektro;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 *
 * @author Marius
 * <br>
 Usage: static final Logger internalLogger = new
 LogsFormatter().setLogging(TestTimers.class.getName(), Level.ALL);
 */
public class LogsFormatter extends Formatter {

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder(1000);
        builder.append(df.format(new Date(record.getMillis()))).append(" - ");
        builder.append("[").append(record.getSourceClassName()).append(".");
        builder.append(record.getSourceMethodName()).append("] - ");
        builder.append("[").append(record.getLevel()).append("] - ");
        builder.append(formatMessage(record));
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public String getHead(Handler h) {
        return super.getHead(h);
    }

    @Override
    public String getTail(Handler h) {
        return super.getTail(h);
    }

    /**
     *
     * @param name could be "your .java".class.getName()
     * @param logLevel The Level you want. For example Level.ALL
     * @return a Logger instance
     *
     *
     */
    static Logger internalLogger = null;
    
    public static void Log(String message) {
        System.out.println(message);
        internalLogger.log(Level.ALL, message);
    }


    public Logger setLogging(Level logLevel) {
        Calendar cal = Calendar.getInstance();
        Date dtNow = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String name = "./Logs_"+dateFormat.format(dtNow)+".log";
        Logger logger = Logger.getLogger(name);
        //Handler consoleHandler = null;
        Handler fileHandler = null;
        LogsFormatter logsFormatter = null;
        try {
            //Creating consoleHandler and fileHandler
            //consoleHandler = new ConsoleHandler();

            //fileHandler = new FileHandler("./" + this.getClass().getCanonicalName()+sTmp+".log");
            fileHandler = new FileHandler(name, true);
	    //Assigning handlers to LOGGER object
            logsFormatter = new LogsFormatter();

            //logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);
            //Setting levels to handlers and LOGGER
            //consoleHandler.setLevel(logLevel);
            fileHandler.setLevel(logLevel);
            fileHandler.setFormatter(logsFormatter);
            logger.setLevel(logLevel);
            logger.config("Logging configuration done. Filename="+name);
            //Console handler removed
            //logger.removeHandler(consoleHandler);
            //logger.removeHandler(fileHandler);
            internalLogger = logger ;
            return logger;
        } catch (IOException exception) {
            logger.log(Level.SEVERE, "Error occur in setLogger.", exception);
            return null;
        }
    }
}
