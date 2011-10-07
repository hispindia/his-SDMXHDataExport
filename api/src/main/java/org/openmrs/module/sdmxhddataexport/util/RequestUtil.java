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

package org.openmrs.module.sdmxhddataexport.util;

/*
 * Copyright (c) 2004-2009, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * * Neither the name of the HISP project nor the names of its contributors may
 *   be used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestUtil
{
    public RequestUtil()
    {
    }

    public static String getCurrentLink( HttpServletRequest request )
    {
        //return request.getServletPath();
    	
    	return request.getRequestURI();
        
    }

    public static String getPathInfo( HttpServletRequest request )
    {
        return request.getPathInfo().substring( 1 );
    }

    public static String getSessionString( HttpServletRequest request, String attributeName )
    {
        HttpSession session = request.getSession();
        if ( session.getAttribute( attributeName ) == null )
            return null;
        else
            return session.getAttribute( attributeName ).toString();
    }

    public static Object getSessionObject( HttpServletRequest request, String attributeName, Class<?> clazz )
    {
        HttpSession session = request.getSession();
        if ( session != null )
        {
            Object object = session.getAttribute( attributeName );
            if ( object != null )
                try
                {
                    return object;
                }
                catch ( Exception exception )
                {
                }
        }
        return null;
    }

    public static void removeAttribute( HttpServletRequest request, String attributeName )
    {
        HttpSession session = request.getSession( false );
        if ( session != null )
            session.removeAttribute( attributeName );
    }

    public static String getSessionStringAndRemove( HttpServletRequest request, String attributeName )
    {
        String value = getSessionString( request, attributeName );
        removeAttribute( request, attributeName );
        return value;
    }

}
