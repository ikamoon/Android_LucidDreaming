package com.aura_weavers.luciddreaming.service

import com.aura_weavers.luciddreaming.model.GetBootstrapResponse
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.functions.functions
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.ktor.client.call.body
import kotlinx.serialization.json.Json

object SupabaseManager {
    private const val SUPABASE_URL = "https://nkzxjrsmqrrowyuzbapl.supabase.co"
    private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im5renhqcnNtcXJyb3d5dXpiYXBsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDg2NjI5MDksImV4cCI6MjA2NDIzODkwOX0.U9CwasjtWnqiEwvgFkt6Ai2JMbZwUy1iGO2H1EJXNa4"

    val supabase = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY
    ) {
        install(Functions)

        defaultSerializer = KotlinXSerializer(Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        })
    }

    /**
     * Fetches bootstrap data from Supabase Edge Function
     * This method calls the "bootstrap" Edge Function with GET method
     * Reference: Swift implementation uses FunctionInvokeOptions with GET method
     * @return GetBootstrapResponse containing column, video, and other configuration data
     */
    suspend fun fetchBootstrap(): GetBootstrapResponse {
        return try {
            // Invoke the bootstrap Edge Function. It returns an HttpResponse.
            val httpResponse = supabase.functions.invoke(
                function = "bootstrap"
                // No body is provided, so it defaults to a GET request.
            )

            // Decode the response body to your GetBootstrapResponse data class.
            val response: GetBootstrapResponse = httpResponse.body()

            println("Bootstrap data fetched successfully: column.id = ${response.column.id}")
            response
        } catch (e: Exception) {
            println("Error fetching bootstrap data: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
}
