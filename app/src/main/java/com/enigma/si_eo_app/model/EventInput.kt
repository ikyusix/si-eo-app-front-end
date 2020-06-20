package com.enigma.si_eo_app.model

import com.google.gson.annotations.SerializedName

class EventInput (
    EoID : Int,
    Name: String ,
    Date: String ,
    Location : String ,
    Prince: String ,
    Capacity: String ,
    Description: String,
    Image:String
) {
    @SerializedName("EoID")
    var eoid :Int?= EoID

    @SerializedName("Name")
    var name :String?= Name

    @SerializedName("Location")
    var location:String?= Location

    @SerializedName("Date")
    var date:String?= Date

    @SerializedName("Prince")
    var prince:String?= Prince

    @SerializedName("Capacity")
    var capacity:String?= Capacity

    @SerializedName("Description")
    var description:String?= Description

    @SerializedName("Image")
    var image:String?= Image

    override fun toString(): String {
        return "EventInput(eoid=$eoid, name=$name, location=$location, date=$date, prince=$prince, capacity=$capacity, description=$description, image=$image)"
    }

}

class ResponseUser(
    var success: Boolean,
    var message: String,
    var data: EventInput?
) {
}