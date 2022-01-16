package gamerworld.projecttemtem.DependencyInjection

import dagger.Component
import gamerworld.projecttemtem.model.TemTemService
import gamerworld.projecttemtem.viewmodel.ListViewModel


@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: TemTemService)

    fun inject(viewModel: ListViewModel)
}