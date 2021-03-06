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
package org.openhab.ui.webapp.internal.render;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.openhab.model.sitemap.Image;
import org.openhab.model.sitemap.Widget;
import org.openhab.ui.webapp.render.RenderException;
import org.openhab.ui.webapp.render.WidgetRenderer;

/**
 * This is an implementation of the {@link WidgetRenderer} interface, which
 * can produce HTML code for Image widgets.
 * 
 * @author Kai Kreuzer
 * @since 0.6.0
 *
 */
public class ImageRenderer extends AbstractWidgetRenderer {
	
	/**
	 * {@inheritDoc}
	 */
	public boolean canRender(Widget w) {
		return w instanceof Image;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public EList<Widget> renderWidget(Widget w, StringBuilder sb) throws RenderException {
		Image image = (Image) w;
		String snippet = (image.getChildren().size() > 0) ? 
				getSnippet("image_link") : getSnippet("image");			

		if(image.getRefresh()>0) {
			snippet = StringUtils.replace(snippet, "%setrefresh%", "<script type=\"text/javascript\">imagesToRefreshOnPage=1</script>");
			snippet = StringUtils.replace(snippet, "%refresh%", "id=\"%id%\" onload=\"setTimeout('reloadImage(\\'%url%\\', \\'%id%\\')', " + image.getRefresh() + ")\"");
		} else {
			snippet = StringUtils.replace(snippet, "%setrefresh%", "");
			snippet = StringUtils.replace(snippet, "%refresh%", "");
		}
		
		String widgetId = itemUIRegistry.getWidgetId(w);
		snippet = StringUtils.replace(snippet, "%id%", widgetId);
		
		String sitemap = w.eResource().getURI().path();
		
		String url = "proxy?sitemap=" + sitemap + "&widgetId=" + widgetId + "&t=" + (new Date()).getTime();
		snippet = StringUtils.replace(snippet, "%url%", url);
		
		sb.append(snippet);
		return null;
	}
}
