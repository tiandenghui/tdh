# OAuth2.0

OAuth是一个关于授权（authorization）的开放网络标准，在全世界得到广泛应用，目前的版本是2.0版。

OAuth 2.0定义了四种授权方式。
授权码模式（authorization code）
简化模式（implicit）
密码模式（resource owner password credentials）
客户端模式（client credentials）


#本模块是基于oauth2的认证服务器实现，用来发放token。
	本模块使用最常见的授权码模式实现（authorization code）
	
	授权码模式（authorization code)基本流程如下
		（A）用户访问客户端，后者将前者导向认证服务器。
		（B）用户选择是否给予客户端授权。
		（C）假设用户给予授权，认证服务器将用户导向客户端事先指定的"重定向URI"（redirection URI），同时附上一个授权码。
		（D）客户端收到授权码，附上早先的"重定向URI"，向认证服务器申请令牌。这一步是在客户端的后台的服务器上完成的，对用户不可见。
		（E）认证服务器核对了授权码和重定向URI，确认无误后，向客户端发送访问令牌（access token）和更新令牌（refresh token）。

	服务器基本测试步骤：
		1.启动认证服务器AuthServerApplication
		
		2.浏览器访问http://localhost:6000/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://example.com
		
			/oauth/authorize/:是spring-security-oauth2 提供的授权码接口
			
			response_type=code：此次请求是获取授权码的请求
			
			client_id=acme 客户端ID，可在配置文件中设置，可同时设置多个
			
			redirect_uri=http://example.com 重定向URI，此处可以为任意
		3.输入设置好的用户名user ，密码pass1234	
		
		3.浏览器自动跳转至类似网址：http://example.com/?code=ZQWzAR ，其中ZQWzAR即为授权码
		
		4.使用curl工具发送请求curl acme:acmesecret@172.20.0.24:9998/uaa/oauth/token -d grant_type=authorization_code -d client_id=acme -d redirect_uri=http://example.com -d code=ZQWzAR
			
			grant_type=authorization_code：授权模式为授权码模式
			
			client_id=acme 客户端ID，必须与第二步一致
			
			redirect_uri=http://example.com 重定向URI，必须与第二步中一致
		
			code=ZQWzAR 上一步获取的授权码
			
		5.返回如下格式JSON数据
		{"access_token":"c87d4224-e3a6-4993-800f-87ec9c018920","token_type":"bearer","refresh_token":"c5836d9e-af0c-4d8c-974a-fdedaec62f03","expires_in":42474,"scope":"openid"}
			access_token即为授权令牌，访问资源时需携带
			
#联合zuul测试security
	1、保证服务中心eureka-server、配置中心config-server启动状态。
	2、按顺序启动eureka-provider（或者consumer）、oauth2-server、zuul-gateway。
	3、测试zuul中配置好的/api-a/或者/api-b/接口，会弹出认证界面，按提示通过认证即可访问。
	
		
			
			
			
			
 

 


