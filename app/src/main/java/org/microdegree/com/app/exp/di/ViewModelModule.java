package org.microdegree.com.app.exp.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.microdegree.com.app.exp.ui.ownerdash.OwnerDashViewModel;
import org.microdegree.com.app.exp.viewmodel.Qme5BizViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(OwnerDashViewModel.class)
    abstract ViewModel bindOwnerDashViewModel(OwnerDashViewModel ownerDashViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(Qme5BizViewModelFactory factory);


}
