package cinema

class CinemaRoomManager {
    private var profit = 0
    private var rows = 0
    private var columns = 0
    private var room = emptyList<List<Char>>()

    init {
        println("Enter the number of rows:")
        rows = readln().toInt()
        println("Enter the number of seats in each row:")
        columns = readln().toInt()
        room = List(rows) { List(columns) { 'S' } }
    }

    fun getTotalIncome(): Int {
        print("\n\nTotal income:\n$")
        return profit
    }
    fun printRoom() {
        print("\nCinema:\n  ")
        var i = 1; repeat(columns) { print("${i++} ") }
        room.forEachIndexed { index, row -> print("\n${index + 1} ${row.joinToString(" ")}") }
    }

    fun calcProfit() {
        profit = columns * if (rows * columns > 60) {
            (rows / 2) * 10 + (rows - rows / 2) * 8
        }
        else {
            rows * 10
        }
    }
}

fun main() {
    val cinemaRoomManager = CinemaRoomManager()
    cinemaRoomManager.printRoom()
    cinemaRoomManager.calcProfit()
    println(cinemaRoomManager.getTotalIncome())
}