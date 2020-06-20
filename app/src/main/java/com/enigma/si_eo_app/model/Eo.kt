package com.enigma.si_eo_app.model

import com.google.gson.annotations.SerializedName

class Eo(
    userID: Int = 0,
    eoname: String = "",
    identity: String= "",
    identityImg: String = "",
    license: String = "",
    licenseImg: String = "",
    address: String = "",
    phone: String = "",
    website: String = "",
    instagram: String = "",
    facebook: String = "",
    twitter: String = "") {

    @SerializedName("UserID")
    var userID: Int = userID

    @SerializedName("EoName")
    var eoname: String = eoname

    @SerializedName("Identity")
    var identity: String = identity

    @SerializedName("IdentityImg")
    var identityImg: String = identityImg

    @SerializedName("License")
    var license: String = license

    @SerializedName("LicenseImg")
    var licenseImg: String = licenseImg

    @SerializedName("Address")
    var address: String = address

    @SerializedName("Phone")
    var phone: String = phone

    @SerializedName("Website")
    var website: String = website

    @SerializedName("Instagram")
    var instagram: String = instagram

    @SerializedName("Facebook")
    var facebook: String = facebook

    @SerializedName("Twitter")
    var twitter: String = twitter

    override fun toString(): String {
        return "Eo(UserID='$userID', \" +" +
                "EoName='$eoname', " +
                "Identity='$identity', " +
                "IdentityImg='$identityImg', " +
                "License='$license', " +
                "LicenseImg='$licenseImg', " +
                "Address='$address', " +
                "Phone='$phone', " +
                "Website='$website', " +
                "Instagram='$instagram', " +
                "Facebook='$facebook', " +
                "Twitter='$twitter',)"
    }
}