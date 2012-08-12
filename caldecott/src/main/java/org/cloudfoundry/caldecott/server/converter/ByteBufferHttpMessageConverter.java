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
package org.cloudfoundry.caldecott.server.converter;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ClassUtils;

/**
 * {@link HttpMessageConverter} that can write {@link ByteBuffer}s.
 * 
 * @author Phillip Webb
 */
public class ByteBufferHttpMessageConverter extends AbstractHttpMessageConverter<ByteBuffer> {

	public ByteBufferHttpMessageConverter() {
		super(new MediaType("application", "octet-stream"), MediaType.ALL);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ClassUtils.isAssignable(ByteBuffer.class, clazz);
	}

	@Override
	protected boolean canRead(MediaType mediaType) {
		return false;
	}

	@Override
	public ByteBuffer readInternal(Class<? extends ByteBuffer> clazz, HttpInputMessage inputMessage) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Long getContentLength(ByteBuffer buffer, MediaType contentType) {
		return new Long(buffer.limit());
	}

	@Override
	protected void writeInternal(ByteBuffer buffer, HttpOutputMessage outputMessage) throws IOException {
		WritableByteChannel channel = Channels.newChannel(outputMessage.getBody());
		channel.write(buffer);
	}
}
