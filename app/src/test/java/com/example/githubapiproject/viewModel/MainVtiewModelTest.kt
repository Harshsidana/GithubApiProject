package com.example.githubapiproject.viewModel

import MainCoroutineRule
import android.accounts.NetworkErrorException
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.githubapiproject.FetchGitDataUseCaseTest
import com.example.githubapiproject.InstantTaskExecutorRule
import com.example.githubapiproject.MockUtil
import com.example.githubapiproject.api.UserListApi
import com.example.githubapiproject.entities.ResourceState
import com.example.githubapiproject.repo.UserListRepo
import com.example.githubapiproject.useCases.FetchGitDataUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class MainVtiewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repo: UserListRepo
    lateinit var useCase: FetchGitDataUseCase
    lateinit var vm: MainViewModel
    @Before
    fun setup() {
        repo = mockk<UserListRepo>(relaxed = true)
        useCase = spyk(FetchGitDataUseCase(repo))
        vm = MainViewModel(useCase)
    }

    @Test
    fun `viewmodel calls getUserData and get callback from livedata with correct value`(){
        coEvery { repo.getUserList() } returns ResourceState.Success(mutableListOf(MockUtil.mockGitResponse))

        vm.resultLiveData.observeForever { resource ->
            Truth.assertThat(resource).isInstanceOf(ResourceState.Success::class.java)
            Truth.assertThat((resource as ResourceState.Success).code).isEqualTo(200)
            Truth.assertThat((resource as ResourceState.Success).body.get(0).email).isEqualTo(MockUtil.mockGitResponse.email)
        }

        vm.getUserData()
        coVerify(exactly = 1) { useCase.setup() }
        coVerify(exactly = 1) { repo.getUserList() }
    }
    @Test
    fun `viewmodel calls getUserData and get callback from livedata with error`(){
        coEvery { repo.getUserList() } returns ResourceState.Failure(NetworkErrorException("error"),404)

        vm.resultLiveData.observeForever { resource ->
            Truth.assertThat(resource).isInstanceOf(ResourceState.Success::class.java)
            Truth.assertThat((resource as ResourceState.Success).code).isEqualTo(404)
            Truth.assertThat((resource as ResourceState.Failure).exception).isInstanceOf(NetworkErrorException::class.java)
        }

        vm.getUserData()
        coVerify(exactly = 1) { useCase.setup() }
        coVerify(exactly = 1) { repo.getUserList() }
    }


}