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
package org.cloudfoundry.caldecott.server.tunnel;

import java.io.InputStream;
import java.nio.ByteBuffer;

import org.cloudfoundry.caldecott.callback.Callback;
import org.cloudfoundry.caldecott.server.json.TunnelJsonSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.util.Assert;

/**
 * @author Phillip Webb
 */
@JsonSerialize(using = TunnelJsonSerializer.class)
public class Tunnel {

	private TunnelId id;

	private Destination destination;

	private boolean connected;

	int inSequence;

	int outSequence;

	public Tunnel(TunnelId id, Destination destination) {
		Assert.notNull(id, "ID must not be null");
		Assert.notNull(destination, "Destination must not be null");
		this.id = id;
		this.destination = destination;
	}

	/**
	 * @return the id
	 */
	public TunnelId getId() {
		return this.id;
	}

	/**
	 * @return the destination
	 */
	public Destination getDestination() {
		return this.destination;
	}

	/**
	 * @return the connected
	 */
	public boolean isConnected() {
		return this.connected;
	}

	/**
	 * @return the inSequence
	 */
	public int getInSequence() {
		return this.inSequence;
	}

	/**
	 * @return the outSequence
	 */
	public int getOutSequence() {
		return this.outSequence;
	}

	/**
	 * 
	 */
	public void delete() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Auto-generated method stub");
	}

	/**
	 * @param seq
	 */
	public void read(int sequence, Callback<ByteBuffer> callback) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Auto-generated method stub");
	}

	/**
	 * @param seq
	 * @param inputStream
	 */
	public void write(int seq, InputStream inputStream) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Auto-generated method stub");
	}

}
