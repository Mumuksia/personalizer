package service

class TextService {

    fun convertListToText(elements: List<String>) : String {
        elements.forEach{
            el -> println(el)
        }
        return elements.joinToString(System.lineSeparator())
    }
}