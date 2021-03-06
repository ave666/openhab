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
package org.openhab.binding.rfxcom.internal.connector;

import java.util.EventObject;

/**
 * The listener interface for receiving data from RFXCOM controller.
 * 
 * @author Pauli Anttila
 * @since 1.2.0
 */
public class RFXComMessageReceivedEvent extends EventObject {

	private static final long serialVersionUID = 3821740012020068392L;

	public RFXComMessageReceivedEvent(Object source) {
		super(source);
	}

	/**
	 * Invoked when data message is received from RFXCOM controller.
	 * 
	 * @param packet
	 *            Data from controller.

	 */
	public void MessageReceivedEvent(byte[] packet) {
	}

}
