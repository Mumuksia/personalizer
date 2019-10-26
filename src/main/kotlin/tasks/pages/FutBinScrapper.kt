package tasks.pages

import org.jsoup.Jsoup
import org.springframework.stereotype.Service
import sender.EmailSender

@Service
class FutBinScrapper(private val emailSender: EmailSender) {

    fun parseFutBinHTML() {
        //1. Fetching the HTML from a given URL
        Jsoup.connect("https://www.futbin.com/squad-building-challenges").get().run {
            //2. Parses and scrapes the HTML response
            select("div.set_back_name").forEachIndexed { index, element ->
                emailSender.sendEmail("FUTBIN", element.text())
            }
        }
    }

}

