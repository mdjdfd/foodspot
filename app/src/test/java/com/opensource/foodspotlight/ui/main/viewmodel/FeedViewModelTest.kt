package com.opensource.foodspotlight.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.opensource.foodspotlight.data.mockapi.MockApiHelper
import com.opensource.foodspotlight.data.model.Currency
import com.opensource.foodspotlight.data.model.User
import com.opensource.foodspotlight.repository.DataRepository
import com.opensource.foodspotlight.utils.Resource
import com.opensource.foodspotlight.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FeedViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var mockApiHelper: MockApiHelper

    @Mock
    private lateinit var userObserver: Observer<Resource<List<User>>>

    private lateinit var dataRepository: DataRepository

    @Before
    fun setUp() {
        // do something if required
        dataRepository = DataRepository(mockApiHelper)
    }


    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<Currency>())
                .`when`(mockApiHelper)
                .getUsers()


            val viewModel = FeedViewModel(dataRepository)
            viewModel.user.observeForever(userObserver)
            verify(mockApiHelper).getUsers()
            verify(userObserver).onChanged(Resource.success(emptyList()))
            viewModel.user.removeObserver(userObserver)
        }
    }


    @Test
    fun `givenServerResponseError_whenFetch_shouldReturnError`() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Happened"
            doThrow(RuntimeException(errorMessage))
                .`when`(mockApiHelper)
                .getUsers()
            val viewModel = FeedViewModel(dataRepository)
            viewModel.user.observeForever(userObserver)
            verify(mockApiHelper).getUsers()
            verify(userObserver).onChanged(
                Resource.error(
                    RuntimeException(errorMessage).toString(),
                    null
                )
            )
            viewModel.user.removeObserver(userObserver)
        }
    }



    @After
    fun tearDown() {
    }

}