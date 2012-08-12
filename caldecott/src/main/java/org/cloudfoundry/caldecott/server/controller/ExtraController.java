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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Phillip Webb
 */
// @Controller
public class ExtraController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String about() {
		return "Caldecott Tunnel (HTTP Transport) 0.0.3\n";
	}

	// FIXME VMC additionals, perhaps a new controller?

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public void vmcInfo() {
		// { "version" => '0.0.4' }.to_json
	}

	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public void services() {
		// services_env = ENV['VMC_SERVICES']
		// return "no services env" if services_env.nil? or services_env.empty?
		// services_env
	}

	@RequestMapping(value = "/services/{name}", method = RequestMethod.GET)
	public void service() {
		// services_env = ENV['VMC_SERVICES']
		// not_found if services_env.nil?
		//
		// services = JSON.parse(services_env)
		// service = services.find { |s| s["name"] == service_name }
		// not_found if service.nil?
		// service["options"].to_json
	}
}
