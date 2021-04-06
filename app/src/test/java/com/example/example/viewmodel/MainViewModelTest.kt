package com.example.example.viewmodel

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.example.util.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.lifecycle.Observer
import com.example.example.api.ApiService
import com.example.example.api.datasource.MainDataSource
import com.example.example.repository.MainRepository
import com.example.example.api.status.Result
import com.example.example.model.Todo
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @RelaxedMockK
    private lateinit var apiService: ApiService

    @RelaxedMockK
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var mainViewModel: MainViewModel

    @MockK
    private lateinit var mainDataSource: MainDataSource

    private lateinit var mainRepository: MainRepository

    @RelaxedMockK
    private lateinit var apiUserObserver: Observer<Result<List<Todo>>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainDataSource =  spyk(
            MainDataSource(
                apiService
            )
        )
        mainRepository = spyk(MainRepository(mainDataSource))
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun `test for loading server response`() {
        testCoroutineRule.runBlockingTest {
            mainViewModel.getTodoList().observeForever(apiUserObserver)
            coEvery { mainRepository.getRemoteData() } returns Result.Loading

            mainViewModel.getTodoData()

            verify { apiUserObserver.onChanged(Result.Loading) }
        }
    }

    @Test
    fun `test for success server response`() {
        testCoroutineRule.runBlockingTest {
            mainViewModel.getTodoList().observeForever(apiUserObserver)
            coEvery { mainRepository.getRemoteData() } returns Result.Success(listOf(
                Todo(1, 100000, "London", true)
            ))

            mainViewModel.getTodoData()

            verify { apiUserObserver.onChanged(Result.Loading) }

            assert(mainViewModel.getTodoList().value != null)
        }
    }

    @Test
    fun `test for errors server response`() {
        testCoroutineRule.runBlockingTest {
            mainViewModel.getTodoList().observeForever(apiUserObserver)
            coEvery { mainRepository.getRemoteData() } returns Result.Error("Error")

            mainViewModel.getTodoData()

            verify { apiUserObserver.onChanged(Result.Loading) }

            assert(mainViewModel.getTodoList().value != null)
        }
    }

    @After
    fun tearDown() {
    }

}