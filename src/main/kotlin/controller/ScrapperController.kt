package controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import service.WebScrapperService

@RestController
class ScrapperController{

    val webScrapper: WebScrapperService = WebScrapperService()

    @GetMapping("/scrapper/get/class")
    fun getTextByClass(@RequestParam(value = "cssClass") cssClass: String,
                          @RequestParam(value = "siteUrl") siteUrl: String) : String =
            webScrapper.getScrappedSite(cssClass, siteUrl)

    @GetMapping("/scrapper/get/id")
    fun getTextById(@RequestParam(value = "id") id: String,
                          @RequestParam(value = "siteUrl") siteUrl: String) : String =
            webScrapper.getScrappedSiteById(id, siteUrl)

    @GetMapping("/scrapper/get/table")
    fun getTextByTableId(@RequestParam(value = "tableId") tableId: String,
                         @RequestParam(value = "siteUrl") siteUrl: String) : String =
            webScrapper.getScrappedSiteByTableId(tableId, siteUrl)

    @GetMapping("/scrapper/get/tdRow")
    fun getTextByTdId(@RequestParam(value = "tdRow") tdRow: String,
                    @RequestParam(value = "siteUrl") siteUrl: String) : String =
            webScrapper.getScrappedSiteByTDId(tdRow, siteUrl)
}