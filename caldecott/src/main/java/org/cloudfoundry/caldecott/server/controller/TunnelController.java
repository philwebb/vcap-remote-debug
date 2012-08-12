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
package org.cloudfoundry.caldecott.server.controller;

import java.io.InputStream;
import java.nio.ByteBuffer;

import org.cloudfoundry.caldecott.callback.BlockingCallback;
import org.cloudfoundry.caldecott.server.tunnel.Destination;
import org.cloudfoundry.caldecott.server.tunnel.Tunnel;
import org.cloudfoundry.caldecott.server.tunnel.TunnelId;
import org.cloudfoundry.caldecott.server.tunnel.TunnelNotFoundException;
import org.cloudfoundry.caldecott.server.tunnel.Tunnels;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller to handle all tunnel operations.
 * 
 * @author Phillip Webb
 */
@Controller
@RequestMapping("/tunnels")
public class TunnelController {

	private Tunnels tunnels = new Tunnels();

	// FIXME return the tunnel/s as JSON

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String list() {
		// [
		// {"dst_connected":true,"dst_host":"172.30.48.23","seq_out":6,"dst_port":3306,"seq_in":4,"path_in":"/tunnels/ed92392c-e983-41d1-83e0-81607ad34b39/in","path_out":"/tunnels/ed92392c-e983-41d1-83e0-81607ad34b39/out","path":"/tunnels/ed92392c-e983-41d1-83e0-81607ad34b39"},
		// {"dst_connected":false,"dst_host":"172.30.48.23","seq_out":1,"dst_port":3306,"seq_in":0,"path_in":"/tunnels/b40abd8b-cee6-47a3-8eae-1560439db9d0/in","path_out":"/tunnels/b40abd8b-cee6-47a3-8eae-1560439db9d0/out","path":"/tunnels/b40abd8b-cee6-47a3-8eae-1560439db9d0"}]
		return this.tunnels.toString();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Tunnel create(Destination destination) {
		// FIXME support async
		BlockingCallback<Tunnel> callback = BlockingCallback.forClass(Tunnel.class);
		this.tunnels.add(destination, callback);
		return callback.get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Tunnel info(@PathVariable TunnelId id) {
		return this.tunnels.get(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable TunnelId id) {
		Tunnel tunnel = this.tunnels.get(id);
		tunnel.delete();
	}

	@RequestMapping(value = "/{id}/out/{seq}", method = RequestMethod.GET)
	@ResponseBody
	public ByteBuffer read(@PathVariable TunnelId id, @PathVariable int seq) {
		// FIXME async
		// Tunnel tunnel = this.tunnels.get(id);
		// BlockingCallback<ByteBuffer> callback = BlockingCallback.forClass(ByteBuffer.class);
		// tunnel.read(seq, callback);
		// return callback.get();
		ByteBuffer buffer = ByteBuffer.allocateDirect(100);
		buffer.put("hello".getBytes());
		buffer.flip();
		return buffer;
	}

	@RequestMapping(value = "/{id}/in/{seq}", method = RequestMethod.PUT)
	public void write(@PathVariable TunnelId id, @PathVariable int seq, InputStream inputStream) {
		Tunnel tunnel = this.tunnels.get(id);
		tunnel.write(seq, inputStream);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void onTunnelNotFound(TunnelNotFoundException exception) {
	}
}
