package org.example.task11

typealias Validator = (String) -> Boolean
typealias Predicate<T> = (T) -> Boolean
typealias Rule = Predicate<String>

val isEmail: Validator =
    { '@' in it && '.' in it.substringAfter('@') }

val isStrongPassword: Validator =
    { it.length >= 8 && it.any(Char::isDigit) }

fun check(label: String, input: String, validator: Validator) {
    println("$label: ${if (validator(input)) "OK" else "INVALID"}")
}

fun <T> countMatching(items: List<T>, rule: Predicate<T>): Int =
    items.count(rule)

typealias Meters = Double
typealias Feet = Double

fun toFeet(length: Meters): Feet = length * 3.28084

fun describeRoom(width: Meters) {
    println("Room width: $width m")
}

fun main() {
    print("E-mail: ")
    val email = readln().trim()

    print("Password: ")
    val password = readln()

    check("E-mail", email, isEmail)
    check("Password", password, isStrongPassword)

    val numbers = listOf(
        "+998901234567",
        "901234567",
        "+998991112233"
    )

    val startsWithCode: Rule = { it.startsWith("+998") }
    println("Numbers with +998: ${countMatching(numbers, startsWithCode)}")

    print("Room width in meters: ")
    val width: Meters = readln().trim().toDouble()
    val feet: Feet = toFeet(width)

    println("That is $feet feet")
    describeRoom(feet)
}