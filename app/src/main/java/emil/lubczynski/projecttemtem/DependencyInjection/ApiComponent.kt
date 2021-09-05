package emil.lubczynski.projecttemtem.DependencyInjection

import dagger.Component
import emil.lubczynski.projecttemtem.model.TemTemService
import emil.lubczynski.projecttemtem.viewmodel.ListViewModel


@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: TemTemService)

    fun inject(viewModel: ListViewModel)
}