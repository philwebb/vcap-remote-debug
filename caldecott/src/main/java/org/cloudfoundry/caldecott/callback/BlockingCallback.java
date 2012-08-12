/*
 * Copyright 2010-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cloudfoundry.caldecott.callback;

/**
 * Adapter class that allows {@link Callback}s to operate asynchronously.
 * 
 * @param <V>
 * @author Phillip Webb
 */
public class BlockingCallback<V> implements Callback<V> {

	private V result;
	private RuntimeException exception;

	@Override
	public void onSuccess(V result) {
		this.result = result;
	}

	@Override
	public void onError(RuntimeException exception) {
		this.exception = exception;
	}

	public V get() {
		// FIXME wait
		if (this.exception != null) {
			throw this.exception;
		}
		return this.result;
	}

	public static <V> BlockingCallback<V> forClass(Class<V> valueClass) {
		return new BlockingCallback<V>();
	}
}
