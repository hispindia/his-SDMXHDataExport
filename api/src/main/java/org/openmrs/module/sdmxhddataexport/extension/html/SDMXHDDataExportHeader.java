/**
 *  Copyright 2011 Health Information Systems Project of India
 *
 *  This file is part of SDMXDataExport module.
 *
 *  SDMXDataExport module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  SDMXDataExport module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with SDMXDataExport module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.sdmxhddataexport.extension.html;

import org.openmrs.module.Extension;

public class SDMXHDDataExportHeader extends Extension {

	public MEDIA_TYPE getMediaType() {
		return MEDIA_TYPE.html;
	}
	
	public String getRequiredPrivilege() {
		return "View SDMXHDDataExport";
	}

	public String getLabel() {
		return "sdmxhddataexport.title";
	}

	public String getUrl() {
		return "module/sdmxhddataexport/listDataElement.form";
	}
}
