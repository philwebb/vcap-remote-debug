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
package org.cloudfoundry.caldecott.server.json;

import java.io.IOException;

import org.cloudfoundry.caldecott.server.tunnel.Tunnel;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * {@link JsonSerializer} for {@link Tunnel}s.
 * 
 * @author Phillip Webb
 */
public class TunnelJsonSerializer extends JsonSerializer<Tunnel> {

	@Override
	public void serialize(Tunnel value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonProcessingException {
		String path = "/tunnels/" + value.getId();
		jgen.writeStartObject();
		jgen.writeStringField("path", path);
		jgen.writeStringField("path_in", path + "/in");
		jgen.writeStringField("path_out", path + "/out");
		jgen.writeStringField("dst_host", value.getDestination().getHost());
		jgen.writeNumberField("dst_port", value.getDestination().getPort());
		jgen.writeBooleanField("dst_connected", value.isConnected());
		jgen.writeNumberField("seq_out", value.getOutSequence());
		jgen.writeNumberField("seq_in", value.getInSequence());
		jgen.writeEndObject();
	}

}
