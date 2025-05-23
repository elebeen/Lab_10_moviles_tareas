package com.example.lab_10_moviles_tareas.service

import com.example.lab_10_moviles_tareas.model.Category
import com.example.lab_10_moviles_tareas.model.Product
import com.example.lab_10_moviles_tareas.model.Supplier
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface tiendaApiService {

    // Rutas para los productos
    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): Response<Product>

    @Headers("Content-Type: application/json")
    @POST("products")
    suspend fun createProduct(@Body product: Product): Response<Product>

    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") id: Int, @Body product: Product): Response<Product>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int): Response<Product>

    // Rutas para las categorías
    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("categories/{id}")
    suspend fun getCategory(@Path("id") id: Int): Response<Category>

    @Headers("Content-Type: application/json")
    @POST("categories")
    suspend fun createCategory(@Body category: Category): Response<Category>

    @PUT("categories/{id}")
    suspend fun updateCategory(@Path("id") id: Int, @Body category: Category): Response<Category>

    @DELETE("categories/{id}")
    suspend fun deleteCategory(@Path("id") id: Int): Response<Category>

    // Rutas para los proveedores
    @GET("suppliers")
    suspend fun getSuppliers(): List<Supplier>

    @GET("suppliers/{id}")
    suspend fun getSupplier(@Path("id") id: Int): Response<Supplier>

    @Headers("Content-Type: application/json")
    @POST("suppliers")
    suspend fun createSupplier(@Body supplier: Supplier): Response<Supplier>

    @PUT("suppliers/{id}")
    suspend fun updateSupplier(@Path("id") id: Int, @Body supplier: Supplier): Response<Supplier>

    @DELETE("suppliers/{id}")
    suspend fun deleteSupplier(@Path("id") id: Int): Response<Supplier>

}