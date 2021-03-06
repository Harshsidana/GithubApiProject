package com.example.githubapiproject



import MainCoroutineRule
import android.accounts.NetworkErrorException
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.githubapiproject.entities.ResourceState
import com.example.githubapiproject.repo.UserListRepo
import com.example.githubapiproject.useCases.FetchGitDataUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
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

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

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
    fun usecase_get_user_list_return_user_list_successfully() {
        coEvery { repo.getUserList() } returns ResourceState.Success(mutableListOf(MockUtil.mockGitResponse))

        useCase.getResultLiveData().observeForever { resource ->
            Truth.assertThat(resource).isInstanceOf(ResourceState.Success::class.java)
            Truth.assertThat((resource as ResourceState.Success).code).isEqualTo(200)
            Truth.assertThat((resource as ResourceState.Success).body.get(0).email).isEqualTo(MockUtil.mockGitResponse.email)
        }

        useCase.setup()
        coVerify(exactly = 1) { repo.getUserList() }

    }
    @Test
    fun usecase_get_user_list_return_user_list_empty() {
        coEvery { repo.getUserList() } returns ResourceState.Success(MockUtil.mockResultEmptyList())

        useCase.getResultLiveData().observeForever { resource ->
            Truth.assertThat(resource).isInstanceOf(ResourceState.Success::class.java)
            Truth.assertThat((resource as ResourceState.Success).code).isEqualTo(200)
            Truth.assertThat((resource as ResourceState.Success).body.isEmpty())
        }

        useCase.setup()
        coVerify(exactly = 1) { repo.getUserList() }

    }

    @Test
    fun usecase_get_user_list_return_user_list_fails() {
        coEvery { repo.getUserList() } returns ResourceState.Failure(NetworkErrorException("error"),404)

        useCase.getResultLiveData().observeForever { resource ->
            Truth.assertThat(resource).isInstanceOf(ResourceState.Failure::class.java)
            Truth.assertThat((resource as ResourceState.Failure).code).isEqualTo(404)
            Truth.assertThat((resource as ResourceState.Failure).exception).isInstanceOf(NetworkErrorException::class.java)
        }

        useCase.setup()
        coVerify(exactly = 1) { repo.getUserList() }

    }


}