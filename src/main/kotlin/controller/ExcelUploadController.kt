package controller

import excel.ExcelParserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File

@RestController
class ExcelUploadController {

    val excelService: ExcelParserService = ExcelParserService()


    @GetMapping("/excel/process")
    fun getScheduledEvent(@RequestParam(value = "filePath") filePath: String) : String =
            excelService.parseExcelForClanLeague(File(filePath).inputStream())

    @GetMapping("/excel/process/present")
    fun getScheduledEvent() : String =
            excelService.parseExcelForClanLeague()
}
