package tasks

import org.springframework.stereotype.Service
import tasks.pages.FutBinScrapper

@Service
class FutBinScheduler(private var futBinScrapper: FutBinScrapper) {




    /**
     * This @Schedule annotation run every 5 seconds in this case. It can also
     * take a cron like syntax.
     * See https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
     */
    //@Scheduled(fixedRate = 500000)
    fun reportTime(){
        println("new scheduler")
        futBinScrapper.parseFutBinHTML()

    }
}