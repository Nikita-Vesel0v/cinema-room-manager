package cinema

class CinemaRoomManager {
    private val cheap = 8 // cheap ticket
    private val regular = 10 // regular ticket

    private var totalIncome = 0
    private var rows = 0
    private var columns = 0
    private var profit = 0
    private var typeRoom = "small"
    private var room = emptyList<MutableList<Char>>()

    init {
        println("Enter the number of rows:")
        rows = readln().toInt()
        println("Enter the number of seats in each row:")
        columns = readln().toInt()
        room = List(rows) { List(columns) { 'S' }.toMutableList() }
        totalIncome = columns * if (rows * columns > 60) {
            typeRoom = "big"; (rows / 2) * 10 + (rows - rows / 2) * 8
        } else rows * 10
    }

    fun getTotalIncome(): Int {
        print("\nTotal income:\n$")
        return totalIncome
    }
    fun printRoom() {
        print("\nCinema:\n  ")
        var i = 1; repeat(columns) { print("${i++} ") }
        room.forEachIndexed { index, row -> print("\n${index + 1} ${row.joinToString(" ")}") }
        println()
    }
    fun buy() {
        println("\nEnter a row number:")
        val row = readln().toInt()
        println("Enter a seat number in that row:")
        val column = readln().toInt()
        if (room[row - 1][column - 1] == 'S') {
            room[row - 1][column - 1] = 'B'
            val ticket = if (typeRoom == "big" && row > this.rows / 2) cheap else regular // (row > this.rows / 2) is cheap zone
            println("\nTicket price: $$ticket")
            profit += ticket
        }
    }
}

fun main() {
    val cinemaRoomManager = CinemaRoomManager()
    cinemaRoomManager.printRoom()
//    println(cinemaRoomManager.getTotalIncome())
    cinemaRoomManager.buy()
    cinemaRoomManager.printRoom()
}