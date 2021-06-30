package com.opensource.foodspotlight.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.opensource.foodspotlight.data.api.ApiHelper
import com.opensource.foodspotlight.data.model.Currency
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
class CurrencyViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var currencyApiObserver: Observer<Resource<List<Currency>>>



    @Before
    fun setUp() {
        // do something if required
    }


    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<Currency>())
                .`when`(apiHelper)
                .getCurrency()


            val viewModel = CurrencyViewModel(apiHelper)
            viewModel.currency.observeForever(currencyApiObserver)
            verify(apiHelper).getCurrency()
            verify(currencyApiObserver).onChanged(Resource.success(emptyList()))
            viewModel.currency.removeObserver(currencyApiObserver)
        }
    }


    @Test
    fun `givenServerResponseError_whenFetch_shouldReturnError`() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Happened"
            doThrow(RuntimeException(errorMessage))
                .`when`(apiHelper)
                .getCurrency()
            val viewModel = CurrencyViewModel(apiHelper)
            viewModel.currency.observeForever(currencyApiObserver)
            verify(apiHelper).getCurrency()
            verify(currencyApiObserver).onChanged(
                Resource.error(
                    RuntimeException(errorMessage).toString(),
                    null
                )
            )
            viewModel.currency.removeObserver(currencyApiObserver)
        }
    }



    @After
    fun tearDown() {
    }

}