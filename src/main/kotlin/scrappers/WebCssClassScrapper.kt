package scrappers

import org.jsoup.Jsoup
import org.springframework.stereotype.Component

@Component
class WebCssClassScrapper {


    fun parseSiteByClass(cssClass: String, url: String): List<String> {
        var site = url
        if (!url.startsWith("http")) //add https
            site = "http://$url"
        Jsoup.connect(site).get().run {
            //2. Parses and scrapes the HTML response
            return select("div." + cssClass).filter { element ->
                element.hasText()
            }.map { element ->
                element.text()
            }
        }
    }

    fun parseSiteByDivId(divId: String, url: String): List<String> {
        var site = url
        if (!url.startsWith("http")) //add https
            site = "http://$url"
        Jsoup.connect(site).get().run {
            //2. Parses and scrapes the HTML response
            return select("div[id*=" + divId + "]").filter { element ->
                element.hasText()
            }.map { element ->
                element.text()
            }
        }
    }
}