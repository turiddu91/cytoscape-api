package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.awt.Color;
import java.awt.Component;

import org.cytoscape.util.color.Palette;
import org.cytoscape.util.color.PaletteType;


/**
 * This defines a color chooser that allows users to choose colors from a
 * palette and (optionally) change palettes.
 *
 * @CyAPI.API
 * @CyAPI.InModule swing-util-api
 */
public interface CyColorPaletteChooserFactory {
	public CyColorPaletteChooser getColorPaletteChooser(PaletteType type, boolean paletteOnly);
}
