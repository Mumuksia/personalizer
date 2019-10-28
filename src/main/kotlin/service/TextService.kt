package service

class TextService {

    fun convertListToText(elements: List<String>) : String {
        return elements.joinToString("                           " +
                System.lineSeparator() + System.lineSeparator() + "<p><p>")
    }
}