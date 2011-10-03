SDMXHDDataExport = {
		checkValue : function()
		{
			var form = jQuery("#form");
			if( jQuery("input[type='checkbox']:checked",form).length > 0 )
			{ 
				if(confirm("Are you sure?"))
				{
					form.submit();
				}
			}
			else
			{
				alert("Please choose objects for deleting");
				return false;
			}
		},
		
		search : function(url, value){
			ACT.go(url+"?"+value+"="+jQuery("#"+value).val());
		},
		onChangeReportDataElement : function(thiz){
			ACT.go("reportDataElement.form?reportId="+jQuery(thiz).val());
		},
		showDialog : function(reportId){
			jQuery("#reportId").val(reportId);
			jQuery('#excecuteQuery').dialog('open');
		},
		showQuery : function(queryId){
			jQuery("#queryId").val(queryId);
			jQuery('#excecuteQuery').dialog('open');
		}
		
		
}

