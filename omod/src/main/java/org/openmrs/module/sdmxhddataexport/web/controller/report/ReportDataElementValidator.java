/**
 *  Copyright 2011 Society for Health Information Systems Programmes, India (HISP India)
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
package org.openmrs.module.sdmxhddataexport.web.controller.report;
import org.openmrs.api.context.Context;
import org.openmrs.module.sdmxhddataexport.SDMXHDDataExportService;
import org.openmrs.module.sdmxhddataexport.model.ReportDataElement;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <p> Class: ReportDataElementValidator </p>
 * <p> Package: org.openmrs.module.sdmxhddataexport.web.controller.report </p> 
 * <p> Author: Nguyen manh chuyen(Email: chuyennmth@gmail.com) </p>
 * <p> Update by: Nguyen manh chuyen </p>
 * <p> Version: $1.0 </p>
 * <p> Create date: Sep 22, 2011 2:03:38 PM </p>
 * <p> Update date: Sep 22, 2011 2:03:38 PM </p>
 **/

public class ReportDataElementValidator implements Validator {

    /**
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
        return ReportDataElement.class.equals(clazz);
    }

    /**
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    public void validate(Object command, Errors error) {
        ReportDataElement reportDataElement = (ReportDataElement) command;

        if (reportDataElement.getReport() == null) {
            error.reject("sdmxhddataexport.reportDataElement.report.required");
        }
        if (reportDataElement.getDataElement() == null) {
            error.reject("sdmxhddataexport.reportDataElement.dataElement.required");
        }
        //if (reportDataElement.getQuery() == null) {
        //    error.reject("sdmxhddataexport.reportDataElement.query.required");
        //}
        SDMXHDDataExportService sDMXHDDataExportService = Context.getService(SDMXHDDataExportService.class);
        ReportDataElement reportE = sDMXHDDataExportService.getReportDataElement(reportDataElement.getReport().getId(), reportDataElement.getDataElement().getId());
        if (reportDataElement.getId() != null) {
            if (reportE != null && reportE.getId().intValue() != reportDataElement.getId().intValue()) {
                error.reject("sdmxhddataexport.reportDataElement.existed");
            }
        } else {
            if (reportE != null) {
                error.reject("sdmxhddataexport.reportDataElement.existed");
            }
        }


    }
}
