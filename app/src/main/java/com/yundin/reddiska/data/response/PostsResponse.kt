package com.yundin.reddiska.data.response

class PostsResponse(
        val kind: String,
        val data: PostsPagination
)

class PostsPagination(
        val modhash: String,
        val dist: Int,
        val children: List<Post>
)

class Post(
        val kind: String,
        val data: PostData
)

class PostData(
        val approved_at_utc: String,
        val subreddit: String,
        val selftext: String,
        val author_fullname: String,
        val saved: Boolean,
        val mod_reason_title: String,
        val gilded: Int,
        val clicked: Boolean,
        val title: String,
        val link_flair_richtext: List<Richtext>,
        val subreddit_name_prefixed: String,
        val hidden: Boolean,
        val pwls: Int,
        val link_flair_css_class: String,
        val downs: Int,
        val thumbnail_height: Int,
        val hide_score: Boolean,
        val name: String,
        val quarantine: Boolean,
        val link_flair_text_color: String,
        val author_flair_background_color: String,
        val subreddit_type: String,
        val ups: Int,
        val total_awards_received: Int,
        val media_embed: Media,
        val thumbnail_width: Int,
        val author_flair_template_id: String,
        val is_original_content: Boolean,
        val user_reports: List<String>,
        val secure_media: Media,
        val is_reddit_media_domain: Boolean,
        val is_meta: Boolean,
        val category: String,
        val secure_media_embed: Media,
        val link_flair_text: String,
        val can_mod_post: Boolean,
        val score: Int,
        val approved_by: String,
        val thumbnail: String,
        val edited: Boolean,
        val author_flair_css_class: String,
        val author_flair_richtext: List<Richtext>,
        val gildings: Gilding,
        val post_hint: String,
        val content_categories: List<String>,
        val is_self: Boolean,
        val mod_note: String,
        val created: Long,
        val link_flair_type: String,
        val wls: Int,
        val banned_by: String,
        val author_flair_type: String,
        val domain: String,
        val selftext_html: String,
        val likes: String,
        val suggested_sort: String,
        val banned_at_utc: String,
        val view_count: String,
        val archived: Boolean,
        val no_follow: Boolean,
        val is_crosspostable: Boolean,
        val pinned: Boolean,
        val over_18: Boolean,
        val media_only: Boolean,
        val can_gild: Boolean,
        val spoiler: Boolean,
        val locked: Boolean,
        val author_flair_text: String,
        val visited: Boolean,
        val num_reports: String,
        val distinguished: String,
        val subreddit_id: String,
        val mod_reason_by: String,
        val removal_reason: String,
        val link_flair_background_color: String,
        val id: String,
        val is_robot_indexable: Boolean,
        val report_reasons: String,
        val author: String,
        val num_crossposts: Int,
        val num_comments: Int,
        val send_replies: Boolean,
        val whitelist_status: String,
        val contest_mode: Boolean,
        val mod_reports: List<String>,
        val author_patreon_flair: Boolean,
        val author_flair_text_color: String,
        val permalink: String,
        val parent_whitelist_status: String,
        val stickied: Boolean,
        val url: String,
        val subreddit_subscribers: Int,
        val created_utc: Long,
        val media: Media,
        val is_video: Boolean,
        val preview: PostPreview,
        val all_awardings: List<Award>
)

class Gilding(
        val gid_1: Int,
        val gid_2: Int,
        val gid_3: Int
)

class PostPreview(
        val images: List<Image>,
        val enabled: Boolean
)

class ImageData(
        val source: Image,
        val resolutions: List<Image>,
        val variants: List<Image>,
        val id: String
)

class Image(
        val url: String,
        val width: Int,
        val height: Int
)
class Award(
        val is_enabled: Boolean,
        val count: Int,
        val subreddit_id: String,
        val description: String,
        val coin_reward: Int,
        val icon_width: Int,
        val icon_url: String,
        val days_of_premium: Int,
        val icon_height: Int,
        val resized_icons: List<Image>,
        val days_of_drip_extension: Int,
        val award_type: String,
        val coin_price: Int,
        val id: String,
        val name: String
)

class Media

class Richtext