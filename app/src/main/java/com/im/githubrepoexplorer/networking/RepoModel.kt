package com.im.githubrepoexplorer.networking

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.im.githubrepoexplorer.database.DatabaseModule
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllReposResponse(
    @SerialName("incomplete_results")
    val incompleteResults: Boolean = false,
    @SerialName("total_count")
    val totalCount: Int = 0,
    @SerialName("items")
    val items: List<RepoModel> = listOf(),
)

@Entity(tableName = DatabaseModule.REPOS_DATABASE_NAME)
@Serializable
data class RepoModel(
    @PrimaryKey
    @SerialName("id")
    val id: Int = 0,
    @SerialName("allow_forking")
    val allowForking: Boolean = false,
    @SerialName("archive_url")
    val archiveUrl: String = "",
    @SerialName("archived")
    val archived: Boolean = false,
    @SerialName("assignees_url")
    val assigneesUrl: String = "",
    @SerialName("blobs_url")
    val blobsUrl: String = "",
    @SerialName("branches_url")
    val branchesUrl: String = "",
    @SerialName("clone_url")
    val cloneUrl: String = "",
    @SerialName("collaborators_url")
    val collaboratorsUrl: String = "",
    @SerialName("comments_url")
    val commentsUrl: String = "",
    @SerialName("commits_url")
    val commitsUrl: String = "",
    @SerialName("compare_url")
    val compareUrl: String = "",
    @SerialName("contents_url")
    val contentsUrl: String = "",
    @SerialName("contributors_url")
    val contributorsUrl: String = "",
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("default_branch")
    val defaultBranch: String = "",
    @SerialName("deployments_url")
    val deploymentsUrl: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("disabled")
    val disabled: Boolean = false,
    @SerialName("downloads_url")
    val downloadsUrl: String = "",
    @SerialName("events_url")
    val eventsUrl: String = "",
    @SerialName("fork")
    val fork: Boolean = false,
    @SerialName("forks")
    val forks: Int = 0,
    @SerialName("forks_count")
    val forksCount: Int = 0,
    @SerialName("forks_url")
    val forksUrl: String = "",
    @SerialName("full_name")
    val fullName: String = "",
    @SerialName("git_commits_url")
    val gitCommitsUrl: String = "",
    @SerialName("git_refs_url")
    val gitRefsUrl: String = "",
    @SerialName("git_tags_url")
    val gitTagsUrl: String = "",
    @SerialName("git_url")
    val gitUrl: String = "",
    @SerialName("has_discussions")
    val hasDiscussions: Boolean = false,
    @SerialName("has_downloads")
    val hasDownloads: Boolean = false,
    @SerialName("has_issues")
    val hasIssues: Boolean = false,
    @SerialName("has_pages")
    val hasPages: Boolean = false,
    @SerialName("has_projects")
    val hasProjects: Boolean = false,
    @SerialName("has_wiki")
    val hasWiki: Boolean = false,
    @SerialName("homepage")
    val homepage: String? = null,
    @SerialName("hooks_url")
    val hooksUrl: String = "",
    @SerialName("html_url")
    val htmlUrl: String = "",
    @SerialName("is_template")
    val isTemplate: Boolean = false,
    @SerialName("issue_comment_url")
    val issueCommentUrl: String = "",
    @SerialName("issue_events_url")
    val issueEventsUrl: String = "",
    @SerialName("issues_url")
    val issuesUrl: String = "",
    @SerialName("keys_url")
    val keysUrl: String = "",
    @SerialName("labels_url")
    val labelsUrl: String = "",
    @SerialName("language")
    val language: String = "",
    @SerialName("languages_url")
    val languagesUrl: String = "",
//    @SerialName("license")
//    val license: License? = null,
    @SerialName("merges_url")
    val mergesUrl: String = "",
    @SerialName("milestones_url")
    val milestonesUrl: String = "",
    @SerialName("mirror_url")
    val mirrorUrl: String? = null,
    @SerialName("name")
    val name: String = "",
    @SerialName("node_id")
    val nodeId: String = "",
    @SerialName("notifications_url")
    val notificationsUrl: String = "",
    @SerialName("open_issues")
    val openIssues: Int = 0,
    @SerialName("open_issues_count")
    val openIssuesCount: Int = 0,
    @SerialName("owner")
    val owner: Owner = Owner(),
    @SerialName("private")
    val `private`: Boolean = false,
    @SerialName("pulls_url")
    val pullsUrl: String = "",
    @SerialName("pushed_at")
    val pushedAt: String = "",
    @SerialName("releases_url")
    val releasesUrl: String = "",
    @SerialName("score")
    val score: Double = 0.0,
    @SerialName("size")
    val size: Int = 0,
    @SerialName("ssh_url")
    val sshUrl: String = "",
    @SerialName("stargazers_count")
    val stargazersCount: Int = 0,
    @SerialName("stargazers_url")
    val stargazersUrl: String = "",
    @SerialName("statuses_url")
    val statusesUrl: String = "",
    @SerialName("subscribers_url")
    val subscribersUrl: String = "",
    @SerialName("subscription_url")
    val subscriptionUrl: String = "",
    @SerialName("svn_url")
    val svnUrl: String = "",
    @SerialName("tags_url")
    val tagsUrl: String = "",
    @SerialName("teams_url")
    val teamsUrl: String = "",
//    @SerialName("topics")
//    val topics: List<String> = listOf(),
    @SerialName("trees_url")
    val treesUrl: String = "",
    @SerialName("updated_at")
    val updatedAt: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("visibility")
    val visibility: String = "",
    @SerialName("watchers")
    val watchers: Int = 0,
    @SerialName("watchers_count")
    val watchersCount: Int = 0,
    @SerialName("web_commit_signoff_required")
    val webCommitSignoffRequired: Boolean = false
)

@Serializable
data class License(
    @SerialName("key")
    val key: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("node_id")
    val nodeId: String = "",
    @SerialName("spdx_id")
    val spdxId: String = "",
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Owner(
    @SerialName("avatar_url")
    val avatarUrl: String = "",
    @SerialName("events_url")
    val eventsUrl: String = "",
    @SerialName("followers_url")
    val followersUrl: String = "",
    @SerialName("following_url")
    val followingUrl: String = "",
    @SerialName("gists_url")
    val gistsUrl: String = "",
    @SerialName("gravatar_id")
    val gravatarId: String = "",
    @SerialName("html_url")
    val htmlUrl: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("login")
    val login: String = "",
    @SerialName("node_id")
    val nodeId: String = "",
    @SerialName("organizations_url")
    val organizationsUrl: String = "",
    @SerialName("received_events_url")
    val receivedEventsUrl: String = "",
    @SerialName("repos_url")
    val reposUrl: String = "",
    @SerialName("site_admin")
    val siteAdmin: Boolean = false,
    @SerialName("starred_url")
    val starredUrl: String = "",
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String = "",
    @SerialName("type")
    val type: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("user_view_type")
    val userViewType: String = ""
)

@Serializable
data class RepoDetails(
    @SerialName("allow_auto_merge")
    val allowAutoMerge: Boolean? = false,
    @SerialName("allow_forking")
    val allowForking: Boolean? = false,
    @SerialName("allow_merge_commit")
    val allowMergeCommit: Boolean? = false,
    @SerialName("allow_rebase_merge")
    val allowRebaseMerge: Boolean? = false,
    @SerialName("allow_squash_merge")
    val allowSquashMerge: Boolean? = false,
    @SerialName("archive_url")
    val archiveUrl: String? = "",
    @SerialName("archived")
    val archived: Boolean? = false,
    @SerialName("assignees_url")
    val assigneesUrl: String? = "",
    @SerialName("blobs_url")
    val blobsUrl: String? = "",
    @SerialName("branches_url")
    val branchesUrl: String? = "",
    @SerialName("clone_url")
    val cloneUrl: String? = "",
    @SerialName("collaborators_url")
    val collaboratorsUrl: String? = "",
    @SerialName("comments_url")
    val commentsUrl: String? = "",
    @SerialName("commits_url")
    val commitsUrl: String? = "",
    @SerialName("compare_url")
    val compareUrl: String? = "",
    @SerialName("contents_url")
    val contentsUrl: String? = "",
    @SerialName("contributors_url")
    val contributorsUrl: String? = "",
    @SerialName("created_at")
    val createdAt: String? = "",
    @SerialName("default_branch")
    val defaultBranch: String? = "",
    @SerialName("delete_branch_on_merge")
    val deleteBranchOnMerge: Boolean? = false,
    @SerialName("deployments_url")
    val deploymentsUrl: String? = "",
    @SerialName("description")
    val description: String? = "",
    @SerialName("disabled")
    val disabled: Boolean? = false,
    @SerialName("downloads_url")
    val downloadsUrl: String? = "",
    @SerialName("events_url")
    val eventsUrl: String? = "",
    @SerialName("fork")
    val fork: Boolean? = false,
    @SerialName("forks")
    val forks: Int? = 0,
    @SerialName("forks_count")
    val forksCount: Int? = 0,
    @SerialName("forks_url")
    val forksUrl: String? = "",
    @SerialName("full_name")
    val fullName: String? = "",
    @SerialName("git_commits_url")
    val gitCommitsUrl: String? = "",
    @SerialName("git_refs_url")
    val gitRefsUrl: String? = "",
    @SerialName("git_tags_url")
    val gitTagsUrl: String? = "",
    @SerialName("git_url")
    val gitUrl: String? = "",
    @SerialName("has_discussions")
    val hasDiscussions: Boolean? = false,
    @SerialName("has_downloads")
    val hasDownloads: Boolean? = false,
    @SerialName("has_issues")
    val hasIssues: Boolean? = false,
    @SerialName("has_pages")
    val hasPages: Boolean? = false,
    @SerialName("has_projects")
    val hasProjects: Boolean? = false,
    @SerialName("has_wiki")
    val hasWiki: Boolean? = false,
    @SerialName("homepage")
    val homepage: String? = "",
    @SerialName("hooks_url")
    val hooksUrl: String? = "",
    @SerialName("html_url")
    val htmlUrl: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("is_template")
    val isTemplate: Boolean? = false,
    @SerialName("issue_comment_url")
    val issueCommentUrl: String? = "",
    @SerialName("issue_events_url")
    val issueEventsUrl: String? = "",
    @SerialName("issues_url")
    val issuesUrl: String? = "",
    @SerialName("keys_url")
    val keysUrl: String? = "",
    @SerialName("labels_url")
    val labelsUrl: String? = "",
    @SerialName("languages_url")
    val languagesUrl: String? = "",
    @SerialName("license")
    val license: License? = License(),
    @SerialName("merges_url")
    val mergesUrl: String? = "",
    @SerialName("milestones_url")
    val milestonesUrl: String? = "",
    @SerialName("mirror_url")
    val mirrorUrl: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("network_count")
    val networkCount: Int? = 0,
    @SerialName("node_id")
    val nodeId: String? = "",
    @SerialName("notifications_url")
    val notificationsUrl: String? = "",
    @SerialName("open_issues")
    val openIssues: Int? = 0,
    @SerialName("open_issues_count")
    val openIssuesCount: Int? = 0,
    @SerialName("organization")
    val organization: Organization? = Organization(),
    @SerialName("owner")
    val owner: Owner? = Owner(),
    @SerialName("parent")
    val parent: Parent? = Parent(),
    @SerialName("permissions")
    val permissions: PermissionsX? = PermissionsX(),
    @SerialName("private")
    val `private`: Boolean? = false,
    @SerialName("pulls_url")
    val pullsUrl: String? = "",
    @SerialName("pushed_at")
    val pushedAt: String? = "",
    @SerialName("releases_url")
    val releasesUrl: String? = "",
    @SerialName("size")
    val size: Int? = 0,
    @SerialName("source")
    val source: Source? = Source(),
    @SerialName("ssh_url")
    val sshUrl: String? = "",
    @SerialName("stargazers_count")
    val stargazersCount: Int? = 0,
    @SerialName("stargazers_url")
    val stargazersUrl: String? = "",
    @SerialName("statuses_url")
    val statusesUrl: String? = "",
    @SerialName("subscribers_count")
    val subscribersCount: Int? = 0,
    @SerialName("subscribers_url")
    val subscribersUrl: String? = "",
    @SerialName("subscription_url")
    val subscriptionUrl: String? = "",
    @SerialName("svn_url")
    val svnUrl: String? = "",
    @SerialName("tags_url")
    val tagsUrl: String? = "",
    @SerialName("teams_url")
    val teamsUrl: String? = "",
    @SerialName("temp_clone_token")
    val tempCloneToken: String? = "",
    @SerialName("template_repository")
    val templateRepository: TemplateRepository? = TemplateRepository(),
    @SerialName("topics")
    val topics: List<String>? = listOf(),
    @SerialName("trees_url")
    val treesUrl: String? = "",
    @SerialName("updated_at")
    val updatedAt: String? = "",
    @SerialName("url")
    val url: String? = "",
    @SerialName("visibility")
    val visibility: String? = "",
    @SerialName("watchers")
    val watchers: Int? = 0,
    @SerialName("watchers_count")
    val watchersCount: Int? = 0
)

@Serializable
data class Organization(
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    @SerialName("events_url")
    val eventsUrl: String? = null,
    @SerialName("followers_url")
    val followersUrl: String? = null,
    @SerialName("following_url")
    val followingUrl: String? = null,
    @SerialName("gists_url")
    val gistsUrl: String? = null,
    @SerialName("gravatar_id")
    val gravatarId: String? = null,
    @SerialName("html_url")
    val htmlUrl: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("login")
    val login: String? = null,
    @SerialName("node_id")
    val nodeId: String? = null,
    @SerialName("organizations_url")
    val organizationsUrl: String? = null,
    @SerialName("received_events_url")
    val receivedEventsUrl: String? = null,
    @SerialName("repos_url")
    val reposUrl: String? = null,
    @SerialName("site_admin")
    val siteAdmin: Boolean? = null,
    @SerialName("starred_url")
    val starredUrl: String? = null,
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Parent(
    @SerialName("allow_auto_merge")
    val allowAutoMerge: Boolean? = false,
    @SerialName("allow_merge_commit")
    val allowMergeCommit: Boolean? = false,
    @SerialName("allow_rebase_merge")
    val allowRebaseMerge: Boolean? = false,
    @SerialName("allow_squash_merge")
    val allowSquashMerge: Boolean? = false,
    @SerialName("archive_url")
    val archiveUrl: String? = "",
    @SerialName("archived")
    val archived: Boolean? = false,
    @SerialName("assignees_url")
    val assigneesUrl: String? = "",
    @SerialName("blobs_url")
    val blobsUrl: String? = "",
    @SerialName("branches_url")
    val branchesUrl: String? = "",
    @SerialName("clone_url")
    val cloneUrl: String? = "",
    @SerialName("collaborators_url")
    val collaboratorsUrl: String? = "",
    @SerialName("comments_url")
    val commentsUrl: String? = "",
    @SerialName("commits_url")
    val commitsUrl: String? = "",
    @SerialName("compare_url")
    val compareUrl: String? = "",
    @SerialName("contents_url")
    val contentsUrl: String? = "",
    @SerialName("contributors_url")
    val contributorsUrl: String? = "",
    @SerialName("created_at")
    val createdAt: String? = "",
    @SerialName("default_branch")
    val defaultBranch: String? = "",
    @SerialName("delete_branch_on_merge")
    val deleteBranchOnMerge: Boolean? = false,
    @SerialName("deployments_url")
    val deploymentsUrl: String? = "",
    @SerialName("description")
    val description: String? = "",
    @SerialName("disabled")
    val disabled: Boolean? = false,
    @SerialName("downloads_url")
    val downloadsUrl: String? = "",
    @SerialName("events_url")
    val eventsUrl: String? = "",
    @SerialName("fork")
    val fork: Boolean? = false,
    @SerialName("forks")
    val forks: Int? = 0,
    @SerialName("forks_count")
    val forksCount: Int? = 0,
    @SerialName("forks_url")
    val forksUrl: String? = "",
    @SerialName("full_name")
    val fullName: String? = "",
    @SerialName("git_commits_url")
    val gitCommitsUrl: String? = "",
    @SerialName("git_refs_url")
    val gitRefsUrl: String? = "",
    @SerialName("git_tags_url")
    val gitTagsUrl: String? = "",
    @SerialName("git_url")
    val gitUrl: String? = "",
    @SerialName("has_downloads")
    val hasDownloads: Boolean? = false,
    @SerialName("has_issues")
    val hasIssues: Boolean? = false,
    @SerialName("has_pages")
    val hasPages: Boolean? = false,
    @SerialName("has_projects")
    val hasProjects: Boolean? = false,
    @SerialName("has_wiki")
    val hasWiki: Boolean? = false,
    @SerialName("homepage")
    val homepage: String? = "",
    @SerialName("hooks_url")
    val hooksUrl: String? = "",
    @SerialName("html_url")
    val htmlUrl: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("is_template")
    val isTemplate: Boolean? = false,
    @SerialName("issue_comment_url")
    val issueCommentUrl: String? = "",
    @SerialName("issue_events_url")
    val issueEventsUrl: String? = "",
    @SerialName("issues_url")
    val issuesUrl: String? = "",
    @SerialName("keys_url")
    val keysUrl: String? = "",
    @SerialName("labels_url")
    val labelsUrl: String? = "",
    @SerialName("language")
    val language: String? = "",
    @SerialName("languages_url")
    val languagesUrl: String? = "",
    @SerialName("license")
    val license: License? = License(),
    @SerialName("merges_url")
    val mergesUrl: String? = "",
    @SerialName("milestones_url")
    val milestonesUrl: String? = "",
    @SerialName("mirror_url")
    val mirrorUrl: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("network_count")
    val networkCount: Int? = 0,
    @SerialName("node_id")
    val nodeId: String? = "",
    @SerialName("notifications_url")
    val notificationsUrl: String? = "",
    @SerialName("open_issues")
    val openIssues: Int? = 0,
    @SerialName("open_issues_count")
    val openIssuesCount: Int? = 0,
    @SerialName("owner")
    val owner: Owner? = Owner(),
    @SerialName("permissions")
    val permissions: PermissionsX? = PermissionsX(),
    @SerialName("private")
    val `private`: Boolean? = false,
    @SerialName("pulls_url")
    val pullsUrl: String? = "",
    @SerialName("pushed_at")
    val pushedAt: String? = "",
    @SerialName("releases_url")
    val releasesUrl: String? = "",
    @SerialName("size")
    val size: Int? = 0,
    @SerialName("ssh_url")
    val sshUrl: String? = "",
    @SerialName("stargazers_count")
    val stargazersCount: Int? = 0,
    @SerialName("stargazers_url")
    val stargazersUrl: String? = "",
    @SerialName("statuses_url")
    val statusesUrl: String? = "",
    @SerialName("subscribers_count")
    val subscribersCount: Int? = 0,
    @SerialName("subscribers_url")
    val subscribersUrl: String? = "",
    @SerialName("subscription_url")
    val subscriptionUrl: String? = "",
    @SerialName("svn_url")
    val svnUrl: String? = "",
    @SerialName("tags_url")
    val tagsUrl: String? = "",
    @SerialName("teams_url")
    val teamsUrl: String? = "",
    @SerialName("temp_clone_token")
    val tempCloneToken: String? = "",
    @SerialName("topics")
    val topics: List<String>? = listOf(),
    @SerialName("trees_url")
    val treesUrl: String? = "",
    @SerialName("updated_at")
    val updatedAt: String? = "",
    @SerialName("url")
    val url: String? = "",
    @SerialName("visibility")
    val visibility: String? = "",
    @SerialName("watchers")
    val watchers: Int? = 0,
    @SerialName("watchers_count")
    val watchersCount: Int? = 0
)

@Serializable
data class PermissionsX(
    @SerialName("admin")
    val admin: Boolean? = null,
    @SerialName("pull")
    val pull: Boolean? = null,
    @SerialName("push")
    val push: Boolean? = null
)

@Serializable
data class Source(
    @SerialName("allow_auto_merge")
    val allowAutoMerge: Boolean? = false,
    @SerialName("allow_merge_commit")
    val allowMergeCommit: Boolean? = false,
    @SerialName("allow_rebase_merge")
    val allowRebaseMerge: Boolean? = false,
    @SerialName("allow_squash_merge")
    val allowSquashMerge: Boolean? = false,
    @SerialName("archive_url")
    val archiveUrl: String? = "",
    @SerialName("archived")
    val archived: Boolean? = false,
    @SerialName("assignees_url")
    val assigneesUrl: String? = "",
    @SerialName("blobs_url")
    val blobsUrl: String? = "",
    @SerialName("branches_url")
    val branchesUrl: String? = "",
    @SerialName("clone_url")
    val cloneUrl: String? = "",
    @SerialName("collaborators_url")
    val collaboratorsUrl: String? = "",
    @SerialName("comments_url")
    val commentsUrl: String? = "",
    @SerialName("commits_url")
    val commitsUrl: String? = "",
    @SerialName("compare_url")
    val compareUrl: String? = "",
    @SerialName("contents_url")
    val contentsUrl: String? = "",
    @SerialName("contributors_url")
    val contributorsUrl: String? = "",
    @SerialName("created_at")
    val createdAt: String? = "",
    @SerialName("default_branch")
    val defaultBranch: String? = "",
    @SerialName("delete_branch_on_merge")
    val deleteBranchOnMerge: Boolean? = false,
    @SerialName("deployments_url")
    val deploymentsUrl: String? = "",
    @SerialName("description")
    val description: String? = "",
    @SerialName("disabled")
    val disabled: Boolean? = false,
    @SerialName("downloads_url")
    val downloadsUrl: String? = "",
    @SerialName("events_url")
    val eventsUrl: String? = "",
    @SerialName("fork")
    val fork: Boolean? = false,
    @SerialName("forks")
    val forks: Int? = 0,
    @SerialName("forks_count")
    val forksCount: Int? = 0,
    @SerialName("forks_url")
    val forksUrl: String? = "",
    @SerialName("full_name")
    val fullName: String? = "",
    @SerialName("git_commits_url")
    val gitCommitsUrl: String? = "",
    @SerialName("git_refs_url")
    val gitRefsUrl: String? = "",
    @SerialName("git_tags_url")
    val gitTagsUrl: String? = "",
    @SerialName("git_url")
    val gitUrl: String? = "",
    @SerialName("has_downloads")
    val hasDownloads: Boolean? = false,
    @SerialName("has_issues")
    val hasIssues: Boolean? = false,
    @SerialName("has_pages")
    val hasPages: Boolean? = false,
    @SerialName("has_projects")
    val hasProjects: Boolean? = false,
    @SerialName("has_wiki")
    val hasWiki: Boolean? = false,
    @SerialName("homepage")
    val homepage: String? = "",
    @SerialName("hooks_url")
    val hooksUrl: String? = "",
    @SerialName("html_url")
    val htmlUrl: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("is_template")
    val isTemplate: Boolean? = false,
    @SerialName("issue_comment_url")
    val issueCommentUrl: String? = "",
    @SerialName("issue_events_url")
    val issueEventsUrl: String? = "",
    @SerialName("issues_url")
    val issuesUrl: String? = "",
    @SerialName("keys_url")
    val keysUrl: String? = "",
    @SerialName("labels_url")
    val labelsUrl: String? = "",
    @SerialName("languages_url")
    val languagesUrl: String? = "",
    @SerialName("license")
    val license: License? = License(),
    @SerialName("merges_url")
    val mergesUrl: String? = "",
    @SerialName("milestones_url")
    val milestonesUrl: String? = "",
    @SerialName("mirror_url")
    val mirrorUrl: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("network_count")
    val networkCount: Int? = 0,
    @SerialName("node_id")
    val nodeId: String? = "",
    @SerialName("notifications_url")
    val notificationsUrl: String? = "",
    @SerialName("open_issues")
    val openIssues: Int? = 0,
    @SerialName("open_issues_count")
    val openIssuesCount: Int? = 0,
    @SerialName("owner")
    val owner: Owner? = Owner(),
    @SerialName("permissions")
    val permissions: PermissionsX? = PermissionsX(),
    @SerialName("private")
    val `private`: Boolean? = false,
    @SerialName("pulls_url")
    val pullsUrl: String? = "",
    @SerialName("pushed_at")
    val pushedAt: String? = "",
    @SerialName("releases_url")
    val releasesUrl: String? = "",
    @SerialName("security_and_analysis")
    val securityAndAnalysis: SecurityAndAnalysis? = SecurityAndAnalysis(),
    @SerialName("size")
    val size: Int? = 0,
    @SerialName("ssh_url")
    val sshUrl: String? = "",
    @SerialName("stargazers_count")
    val stargazersCount: Int? = 0,
    @SerialName("stargazers_url")
    val stargazersUrl: String? = "",
    @SerialName("statuses_url")
    val statusesUrl: String? = "",
    @SerialName("subscribers_count")
    val subscribersCount: Int? = 0,
    @SerialName("subscribers_url")
    val subscribersUrl: String? = "",
    @SerialName("subscription_url")
    val subscriptionUrl: String? = "",
    @SerialName("svn_url")
    val svnUrl: String? = "",
    @SerialName("tags_url")
    val tagsUrl: String? = "",
    @SerialName("teams_url")
    val teamsUrl: String? = "",
    @SerialName("temp_clone_token")
    val tempCloneToken: String? = "",
    @SerialName("topics")
    val topics: List<String>? = listOf(),
    @SerialName("trees_url")
    val treesUrl: String? = "",
    @SerialName("updated_at")
    val updatedAt: String? = "",
    @SerialName("url")
    val url: String? = "",
    @SerialName("visibility")
    val visibility: String? = "",
    @SerialName("watchers")
    val watchers: Int? = 0,
    @SerialName("watchers_count")
    val watchersCount: Int? = 0
)

@Serializable
data class TemplateRepository(
    @SerialName("allow_auto_merge")
    val allowAutoMerge: Boolean? = false,
    @SerialName("allow_merge_commit")
    val allowMergeCommit: Boolean? = false,
    @SerialName("allow_rebase_merge")
    val allowRebaseMerge: Boolean? = false,
    @SerialName("allow_squash_merge")
    val allowSquashMerge: Boolean? = false,
    @SerialName("archive_url")
    val archiveUrl: String? = "",
    @SerialName("archived")
    val archived: Boolean? = false,
    @SerialName("assignees_url")
    val assigneesUrl: String? = "",
    @SerialName("blobs_url")
    val blobsUrl: String? = "",
    @SerialName("branches_url")
    val branchesUrl: String? = "",
    @SerialName("clone_url")
    val cloneUrl: String? = "",
    @SerialName("collaborators_url")
    val collaboratorsUrl: String? = "",
    @SerialName("comments_url")
    val commentsUrl: String? = "",
    @SerialName("commits_url")
    val commitsUrl: String? = "",
    @SerialName("compare_url")
    val compareUrl: String? = "",
    @SerialName("contents_url")
    val contentsUrl: String? = "",
    @SerialName("contributors_url")
    val contributorsUrl: String? = "",
    @SerialName("created_at")
    val createdAt: String? = "",
    @SerialName("default_branch")
    val defaultBranch: String? = "",
    @SerialName("delete_branch_on_merge")
    val deleteBranchOnMerge: Boolean? = false,
    @SerialName("deployments_url")
    val deploymentsUrl: String? = "",
    @SerialName("description")
    val description: String? = "",
    @SerialName("disabled")
    val disabled: Boolean? = false,
    @SerialName("downloads_url")
    val downloadsUrl: String? = "",
    @SerialName("events_url")
    val eventsUrl: String? = "",
    @SerialName("fork")
    val fork: Boolean? = false,
    @SerialName("forks")
    val forks: Int? = 0,
    @SerialName("forks_count")
    val forksCount: Int? = 0,
    @SerialName("forks_url")
    val forksUrl: String? = "",
    @SerialName("full_name")
    val fullName: String? = "",
    @SerialName("git_commits_url")
    val gitCommitsUrl: String? = "",
    @SerialName("git_refs_url")
    val gitRefsUrl: String? = "",
    @SerialName("git_tags_url")
    val gitTagsUrl: String? = "",
    @SerialName("git_url")
    val gitUrl: String? = "",
    @SerialName("has_downloads")
    val hasDownloads: Boolean? = false,
    @SerialName("has_issues")
    val hasIssues: Boolean? = false,
    @SerialName("has_pages")
    val hasPages: Boolean? = false,
    @SerialName("has_projects")
    val hasProjects: Boolean? = false,
    @SerialName("has_wiki")
    val hasWiki: Boolean? = false,
    @SerialName("homepage")
    val homepage: String? = "",
    @SerialName("hooks_url")
    val hooksUrl: String? = "",
    @SerialName("html_url")
    val htmlUrl: String? = "",
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("is_template")
    val isTemplate: Boolean? = false,
    @SerialName("issue_comment_url")
    val issueCommentUrl: String? = "",
    @SerialName("issue_events_url")
    val issueEventsUrl: String? = "",
    @SerialName("issues_url")
    val issuesUrl: String? = "",
    @SerialName("keys_url")
    val keysUrl: String? = "",
    @SerialName("labels_url")
    val labelsUrl: String? = "",
    @SerialName("language")
    val language: String? = "",
    @SerialName("languages_url")
    val languagesUrl: String? = "",
    @SerialName("license")
    val license: License? = License(),
    @SerialName("merges_url")
    val mergesUrl: String? = "",
    @SerialName("milestones_url")
    val milestonesUrl: String? = "",
    @SerialName("mirror_url")
    val mirrorUrl: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("network_count")
    val networkCount: Int? = 0,
    @SerialName("node_id")
    val nodeId: String? = "",
    @SerialName("notifications_url")
    val notificationsUrl: String? = "",
    @SerialName("open_issues")
    val openIssues: Int? = 0,
    @SerialName("open_issues_count")
    val openIssuesCount: Int? = 0,
    @SerialName("owner")
    val owner: Owner? = Owner(),
    @SerialName("permissions")
    val permissions: PermissionsX? = PermissionsX(),
    @SerialName("private")
    val `private`: Boolean? = false,
    @SerialName("pulls_url")
    val pullsUrl: String? = "",
    @SerialName("pushed_at")
    val pushedAt: String? = "",
    @SerialName("releases_url")
    val releasesUrl: String? = "",
    @SerialName("size")
    val size: Int? = 0,
    @SerialName("ssh_url")
    val sshUrl: String? = "",
    @SerialName("stargazers_count")
    val stargazersCount: Int? = 0,
    @SerialName("stargazers_url")
    val stargazersUrl: String? = "",
    @SerialName("statuses_url")
    val statusesUrl: String? = "",
    @SerialName("subscribers_count")
    val subscribersCount: Int? = 0,
    @SerialName("subscribers_url")
    val subscribersUrl: String? = "",
    @SerialName("subscription_url")
    val subscriptionUrl: String? = "",
    @SerialName("svn_url")
    val svnUrl: String? = "",
    @SerialName("tags_url")
    val tagsUrl: String? = "",
    @SerialName("teams_url")
    val teamsUrl: String? = "",
    @SerialName("temp_clone_token")
    val tempCloneToken: String? = "",
    @SerialName("topics")
    val topics: List<String>? = listOf(),
    @SerialName("trees_url")
    val treesUrl: String? = "",
    @SerialName("updated_at")
    val updatedAt: String? = "",
    @SerialName("url")
    val url: String? = "",
    @SerialName("visibility")
    val visibility: String? = "",
    @SerialName("watchers")
    val watchers: Int? = 0,
    @SerialName("watchers_count")
    val watchersCount: Int? = 0
)

@Serializable
data class SecurityAndAnalysis(
    @SerialName("advanced_security")
    val advancedSecurity: AdvancedSecurity? = null,
    @SerialName("secret_scanning")
    val secretScanning: SecretScanning? = null,
    @SerialName("secret_scanning_non_provider_patterns")
    val secretScanningNonProviderPatterns: SecretScanningNonProviderPatterns? = null,
    @SerialName("secret_scanning_push_protection")
    val secretScanningPushProtection: SecretScanningPushProtection? = null
)

@Serializable
data class AdvancedSecurity(
    @SerialName("status")
    val status: String? = null
)

@Serializable
data class SecretScanning(
    @SerialName("status")
    val status: String? = null
)

@Serializable
data class SecretScanningNonProviderPatterns(
    @SerialName("status")
    val status: String? = null
)

@Serializable
data class SecretScanningPushProtection(
    @SerialName("status")
    val status: String? = null
)

@Entity(tableName = DatabaseModule.CONTRIBUTOR_DATABASE_NAME)
@Serializable
data class ContributorModel(
    @PrimaryKey
    @SerialName("id")
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    @SerialName("contributions")
    val contributions: Int? = null,
    @SerialName("events_url")
    val eventsUrl: String? = null,
    @SerialName("followers_url")
    val followersUrl: String? = null,
    @SerialName("following_url")
    val followingUrl: String? = null,
    @SerialName("gists_url")
    val gistsUrl: String? = null,
    @SerialName("gravatar_id")
    val gravatarId: String? = null,
    @SerialName("html_url")
    val htmlUrl: String? = null,
    @SerialName("login")
    val login: String? = null,
    @SerialName("node_id")
    val nodeId: String? = null,
    @SerialName("organizations_url")
    val organizationsUrl: String? = null,
    @SerialName("received_events_url")
    val receivedEventsUrl: String? = null,
    @SerialName("repos_url")
    val reposUrl: String? = null,
    @SerialName("site_admin")
    val siteAdmin: Boolean? = null,
    @SerialName("starred_url")
    val starredUrl: String? = null,
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("user_view_type")
    val userViewType: String? = null
)