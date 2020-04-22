package com.example.microskills.courseFolder

fun generateData() : MutableList<Message> {
    val data : MutableList<Message> = mutableListOf()

    //I am hardcoding the dataset; in the future, it should be retrieved from
    //the internet or at least from an other module
    val usrMe = User("raphael")
    val usrTeacher = User("teacher")
    val aMsg =
        TextMessage(usrMe, "Hello sir")
    val aMsg2 = TextMessage(
        usrTeacher,
        "Hello Raphaël, let's get to business! Hello Raphaël, let's get to business! Hello Raphaël, let's get to business! Hello Raphaël, let's get to business!"
    )
    val aMsg3 = TextMessage(
        usrTeacher,
        "Did you wash your hands yes or no?"
    )
    val aMsg4 =
        TextMessage(
            usrMe,
            "Ohh, actually I forgot!"
        )
    val aMsg4half =
        TextMessage(
            usrTeacher,
            "How long has it been since you last did?!"
        )
    aMsg4half.expectNumInput = true
    val aMsg5 =
        TextMessage(usrTeacher, "Do it.")
    val aMsg6 =
        TextMessage(usrTeacher,"Done yet?")
    val aMsg7 =
        TextMessage(usrMe, "Yes sir")
    val aMsg8 = TextMessage(
        usrTeacher,
        "Great, let's continue on then."
    )
    val aMsg9 =
        ImageMessage(usrMe, "useless")
    val aMsg10 =
        ImageMessage(usrTeacher, "useless")
    data.addAll(listOf(aMsg, aMsg2, aMsg3,aMsg4,aMsg4half))
    data.addAll(listOf(aMsg5, aMsg5, aMsg5, aMsg5, aMsg5, aMsg5, aMsg5, aMsg5, aMsg5, aMsg5, aMsg5, aMsg5, aMsg5, aMsg6, aMsg7, aMsg8, aMsg9, aMsg5, aMsg10))
    return data
}
