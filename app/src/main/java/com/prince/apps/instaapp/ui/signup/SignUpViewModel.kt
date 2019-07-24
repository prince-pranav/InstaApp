package com.prince.apps.instaapp.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.prince.apps.instaapp.data.repository.UserRepository
import com.prince.apps.instaapp.ui.base.BaseViewModel
import com.prince.apps.instaapp.utils.common.*
import com.prince.apps.instaapp.utils.network.NetworkHelper
import com.prince.apps.instaapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.reflect.Field

/**
 * Created by prince patel on 7/16/2019.
 */
class SignUpViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    lateinit var nameField: String

    private var validationList: MutableLiveData<List<Validation>> = MutableLiveData()

    var emailField: MutableLiveData<String> = MutableLiveData()
    var passwordField: MutableLiveData<String> = MutableLiveData()
    var signUpIn : MutableLiveData<Boolean> = MutableLiveData()
    val launchLoginActivity: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    val emailValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL)
    val passwordValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.PASSWORD)

    override fun onCreate() {

    }

    private fun filterValidation(field: Validation.Field): LiveData<Resource<Int>> =
        Transformations.map(validationList) {
            it.find { validation -> validation.field == field }
                ?.run { return@run this.resource }
                ?: Resource.unknown()
        }

    fun onNameField(name: String) {
        nameField = name
    }

    fun onEmailFieldChange(email: String) = emailField.postValue(email)
    fun onPasswordFieldChange(password: String) = passwordField.postValue(password)

    fun onSignUp() {
        val email = emailField.value
        val password = passwordField.value

        val validations = Validator.validateLoginFields(email, password)
        validationList.postValue(validations)

        if (validations.isNotEmpty() && email != null && password != null) {
            val successValidation = validations.filter { it.resource.status == Status.SUCCESS }

            if (successValidation.size == validations.size && checkInternetConnectionWithMessage()) {
                signUpIn.postValue(true)
                compositeDisposable.addAll(
                    userRepository.doUserSignUp(nameField, email, password)
                        .subscribeOn(schedularProvider.io())
                        .subscribe(
                            {
                                userRepository.saveCurrentUser(it)
                                signUpIn.postValue(false)
                                launchLoginActivity.postValue(Event(emptyMap()))
                            },
                            {
                                handleNetworkError(it)
                                signUpIn.postValue(false)
                            }
                        )

                )
            }
        }
    }
}
