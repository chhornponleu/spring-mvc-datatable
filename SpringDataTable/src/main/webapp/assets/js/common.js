(function() {

	function ajaxSetup() {
		$.ajaxSetup({
			complete : function(resp) {
				if (resp.status == 403) { // 403 = request forbidden
					window.location.href = baseUrl;
				}
			}
		});
	}
	
	/**
	 * Submit form using ajax
	 * @description
	 * This module supports submit multipart form
	 */
	function AjaxSubmit(params) {
		var options = $.extend(true, {
			form : undefined,
			url : undefined,
			callback : function() {
			}
		}, params);
		if (!options.url) {
			console.error("Please specify url")
			return;
		}
		function submit() {
			var formData = new FormData(form);
			$.ajax({
				url : options.url,
				type : 'POST',
				data : formData,
				success : function(data) {
					callback(data)
				},
				cache : false,
				contentType : false,
				processData : false
			});
		}

		return {
			submit : submit
		}
	}

	$.fn.ajaxSubmit = function(url, callback) {
		var isFormElement = $(this).is("form");
		if (!isFormElement) {

		}
		var ajaxForm = new AjaxSubmit({
			url : url,
			form : $(this)[0],
			callback : callback
		});
		$(this).on('submit', function(e) {
			ajaxForm.submit();
			return false;
		});
	}
})();