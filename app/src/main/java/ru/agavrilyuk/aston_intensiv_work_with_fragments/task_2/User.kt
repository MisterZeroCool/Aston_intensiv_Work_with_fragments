package ru.agavrilyuk.aston_intensiv_work_with_fragments.task_2

data class User(
    val id: Int,
    val im: Int,
    val name: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
)