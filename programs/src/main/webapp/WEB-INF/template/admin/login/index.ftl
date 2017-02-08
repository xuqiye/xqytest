<!DOCTYPE html>
<html>
<head>
<title>${message("admin.login.title")}</title>
[#include "/admin/include/head.html"]
<link href="${base}/resource/admin/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resource/js/cookie.js"></script>
<script type="text/javascript" src="${base}/resource/admin/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resource/admin/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resource/admin/js/rng.js"></script>
<script type="text/javascript" src="${base}/resource/admin/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resource/admin/js/base64.js"></script>
<script type="text/javascript" charset="utf-8">
	$().ready(function(){
		var $username = $("#username");
		var $password = $("#password");
		var $captcha = $("#captcha");
		var $captchaImage = $("#captchaImage");
		var $isSaveUsername = $("#isSaveUsername");
		var $submitBt = $("#submitBt");
		$captchaImage.click(function(){
			$captchaImage.attr("src","${base}/admin/common/captcha.do?captchaId=${captchaId}&timestamp="+new Date().valueOf());
		});
		if(getCookie("adminUsername")){
			$isSaveUsername.attr("checked",true);
			$username.val(getCookie("adminUsername"));
			$password.focus();
		}else{
			$isSaveUsername.attr("checked",false);
			$username.focus();
		}
		$("#submitBt").click(function(){
		
			if(!$username.val()){
				$.message("warn", "${message("admin.login.usernameRequired")}");	
				return false;
			}
			if(!$password.val()){
				$.message("warn","${message("admin.password.passwordRequired")}");
				return false;
			}
			if(!$captcha.val()){
				$.message("warn","${message("admin.captcha.captchaRequired")}");	
				return false;
			}
			
			if($isSaveUsername.attr("checked")){
				addCookie("adminUsername", $username.val(), {expires: 30 * 60})
			}else{
				removeCookie("adminUsername",null);
			}
			$.ajax({
				url : "${base}/admin/common/publicKey.do" ,
				type : "GET" ,
				dataType : "json" ,
				cache : false ,
				beforeSend : function(){
					$submitBt.prop("disabled",false);
				},
				success : function(result){
					var rsaKey = new RSAKey();
					rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
					var enPassword = hex2b64(rsaKey.encrypt($password.val()));
					$.ajax({
						url : "${base}/admin/submit.do",
						type : "POST" ,
						data : {
							username : $username.val() ,
							password : enPassword ,
							captchaId : "${captchaId}",
							captcha : $captcha.val()
						},
						dataType : "JSON" ,
						cache : false ,
						success : function(message){
							
						}
					});
				}
			});
			
			
		});
		
	});


</script>
</head>
<body class="login">
	<div class="body">
		<div class="boxTop">
			<div class="boxMiddle"></div>
			<div class="boxButtom">
			<strong>${message("admin.login.title")}</strong>
			</div> 
			<form id="loginFrom" class="loginFrom" action="${base}/admin/login/submit.do">
				<table class="loginTable">
					<tr>
						<th>${message("admin.login.username")}:</th>
						<td><input type="text" id="username" class="username"/></td>
					</tr>
					<tr>
						<th>${message("admin.login.password")}:</th>
						<td><input type="password" id="password" class="password"/></td>
					</tr>
					<tr>
						<th>${message("admin.captcha.name")}:</th>	
						<td><input type="text" id="captcha" class="captcha"></td>
						<td>
						<div class="captchaImage">
						<img id="captchaImage"  src="${base}/admin/common/captcha.do?captchaId=${captchaId}" title="${message("admin.captcha.imageTitle")}" />
						</div>
						</td>
						
					</tr>
					<tr>
						<th>&nbsp;</th>
						<td>
							<input type="checkbox" id="isSaveUsername"/>
							<label for="isSaveUsername">&nbsp;${message("admin.login.rememberUsername")}</label>
						</td>
					</tr>
					<tr>
						<th>&nbsp;</th>
						<td>
							<input type="button" class="homeButton ignoreForm" value="" onclick="window.open('${base}/')" hidefocus="true">
							<input type="button" class="submitButton ignoreForm" value='${message("admin.login.submit")}' hidefocus="true"  id="submitBt"/>
							<input type="button" class="registerButton ignoreForm" value='${message("admin.login.register")}' hidefocus="true"  id="register"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>