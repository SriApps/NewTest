package com.sri.testsen.model

import com.google.gson.annotations.SerializedName

data class RealEstateModel(
        val ad_id: Int,
        val data: Data,
        val title: String
)

data class Data(
    val listings: List<Listings>
)

data class Listings(
        val Id: String,
        val AgencyLogoUrl: String,
        val Area: String,
        val AuctionDate: String,
        val AvailableFrom: Any,
        @SerializedName("Bathrooms")
        val bathrooms: Int,
        val Bedrooms: Int,
        val Carspaces: Int,
        val DateFirstListed: String,
        val DateUpdated: String,
        val Description: String,
        val DisplayPrice: String,
        val Currency: String,
        val Location: Location,
        val owner: Owner,
        val ImageUrls: List<String>,
        val is_premium: Int,
        val IsPriority: Int,
        val Latitude: Double,
        val ListingPrice: Any,
        val ListingStatistics: Any,
        val ListingType: String,
        val ListingTypeString: String,
        val Longitude: Double
)

data class Location(
    val Address: String,
    val Address2: String,
    val State: String,
    val Suburb: String
)

data class Owner(
    val name: String,
    val lastName: String,
    val dob: String,
    val image: Image
)

data class Image(
        val big: Big,
        val small: Small,
        val medium: Medium
)

data class Big(
    val url: String
)

data class Medium(
    val url: String
)

data class Small(
    val url: String
)