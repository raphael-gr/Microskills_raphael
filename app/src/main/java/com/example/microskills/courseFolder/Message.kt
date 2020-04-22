package com.example.microskills.courseFolder

//"abstract" class means it shouldn't be instantiated directly - only its children should
// Having "Message" as a separate class could prove useful if we need general functions for all msgs
abstract class Message (
    var sender: User? = null,
    var displayed: Boolean = false,
    var choiceInput: Choice? = null,
    var expectNumInput: Boolean = false,
    var expectFreeTextInput: Boolean = false,
    var expectFreeImageInput: Boolean = false,
    var expectFreeVoiceInput: Boolean = false
)

class TextMessage (
    //no var on next line because sender is already a property of the parent class
    sender: User? = null,
    var text: String? = null
): Message(sender)

class ImageMessage (
    sender: User? = null,
    var src: String? = null
    //src is the reference to the image online
): Message(sender)

class Choice (
    optionList: Array<String>
)


class User (
    var uid: String? = null
)
