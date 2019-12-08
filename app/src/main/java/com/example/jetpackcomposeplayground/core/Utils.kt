package com.example.jetpackcomposeplayground.core

fun getPersonList() = listOf<Person>(
    Person("Grace Hopper", 25),
    Person("Ada Lovelace", 29),
    Person("John Smith", 28),
    Person("Elon Musk", 41),
    Person("Will Smith", 31),
    Person("Robert James", 42),
    Person("Anthony Curry", 91),
    Person("Kevin Jackson", 22)
)

data class Person(
    val name: String,
    val age: Int
)