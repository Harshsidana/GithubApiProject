package com.example.githubapiproject.repoImpl

import com.example.githubapiproject.MockUtil
import com.example.githubapiproject.api.ResponseData
import com.example.githubapiproject.api.UserListApi
import com.example.githubapiproject.entities.ResourceState
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Call


class UserListRepoImplTest {
    lateinit var userList: UserListApi
    lateinit var userListRepo: UserListRepoImpl

    @Before
    fun setup() {
        userList = mockk(relaxed = true)
        userListRepo = UserListRepoImpl(userList)
    }

    @Test
    fun `getRepositoryList Returns Github Users Success Response`()= runBlocking {

        val mockResponse = mockk<Call<ResponseData>>()
        every { mockResponse.execute() } returns MockUtil.mockResultSuccess()
        every { userList.getGitUserList()} returns mockResponse
        val userList=userListRepo.getUserList()
        Truth.assertThat(userList).isInstanceOf(ResourceState.Success::class.java)
        Truth.assertThat((userList as ResourceState.Success).code).isEqualTo(200)
        Truth.assertThat((userList as ResourceState.Success).body).isNotEmpty()
        Truth.assertThat((userList as ResourceState.Success).body[0].email).isEqualTo(
            MockUtil.mockDataList.get(0).email)

    }

    @Test
    fun `getRepositoryList Returns Github Users success list null but dont crash`()= runBlocking {

        val mockResponse = mockk<Call<ResponseData>>()
        every { mockResponse.execute() } returns MockUtil.mockResultFail()
        every { userList.getGitUserList()} returns mockResponse
        val userList=userListRepo.getUserList()
        Truth.assertThat(userList).isInstanceOf(ResourceState.Success::class.java)
        Truth.assertThat((userList as ResourceState.Success).code).isEqualTo(200)
        Truth.assertThat((userList as ResourceState.Success).body).isEmpty()
    }

    @Test
    fun `getRepositoryList Returns Github Users success list empty`()= runBlocking {

        val mockResponse = mockk<Call<ResponseData>>()
        every { mockResponse.execute() } returns MockUtil.mockResultFailEmptyList()
        every { userList.getGitUserList()} returns mockResponse
        val userList=userListRepo.getUserList()
        Truth.assertThat(userList).isInstanceOf(ResourceState.Success::class.java)
        Truth.assertThat((userList as ResourceState.Success).code).isEqualTo(200)
        Truth.assertThat((userList as ResourceState.Success).body).isEmpty()
    }


}