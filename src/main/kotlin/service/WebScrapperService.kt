package service

import org.springframework.stereotype.Component
import scrappers.WebCssClassScrapper

@Component
class WebScrapperService {

    val scrapper: WebCssClassScrapper = WebCssClassScrapper()
    val textService: TextService = TextService()

    fun getScrappedSite(url: String, cssEl: String) : String {
        return textService.convertListToText(scrapper.parseSiteByClass(url, cssEl))
    }

    fun getScrappedSiteById(url: String, id: String) : String {
        return textService.convertListToText(scrapper.parseSiteByDivId(url, id))
    }

    fun getScrappedSiteByTableId(url: String, id: String) : String {
        return textService.convertListToText(scrapper.parseSiteByTable(url, id))
    }

    fun getScrappedSiteByTDId(url: String, id: String) : String {
        return textService.convertListToText(scrapper.parseSiteByTableRow(url, id))
    }
}