package com.example.githubapiproject



import MainCoroutineRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.githubapiproject.repo.UserListRepo
import com.example.githubapiproject.useCases.FetchGitDataUseCase
import com.jraska.livedata.test
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class FetchGitDataUseCaseTest {

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var repo: UserListRepo
    lateinit var useCase: FetchGitDataUseCase

    @Before
    fun setup() {
        repo = mockk(relaxed = true)
        useCase = FetchGitDataUseCase(repo)
    }

    @Test
    fun usecase_get_user_list_return_user_list_successfully(){
        //mock
        useCase.getResultLiveData().test()
            .assertValue{
                it != null
            }
        //given
        useCase.setup()

    }
}