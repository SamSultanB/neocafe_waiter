package com.neocafe.neocafewaiter.model.api.retrofit

/**
 * todo: learn to write docs
 *
 */
sealed interface NetworkStatus<T>{
	/**
	 *
	 */
	class Success<T>(val data: T) : NetworkStatus<T>
	
	/**
	 *
	 */
	class Error<T>(val message: String) : NetworkStatus<T>
	
	/**
	 *
	 */
	class Loading<T> : NetworkStatus<T>
}
