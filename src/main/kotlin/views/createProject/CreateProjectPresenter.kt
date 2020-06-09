package views.createProject

class CreateProjectPresenter : CreateProjectContract.Presenter {

    private lateinit var view: CreateProjectContract.View

    override fun attach(view: CreateProjectContract.View) {
        this.view = view
        view.showCreateProjectPage()
    }

    override fun createProject(name: String) {

    }
}

class CreateProjectContract {

    interface View {
        fun showCreateProjectPage()
        fun showCreateProjectFailure()
    }

    interface Presenter {
        fun attach(view: View)
        fun createProject(name: String)
    }
}