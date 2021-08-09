package com.example.demoapp.entity

import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class ProductTest{

    @Test
    fun test_isProductWithDifferentIDEqual(){
        val product1 = Product(1, "Test product 1", "200",
            "", "", "", ArrayList(), ArrayList())

        val product2 = Product(2, "Test product 2", "400",
            "", "", "", ArrayList(), ArrayList())

        assert(product1 != product2)
    }


    @Test
    fun test_isProductWithSameIDEqual(){
        val product1 = Product(1, "Test product 1", "200",
            "", "", "", ArrayList(), ArrayList())

        val product2 = Product(1, "Test product 2", "400",
            "", "", "", ArrayList(), ArrayList())

        assert(product1 == product2)
    }

    @Test
    fun test_isProductEqualToNullObject(){
        val product1 = Product(1, "Test product 1", "200",
            "", "", "", ArrayList(), ArrayList())

        assert(!product1.equals(null))
    }

    @Test
    fun test_isProductEqualToOtherClassObject(){
        val product1 = Product(1, "Test product 1", "200",
            "", "", "", ArrayList(), ArrayList())

        assert(product1 != Any())
    }

}