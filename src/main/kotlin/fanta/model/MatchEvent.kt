package fanta.model

data class MatchEvent(val type: String, val minute: String, val playerName: String,
                      val secondPlayerName: String = "")