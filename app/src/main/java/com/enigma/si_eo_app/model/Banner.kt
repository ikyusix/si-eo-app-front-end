package com.enigma.si_eo_app.model

class Banner (val EventId :String, val UrlBanner :String){
    override fun toString(): String {
        return "Banner(EventId='$EventId', UrlBanner='$UrlBanner')"
    }
}