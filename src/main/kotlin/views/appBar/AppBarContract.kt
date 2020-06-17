package views.appBar

import models.User
import mvp.PageContract

interface AppBarContract : PageContract {
    fun showPageTitle(title: String)
    fun showUserAvatar(user: User)
}