package com.ss.apportar.nw

import java.io.Serializable

class Mensaje : Serializable {
    lateinit var email: String
    lateinit var message: String
}