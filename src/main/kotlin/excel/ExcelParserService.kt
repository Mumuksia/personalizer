package excel

import org.apache.poi.ss.usermodel.WorkbookFactory
import rozha.CWLKPIService
import rozha.Member
import java.io.File
import java.io.FileInputStream

class ExcelParserService {

    val cwlkpiService: CWLKPIService = CWLKPIService()


    fun parseExcelForClanLeague(fs: FileInputStream): String {
        val xlWb = WorkbookFactory.create(fs)
        val rowNumber = 0
        val columnNumber = 0

        val xlWs = xlWb.getSheetAt(0)
        println(xlWs.getRow(rowNumber).getCell(columnNumber))

        return xlWs.getRow(rowNumber).getCell(columnNumber).stringCellValue
    }

    fun parseExcelForClanLeague(): String {
        val path = System.getProperty("user.dir")

        val xlWb = WorkbookFactory.create(File("$path/temp.xlsx").inputStream())
        val columnNumber = 0

        val xlWs = xlWb.getSheetAt(0)
        for(rowNumber in 1..xlWs.lastRowNum){
            val attacks = ArrayList<Pair<Int, Int>>()
            attacks.add(Pair(xlWs.getRow(rowNumber).getCell(2).numericCellValue.toInt(),
                    xlWs.getRow(rowNumber).getCell(3).numericCellValue.toInt()))
            attacks.add(Pair(xlWs.getRow(rowNumber).getCell(4).numericCellValue.toInt(),
                    xlWs.getRow(rowNumber).getCell(5).numericCellValue.toInt()))
            attacks.add(Pair(xlWs.getRow(rowNumber).getCell(6).numericCellValue.toInt(),
                    xlWs.getRow(rowNumber).getCell(7).numericCellValue.toInt()))
            attacks.add(Pair(xlWs.getRow(rowNumber).getCell(8).numericCellValue.toInt(),
                    xlWs.getRow(rowNumber).getCell(9).numericCellValue.toInt()))
            attacks.add(Pair(xlWs.getRow(rowNumber).getCell(10).numericCellValue.toInt(),
                    xlWs.getRow(rowNumber).getCell(11).numericCellValue.toInt()))
            attacks.add(Pair(xlWs.getRow(rowNumber).getCell(12).numericCellValue.toInt(),
                    xlWs.getRow(rowNumber).getCell(13).numericCellValue.toInt()))
            attacks.add(Pair(xlWs.getRow(rowNumber).getCell(14).numericCellValue.toInt(),
                    xlWs.getRow(rowNumber).getCell(15).numericCellValue.toInt()))

            print(xlWs.getRow(rowNumber).getCell(columnNumber))
            print("               ")
            println(cwlkpiService.getKPIForMember(Member(xlWs.getRow(rowNumber).getCell(1).numericCellValue.toInt(), attacks)))
        }

        return xlWs.getRow(1).getCell(columnNumber).stringCellValue
    }
}