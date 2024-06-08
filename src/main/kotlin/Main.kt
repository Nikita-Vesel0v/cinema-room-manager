package cinema

class CinemaRoomManager {
    private val cheap = 8 // cheap ticket
    private val regular = 10 // regular ticket

    private var totalIncome = 0
    private var rows = 0
    private var columns = 0
    private var curIncome = 0
    private var typeRoom = "small"
    private var room = emptyList<MutableList<Char>>()
    private var totalTickets = 0
    private var soldTickets = 0

    init {
        while (true)
            try {
                println("Enter the number of rows:")
                rows = readln().toInt()
                println("Enter the number of seats in each row:")
                columns = readln().toInt()
                break
            } catch (e: NumberFormatException) {
                println("\nWrong input!\n")
            }
        room = List(rows) { List(columns) { 'S' }.toMutableList() }
        totalTickets = rows * columns
        totalIncome = columns * if (rows * columns > 60) {
            typeRoom = "big"; (rows / 2) * 10 + (rows - rows / 2) * 8
        } else rows * 10
    }

    fun menu() {
        while (true) {
            println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
            when (readln()) {
                "1" -> printRoom()
                "2" -> buy()
                "3" -> statistics()
                "0" -> return
                else -> {
                    println("\nWrong input!"); continue
                }
            }
        }

    }

    private fun statistics() {
        println(
            """
            
            Number o f purchased tickets: $soldTickets
            Percentage: ${"%.2f".format(soldTickets * 100.0 / totalTickets)}%
            Current income: $$curIncome
            Total income: $$totalIncome
            
        """.trimIndent()
        )
    }

    private fun printRoom() {
        print("\nCinema:\n  ")
        var i = 1; repeat(columns) { print("${i++} ") }
        room.forEachIndexed { index, row -> print("\n${index + 1} ${row.joinToString(" ")}") }
        println()
    }

    private fun buy() {
        var row: Int
        var column: Int

        while (true) {
            try {
                println("\nEnter a row number:")
                row = readln().toInt()
                println("Enter a seat number in that row:")
                column = readln().toInt()
                if (room[row - 1][column - 1] == 'S') {
                    room[row - 1][column - 1] = 'B'
                    soldTickets++
                    val ticket = if (typeRoom == "big" && row > this.rows / 2) cheap else regular // (row > this.rows / 2) is cheap zone
                    println("\nTicket price: $$ticket")
                    curIncome += ticket
                } else { println("\nThat ticket has already been purchased!"); continue }
                break
            } catch (e: Exception) {
                println("\nWrong input!")
            }
        }
    }
}

fun main() {
    val cinemaRoomManager = CinemaRoomManager()
    cinemaRoomManager.menu()
}