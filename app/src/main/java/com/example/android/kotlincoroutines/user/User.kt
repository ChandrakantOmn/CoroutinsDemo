package com.example.android.kotlincoroutines.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class User(

        val login: String,

        @PrimaryKey
        val id: Long,

        @SerializedName("avatar_url")
        val avatarUrl: String,

        @SerializedName("gravatar_id")
        val gravatarId: String,
        @Expose
        val url: String,

        @SerializedName("html_url")
        val htmlUrl: String,

        @SerializedName("followers_url")
        val followersUrl: String,

        @SerializedName("following_url")
        val followingUrl: String,

        @SerializedName("gists_url")
        val gistsUrl: String,

        @SerializedName("starred_url")
        val starredUrl: String,

        @SerializedName("subscriptions_url")
        val subscriptionsUrl: String,

        @SerializedName("organizations_url")
        val organizationsUrl: String,

        @SerializedName("repos_url")
        val reposUrl: String,

        @SerializedName("events_url")
        val eventsUrl: String,

        @SerializedName("received_events_url")
        val receivedEventsUrl: String,

        val type: String,

        @SerializedName("site_admin")
        val siteAdmin: Boolean
) : Serializable {
    constructor() : this(
            "", 0L, "", "", "", "", "", "", "", "", "", "", "", "", "", "", false
    )
}
