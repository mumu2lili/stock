<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>stock site</title>
<link rel="stylesheet" type="text/css" href="${apiPath}css/c1.css" />
<script type="text/JavaScript" src="${apiPath}js/jquery-3.1.1.min.js"></script>
</head>
<body>
	<h2>stocks!</h2>
	<table id="quote_area">
		<tr>
			<th>代码</th>
			<th>最新价</th>
			<th>时间</th>
		</tr>

	</table>
</body>
<script type="text/javascript">
	$(function() {
		setInterval(getQuote, 5000);
	});

	function getQuote() {
		$.ajax({
			"url" : "${apiPath}v1/stock-quote/quotes?pageNum=1&pageSize=10&t=" + Math.random(),
			"type" : "GET",
			"contentType" : "application/json",
			"success" : function(data) {
				$(".content").remove();

				var arr = data.list;
				for (var i = 0; i < arr.length; i++) {
					var item = arr[i];
					var dateStr = new Date(item.tradeTime).Format(
							"yyyy-MM-dd HH:mm:ss");
					var str = "<tr class='content'><td>" + item.code
							+ "</td><td>" + item.closing + "</td><td>"
							+ dateStr + "</td></tr>";
					$("#quote_area").append(str);
				}

			}
		});
	}

	Date.prototype.Format = function(formatStr) {
		var str = formatStr;
		var Week = [ '日', '一', '二', '三', '四', '五', '六' ];

		str = str.replace(/yyyy|YYYY/, this.getFullYear());
		str = str.replace(/yy|YY/,
				(this.getYear() % 100) > 9 ? (this.getYear() % 100).toString()
						: '0' + (this.getYear() % 100));

		var month = this.getMonth() + 1;
		str = str.replace(/MM/, month > 9 ? month
				.toString() : '0' + month);
		str = str.replace(/M/g, month);

		str = str.replace(/w|W/g, Week[this.getDay()]);

		str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate()
				.toString() : '0' + this.getDate());
		str = str.replace(/d|D/g, this.getDate());

		str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours()
				.toString() : '0' + this.getHours());
		str = str.replace(/h|H/g, this.getHours());
		str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes()
				.toString() : '0' + this.getMinutes());
		str = str.replace(/m/g, this.getMinutes());

		str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds()
				.toString() : '0' + this.getSeconds());
		str = str.replace(/s|S/g, this.getSeconds());

		return str;
	}
</script>
</html>