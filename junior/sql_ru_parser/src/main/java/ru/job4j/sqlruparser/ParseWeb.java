package ru.job4j.sqlruparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

import com.google.common.base.Joiner;

public class ParseWeb {
    public static final String JOB_PAGE = "https://www.sql.ru/forum/job";

    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());
    public static final String WILL_PARSE_PAGE = "Will parse page";
    public static final String YEAR_LIMIT_REACHED = "Year limit reached";
    public static final String SKIPPING_CLOSED_TOPIC = "Skipping closed topic";
    public static final String WILL_GET_LOCAL_DATE_TIME_FROM_STRING = "Will get LocalDateTime from String:";
    public static final String LAST_PAGE_NUMBER_IS = "Last page number is";
    public static final String STRING = "String:";
    public static final String TO = "to:";
    public static final String SPACE = " ";
    public static final String SLASH = "/";
    // css queries
    public static final String PAGES = ".sort_options a";
    public static final String TABLE_ROW = ".forumTable tr:not(:first-child)";
    public static final String TOPIC = ".postslisttopic";
    public static final String CLOSED_TOPIC = ".closedTopic";
    public static final String DATE = "td:nth-child(6)";
    public static final String LINK = "a";
    public static final String TEXT = ".msgBody:nth-child(2)";
    // attribute
    public static final String HREF = "href";

    public static final Year PREVIOUS_YEAR = Year.now().minusYears(1);
    // regEx
    public static final String IS_JAVA = ".*(?!Java\\s*Script)(Java).*";
    public static final String COMMA_SPACE = ",\\s";
    // contains
    public static final String YESTERDAY = "вчера";
    public static final String TODAY = "сегодня";
    // SimpleDateFormat pattern
    public static final String PATTERN = "dd MMM yy, HH:mm";
    // Locale language
    public static final String RU = "ru";

    boolean isLimit;

    public LinkedHashSet<JavaJob> parse(LocalDateTime lastStartTime) {
        LinkedHashSet<JavaJob> javaJobs = new LinkedHashSet<>();
        isLimit = false;
        for (int i = 1; i <= getLastPage(); i++) {
            LOG.debug(Joiner.on(" ").join(WILL_PARSE_PAGE, i));
            if (isLimit) {
                LOG.debug(YEAR_LIMIT_REACHED);
                break;
            }
            for (Element row : getPage(i).select(TABLE_ROW)) {
                Element topic = row.select(TOPIC).first();

                boolean isTopicClosed = topic.select(CLOSED_TOPIC).size() != 0;
                if (isTopicClosed) {
                    LOG.debug(SKIPPING_CLOSED_TOPIC);
                    continue;
                }

                String dateString = row.select(DATE).first().text();
                LocalDateTime rowTime = stringToLocalDateTime(dateString);

                checkLimit(rowTime, lastStartTime);
                if (isLimit) {
                    LOG.debug(YEAR_LIMIT_REACHED);
                    break;
                }
                Element a = topic.select(LINK).first();

                boolean isJava = Pattern.compile(IS_JAVA, Pattern.CASE_INSENSITIVE).matcher(a.text()).matches();
                if (!isJava) {
                    continue;
                }

                JavaJob javaJob = new JavaJob();
                javaJob.setName(a.text());
                javaJob.setLink(a.attr(HREF));
                Document topicPage = null;
                try {
                    topicPage = Jsoup.connect(javaJob.getLink()).get();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
                javaJob.setText(topicPage.select(TEXT).first().text());
                javaJobs.add(javaJob);
            }
        }
        return javaJobs;
    }

    private LocalDateTime stringToLocalDateTime(String dateString) {
        LOG.debug(Joiner.on(SPACE).join(WILL_GET_LOCAL_DATE_TIME_FROM_STRING, dateString));
        LocalDateTime localDateTime = null;
        if (dateString.contains(YESTERDAY)) {
            localDateTime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.parse(getTimeStr(dateString)));
        } else if (dateString.contains(TODAY)) {
            localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(getTimeStr(dateString)));
        } else {
            try {
                Date date = new SimpleDateFormat(PATTERN, new Locale(RU)).parse(dateString);
                localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            } catch (ParseException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        LOG.debug(Joiner.on(SPACE).join(STRING, dateString, TO, localDateTime));
        return localDateTime;
    }

    private void checkLimit(LocalDateTime rowTime, LocalDateTime lastStartTime) {
        if (Objects.isNull(lastStartTime)) {
            int year = rowTime.getYear();
            isLimit = !Year.of(year).isAfter(PREVIOUS_YEAR);
        } else {
            isLimit = !rowTime.isAfter(lastStartTime);
        }
    }

    private String getTimeStr(String dateString) {
        String[] splited = dateString.split(COMMA_SPACE);
        return splited[1];
    }

    private Document getPage(int i) {
        Document page = null;
        try {
            page = Jsoup.connect(Joiner.on(SLASH).join(JOB_PAGE, i)).get();
            LOG.debug(page.title());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return page;
    }

    private Integer getLastPage() {
        Integer lastPage = null;
        try {
            lastPage = Integer.parseInt(Jsoup.connect(JOB_PAGE).get().select(PAGES).last().text());
            LOG.debug(Joiner.on(SPACE).join(LAST_PAGE_NUMBER_IS, lastPage));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return lastPage;
    }
}
