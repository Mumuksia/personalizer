package rozha

class CWLKPIService {

    fun getKPIForMember(member: Member) : String {
        return when(member.level) {
            12 -> getTH12KPI(member.attacks).toString()
            11 -> getTH11KPI(member.attacks).toString()
            10 -> getTH10KPI(member.attacks).toString()
            else -> getTH09KPI(member.attacks).toString()
        }
    }

    fun getTH12KPI(attacks: List<Pair<Int, Int>>) : Double {

        var expectedStars = 0.0
        var totalStars = 0

        for ((th, stars) in attacks){
            totalStars += stars
            when (th) {
                12 -> expectedStars += 2.5
                0 -> expectedStars += 0
                else -> expectedStars += 3
            }
        }

        return totalStars/expectedStars
    }

    fun getTH11KPI(attacks: List<Pair<Int, Int>>) : Double {

        var expectedStars = 0.0
        var totalStars = 0

        for ((th, stars) in attacks){
            totalStars += stars
            when (th) {
                12 -> expectedStars += 2
                11 -> expectedStars += 2.7
                0 -> 0
                else -> expectedStars += 3
            }
        }

        return totalStars/expectedStars
    }

    fun getTH10KPI(attacks: List<Pair<Int, Int>>) : Double {

        var expectedStars = 0.0
        var totalStars = 0

        for ((th, stars) in attacks){
            totalStars += stars
            expectedStars += when (th) {
                12 -> 1
                11 -> 2
                0 -> 0
                else -> 3
            }
        }

        return totalStars/expectedStars
    }

    fun getTH09KPI(attacks: List<Pair<Int, Int>>) : Double {

        var expectedStars = 0.0
        var totalStars = 0

        for ((th, stars) in attacks){
            totalStars += stars
            expectedStars += when (th) {
                12 -> 1
                11 -> 1
                10 -> 2
                0 -> 0
                else -> 3
            }
        }

        return totalStars/expectedStars
    }
}