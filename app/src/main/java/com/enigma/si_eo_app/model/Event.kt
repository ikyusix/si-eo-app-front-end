package com.enigma.si_eo_app.model

class Event(val  Id :String, val Name :String, val Date :String, val Location :String, val Prince :String, val Capacity :String,  val Description :String, val  UrlBanner: String) {
    override fun toString(): String {
        return "Event(Id='$Id', Name='$Name', Date='$Date', Location='$Location', Prince='$Prince', Capacity='$Capacity', Description='$Description', UrlBanner='$UrlBanner')"
    }
}