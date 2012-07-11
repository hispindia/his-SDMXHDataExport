/**
 *  Copyright 2009 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of SDMXHDataExport module.
 *
 *  SDMXHDataExport module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  SDMXHDataExport module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with SDMXHDataExport module.  If not, see <http://www.gnu.org/licenses/>.
 *
*/

var EVT =
{
	ready : function()
	{
		/**
		 * Page Actions
		 */
		var enableCheck = true;
		var pageId = jQuery("#pageId").val();
		if(enableCheck && pageId != undefined && eval("CHECK." + pageId))
		{
			eval("CHECK." + pageId + "()");
		}

		/**
		 * Ajax Indicator when send and receive data
		 */
		if(jQuery.browser.msie)
		{
			jQuery.ajaxSetup({cache: false});
		}
	
	}
};

var CHECK = 
{
	
	listDataElementPage : function(){
		jQuery('.date-pick').datepicker({yearRange:'c-30:c+30', dateFormat: 'dd/mm/yy', changeMonth: true, changeYear: true});
		jQuery('#excecuteQuery').dialog({
			autoOpen: false,
			modal: true,
			title: 'Execute query',
			width: '40%',
			buttons: {
				"Run": function() {
					 var sd = jQuery("#startDate").val();
                     var ed = jQuery("#endDate").val();
                     if((sd==null || sd=="")  && (ed==null || ed==""))
                             {
                                     alert(" Provide start date and end date ");
                             }       
                     else if (sd==null || sd=="")
                    {
                       alert(" Provide start date");
                    }
            else if(ed==null || ed=="")
            {
               alert(" Provide end date");
            }
					
					
				else
					{
					//change here
					
										
					var data = jQuery.ajax(
							{
								type:"POST"  //this is sending post request to executeQuery.form
								,url: "executeQuery.form"
								,data: ({startDate :jQuery("#startDate").val(),endDate : jQuery("#endDate").val(), queryId : jQuery("#queryId").val(),})	
								,async: false
								, cache : false
							}).responseText;
					jQuery("#resultExecute").html(data);
					}
				},
				Close: function() {
					jQuery("#resultExecute").html("");
					jQuery( this ).dialog( "close" );
				}
			}
		});
	},
	
	dataElementPage : function()
	{
		var validator = jQuery("#dataElementForm").validate(
		{
			event : "blur",
			rules : 
			{
			
				"name" : { required : true}
				
			}
		});
		
		
	},
	reportPage : function()
	{
		var validator = jQuery("#reportForm").validate(
		{
			event : "blur",
			rules : 
			{
				"name" : { required : true},
				"code" : { required : true}
			}
		});
	},
	queryPage : function()
	{
		var validator = jQuery("#queryForm").validate(
		{
			event : "blur",
			rules : 
			{
			
				"name" : { required : true},
				"sqlQuery" : {required : true}
				
			}
		});
	},
	executeQueryPage : function()
	{
		jQuery('.date-pick').datepicker({yearRange:'c-30:c+30', dateFormat: 'dd/mm/yy', changeMonth: true, changeYear: true});
		var validator = jQuery("#executeQueryForm").validate(
		{
			event : "blur",
			rules : 
			{
			
				"startDate" : { required : true},
				"endDate" : {required : true}
				
			},
			submitHandler: function(form) {
				//form.submit();
				
				var data = jQuery.ajax(
						{
							type:"POST"
							,url: "executeQuery.form"
							,data: ({startDate :jQuery("#startDate").val(),endDate : jQuery("#endDate").val(), queryId : jQuery("#queryId").val(), sqlQuery : jQuery("#sqlQuery").val()})	
							,async: false
							, cache : false
						}).responseText;
				jQuery("#resultExecute").html(data);
			}
		});
	},
	reportDataElementPage : function()
	{
		jQuery('.date-pick').datepicker({yearRange:'c-30:c+30', dateFormat: 'dd/mm/yy', changeMonth: true, changeYear: true});
		var validator = jQuery("#reportDataElementForm").validate(
				{
					event : "blur",
					rules : 
					{
						"reportId" : { required : true},
						"dataElementId" : { required : true},
						"queryId" : {required : true}
						
					},
					submitHandler: function(form) {
						form.submit();
						
						//return true;
					}
				});
		
		jQuery('#excecuteQuery').dialog({
			autoOpen: false,
			modal: true,
			title: 'Execute report',
			width: '40%',
			buttons: {
				"Run": function() {
					var data = jQuery.ajax(
							{
								type:"GET"
								,url: "resultExecuteReport.form"
								,data: ({startDate :jQuery("#startDate").val(),endDate : jQuery("#endDate").val(), reportId : jQuery("#reportId").val(),})	
								,async: false
								, cache : false
							}).responseText;
					jQuery("#divResults").html(data);
					
				},
				Close: function() {
					jQuery("#divResults").html("");
					jQuery( this ).dialog( "close" );
				}
			}
		});
			
				
		jQuery('#excecuteQueryButton').click(function() {
			jQuery('#excecuteQuery').dialog('open');
		});
	},
	listReportPage : function()
	{
		jQuery('.date-pick').datepicker({yearRange:'c-30:c+30', dateFormat: 'dd/mm/yy', changeMonth: true, changeYear: true});
		
		
		
		jQuery('#excecuteQuery').dialog({
			autoOpen: false,
			modal: true,
			title: 'Execute report',
			width: '40%',
			buttons: {
				"Run": function() {
					
					
					 var sd = jQuery("#startDate").val();
                     var ed = jQuery("#endDate").val();
                     if((sd==null || sd=="")  && (ed==null || ed==""))
                             {
                                     alert(" Provide start date and end date ");
                             }       
                     else if (sd==null || sd=="")
                    {
                       alert(" Provide start date");
                    }
            else if(ed==null || ed=="")
            {
               alert(" Provide end date");
            }
					
					
				else
					{
					window.location.href = openmrsContextPath + "/module/sdmxhddataexport/downloadExecutedReport.form?reportId=" + jQuery("#reportId").val() + "&startDate=" + jQuery("#startDate").val() + "&endDate=" + jQuery("#endDate").val()+"&outputType="+jQuery("#outputType").val();
					}
					// var data = jQuery.ajax(
							// {
								// type:"GET"
								// ,url: "resultExecuteReport.form"
								// ,data: ({startDate :jQuery("#startDate").val(),endDate : jQuery("#endDate").val(), reportId : jQuery("#reportId").val(),})	
								// ,async: false
								// , cache : false
							// }).responseText;
					// jQuery("#divResults").html(data);
					// jQuery.ajax({
						// type : "GET",				
						// url : "downloadExecutedReport.form",
						// data : ({
							// startDate :jQuery("#startDate").val(),
							// endDate : jQuery("#endDate").val(), 
							// reportId : jQuery("#reportId").val()
						// })
					// });
				},
				Close: function() {
					jQuery("#divResults").html("");
					jQuery( this ).dialog( "close" );
				}
			}
		});
			
	},
	listQueryPage : function()
	{
		jQuery('.date-pick').datepicker({yearRange:'c-30:c+30', dateFormat: 'dd/mm/yy', changeMonth: true, changeYear: true});
		jQuery('#excecuteQuery').dialog({
			autoOpen: false,
			modal: true,
			title: 'Execute query',
			width: '40%',
			buttons: {
				"Run": function() {
					var data = jQuery.ajax(
							{
								type:"POST"
								,url: "executeQuery.form"
								,data: ({startDate :jQuery("#startDate").val(),endDate : jQuery("#endDate").val(), queryId : jQuery("#queryId").val(),})	
								,async: false
								, cache : false
							}).responseText;
					jQuery("#resultExecute").html(data);
					
				},
				Close: function() {
					jQuery("#resultExecute").html("");
					jQuery( this ).dialog( "close" );
				}
			}
		});
	}
	
};

/**
 * Pageload actions trigger
 */

jQuery(document).ready(
	function() 
	{
		EVT.ready();
	}
);



