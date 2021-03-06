/**
 * openHAB, the open Home Automation Bus.
 * Copyright (C) 2010-2013, openHAB.org <admin@openhab.org>
 *
 * See the contributors.txt file in the distribution for a
 * full listing of individual contributors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Additional permission under GNU GPL version 3 section 7
 *
 * If you modify this Program, or any covered work, by linking or
 * combining it with Eclipse (or a modified version of that library),
 * containing parts covered by the terms of the Eclipse Public License
 * (EPL), the licensors of this Program grant you additional permission
 * to convey the resulting work.
 */
package org.openhab.binding.onkyo.internal;

import java.util.EventObject;

import org.openhab.binding.onkyo.internal.eiscp.Eiscp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class open a TCP/IP connection to the Onkyo device and send a command.
 * 
 * @author Pauli Anttila
 * @since 1.3.0
 */
public class OnkyoConnection implements OnkyoEventListener {

	private static Logger logger = 
		LoggerFactory.getLogger(OnkyoConnection.class);

	private String ip;
	private int port;
	private Eiscp connection = null;
	
	public OnkyoConnection(String ip, int port) {
		this.ip = ip;
		this.port = port;
		connection = new Eiscp(ip, port);
	}

	public void openConnection() {
		connection.connectSocket();
	}

	public void closeConnection() {
		connection.closeSocket();
	}

	public void addEventListener(OnkyoEventListener listener) {
		connection.addEventListener(listener);
	}
	
	public void removeEventListener(OnkyoEventListener listener) {
		connection.removeEventListener(listener);
	}
	
	/**
	 * Sends a command to Onkyo device.
	 * 
	 * @param cmd eISCP command to send
	 */
	public void send(final String cmd) {

		try {
			connection.sendCommand(cmd);
		} catch (Exception e) {
			logger.error("Could not send command to device on {}: {}", ip + ":" + port, e);
		}

	}

	@Override
	public void statusUpdateReceived(EventObject event, String ip, String data) {
		
	}

}