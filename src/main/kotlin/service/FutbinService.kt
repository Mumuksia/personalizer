package service

interface FutbinService {

    fun updateChallenge(challenge: String)

    fun getAllChallenges(challenges: List<String>)

    fun setAllChallenges(): List<String>
}